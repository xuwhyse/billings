package com.awhyse.concurrent.netty.client;

import com.alibaba.fastjson.JSON;
import com.awhyse.concurrent.netty.server.HeartBeatHandler;
import com.awhyse.concurrent.netty.server.NettyJsontServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JsonClient {

	Logger logger = LoggerFactory.getLogger(JsonClient.class);
	final boolean SSL = System.getProperty("ssl") != null;
	final String HOST = System.getProperty("host", "127.0.0.1");
	final int PORT = NettyJsontServer.port;
	private String title;
	/**
	 * 0:断开 1：连接
	 */
	public volatile int clientState = 0;
	JsonClient client = null;
	static ExecutorService executorService = Executors.newFixedThreadPool(3);

	public JsonClient(String title) {
		this.title = title;
		client = this;
		watchTDStratr();
	}

	private void watchTDStratr() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if(clientState==0){
					try {
						logger.info("尝试重连--------");
						init();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.SECONDS);
	}
	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws SSLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				JsonClient c1 = new JsonClient("这个消息会增长+");
				try {
					c1.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable run2 = new Runnable() {
			
			@Override
			public void run() {
				JsonClient c2 = new JsonClient("BBBBBBBBBB-");
				try {
					c2.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		executorService.execute(run);
//		executorService.execute(run2);
		
	}

	public void init() throws Exception{
		// TODO Auto-generated method stub
		final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        }
        else {
            sslCtx = null;
        }
 
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        if (sslCtx != null) {
                            p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                        }
//                         p.addLast(new LineBasedFrameDecoder(1024));
	   	                 p.addLast(new StringDecoder());//addLast添加到队列ChannelHandler尾部
	   	                 p.addLast(new StringEncoder());
//                         p.addLast(new JsonObjectDecoder(1024));
						p.addLast("idleStateHandler", new IdleStateHandler(HeartBeatHandler.readerIdleTimeSeconds,
								HeartBeatHandler.writerIdleTimeSeconds, 0));
						p.addLast("idleHandler", new HeartBeatHandler());
						p.addLast(new ReconnetHandler(client));
						p.addLast(new JsonClientHandler(title));
                    }
                });
 
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            clientState = 1;
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }
        finally {
            // Shut down the event loop to terminate all threads.
			clientState = 0;
            group.shutdownGracefully();
        }
	}

}
//=====================================
class JsonClientHandler extends ChannelInboundHandlerAdapter {
	 
    private String title;
	Logger logger = LoggerFactory.getLogger(JsonClientHandler.class);


	public JsonClientHandler(String title) {
    	this.title = title;
	}


	@Override
    public void channelActive(ChannelHandlerContext ctx) {
    	System.err.println("channelActive");
//    	Map<String, Object> map = new HashMap<String, Object>(2);
//    	map.put("123", "hello jsonServer");
//    	String msg = JsonUtil.ObToJson(map);
////    	msg +="\t\n";
//        ctx.writeAndFlush(msg);//向服务器写入数据
        doJobTest(ctx);
    }
 
 
    private void doJobTest(final ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				String str = title;
				int i=0;
				while(true){
					try {
						Thread.sleep(1*1000);
						ctx.writeAndFlush(i+"------------");
						logger.info(String.valueOf(i));
						i++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread td = new Thread(run);
		td.start();
	}


	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
		logger.info("<<<=========channelRead");
    	try{
	    	if(msg instanceof String){
	    		String str = (String) msg;
	    		Map<String, Object>  map = new HashMap<String, Object>(8);
	    		map = JSON.parseObject(str, map.getClass());
				logger.info(map.toString());

//				Thread.currentThread().sleep(5*1000);
	    	}
    	}catch(Exception e){
    		System.err.println("解析出错 ："+msg);
    	}
    }
 
 
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//    	System.err.println("channelReadComplete");
//        ctx.flush();
//    }
 
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();//远程主机强制关闭连接
        ctx.close();
    }
}


//==============================================
class ReconnetHandler extends ChannelInboundHandlerAdapter {

	JsonClient client = null;
	public ReconnetHandler(JsonClient client) {
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

