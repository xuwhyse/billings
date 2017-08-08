package com.awhyse.gateway;

import com.awhyse.gateway.server.MkGWMsgReciver;
import com.awhyse.gateway.transport.FDTFrameDecoder;
import com.awhyse.gateway.transport.FDTFrameEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 根上级gateway连接的类,继承runable,线程启动就是去连接gateway
 */
public class MsgPackLiteDataClient {
	
	private static final Logger log = LoggerFactory.getLogger(MsgPackLiteDataClient.class);
	
	private static String[] codeTableNeededMarkets = null;

	private Channel channel = null;
	List<String> listOrder = Collections.synchronizedList(new ArrayList<>(10));//;
	public String gatewayHost;
	public Integer gatewayPort;
	private Integer buffingSize;
	/**
	 * 0:断开 1：连接
	 */
	public volatile int clientState = 0;
	public MsgPackLiteDataClient client = null;
	public MkGWMsgReciver mkGWMsgReciver = null;
	public ChannelHandlerContext ctx;//写数据的,由底层返回填写

	public MsgPackLiteDataClient() {
		client = this;
	}
	/**
	 * 开启重连监控+启动netty
	 */
	public void run() {
		runNettyAsy();
		reConnectTDStart();
	}
	private void runNettyAsy() {
		Runnable runNetty = new Runnable() {
			@Override
			public void run() {
				runNetty();
			}
		};
		Thread tdNetty = new Thread(runNetty);
		tdNetty.setName("gateway_client");
		tdNetty.start();
	}
	private void reConnectTDStart() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if(clientState==0){
					try {
						log.info("----------尝试重连--------");
						runNetty();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
//				else{
//					log.info("----------与上游gateway连接OK--------");
//				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 30, 20, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		MsgPackLiteDataClient item = new MsgPackLiteDataClient();
		item.gatewayHost = "127.0.0.1";
		item.gatewayPort = 8870;
	}
	//====================================================================================

	private void runNetty() {
		EventLoopGroup group = new NioEventLoopGroup();
		ChannelFuture f;
		Bootstrap bootstrap = new Bootstrap()
		.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
		.handler(new MsgPackLiteDataClientInitializer(client));
		try {			
			// Start the client.
			f = bootstrap.connect(gatewayHost,gatewayPort);
			f.awaitUninterruptibly();
			if(f.isSuccess()) {
				clientState = 1;
				log.info("----------MsgPackLiteDataClient started--------");
				// Wait until the connection is closed.
				channel = f.channel();
//				MsgPackLiteDataClientHandler.sendBufferingSizeMsg(buffingSize);
				f.channel().closeFuture().sync();
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		finally {
			clientState = 0;
			log.info("----------MsgPackLiteDataClient stop!!--------");
			group.shutdownGracefully();
		}
	}

	/**
	 * 发送给上游gateway的请求，比如订阅等
	 * @param str
	 */
	public  void sendRequest(String str) {
		if(ctx==null){
			//记录指令
			listOrder.add(str);
		}else{
			ctx.writeAndFlush(str);
			log.info(str);
		}
	}
	/**
	 * 激活回调，可以发命令了
	 */
	public void onActive() {
		for(int i=0;i<listOrder.size();i++){
			sendRequest(listOrder.get(i));
		}
		listOrder.clear();
	}
	public static String addHashTail(String str,boolean bAddHash)
	{
		if(bAddHash) {
			return str + "|Hash=" + str.hashCode();
		}
		return str;
	}

	public static String[] getCodeTableNeededMarkets()
	{
		return codeTableNeededMarkets;
	}

	public void setCodeTableNeededMarkets(String[] codeTableNeededMarkets)
	{
		MsgPackLiteDataClient.codeTableNeededMarkets = codeTableNeededMarkets;
	}

}


class MsgPackLiteDataClientInitializer extends ChannelInitializer<SocketChannel> {

	MsgPackLiteDataClient client = null;
	public MsgPackLiteDataClientInitializer(MsgPackLiteDataClient client) {
		this.client = client;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
	 	ch.pipeline().addLast("decoder",new FDTFrameDecoder());
	 	ch.pipeline().addLast("encoder",new FDTFrameEncoder());
	 	//设置read超时为7秒，write为10,秒,有
		pipeline.addLast("idleStateHandler", new IdleStateHandler(15, 8, 0));
		pipeline.addLast("idleHandler", new MsgPackLiteDataClientIdleHandler(client));
		pipeline.addLast(new ReconnetHandler(client));
		pipeline.addLast("handler", new MsgPackLiteDataClientHandler(client));
		
	}
}

class MsgPackLiteDataClientIdleHandler extends ChannelDuplexHandler {
	private static final Logger log = LoggerFactory.getLogger(MsgPackLiteDataClientIdleHandler.class);
	MsgPackLiteDataClient client = null;

	public MsgPackLiteDataClientIdleHandler(MsgPackLiteDataClient client) {
		this.client = client;
	}

	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
            		log.warn("Msg Pack Lite Upstream reader idle for " + 7 + " seconds , channel will close : " + ctx.channel().remoteAddress().toString());
            		ctx.close();
				client.clientState = 0;
            } 
            if (e.state() == IdleState.WRITER_IDLE) {
            	ctx.channel().writeAndFlush(MsgPackLiteDataClient.addHashTail("API=ClientHeartBeat",true));
            }
        }
    }
}

//==============================================
class ReconnetHandler extends ChannelInboundHandlerAdapter {

	MsgPackLiteDataClient client = null;
	public ReconnetHandler(MsgPackLiteDataClient client) {
		this.client = client;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		client.clientState = 0;
		super.exceptionCaught(ctx,cause);
		cause.printStackTrace();//远程主机强制关闭连接
	}
}





