package com.awhyse.concurrent.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLException;

import org.apache.commons.lang.math.RandomUtils;

import com.alibaba.fastjson.JSON;
import com.awhyse.concurrent.netty.server.lengthbase.NettyLengthDefServer;
import com.awhyse.concurrent.netty.server.lengthbase.NettyLengthDefServerHelp;
import com.billings.billingsystem.common.JsonUtil;

public class LengthBaseClient {

	static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = NettyLengthDefServer.port;
	private String title;

	public LengthBaseClient(String title) {
		this.title = title;
	}

	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws SSLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				LengthBaseClient c1 = new LengthBaseClient("这个消息会增长+");
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
				LengthBaseClient c2 = new LengthBaseClient("BBBBBBBBBB-");
				try {
					c2.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		executorService.execute(run);
		executorService.execute(run2);
		
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
//	   	                 p.addLast(new StringDecoder());//addLast添加到队列ChannelHandler尾部
//	   	                 p.addLast(new StringEncoder());
	   	                 p.addLast(new ObjectEncoder());//addLast添加到队列ChannelHandler尾部
		                 p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                         p.addLast(new LengthBaseClientHandler(title));
                    }
                });
 
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
 
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }
        finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
	}

}
//=====================================
class LengthBaseClientHandler extends ChannelInboundHandlerAdapter {
	 
    private String title;


	public LengthBaseClientHandler(String title) {
    	this.title = title;
	}


	@Override
    public void channelActive(ChannelHandlerContext ctx) {
    	System.err.println("channelActive");
    	Map<String, Object> map = new HashMap<String, Object>(2);
    	map.put("123", "hello jsonServer");
    	String msg = JsonUtil.ObToJson(map);
    	byte[] myPack = NettyLengthDefServerHelp.getMyEncodePack(msg);
//    	System.out.println(new String(myPack));
        ctx.writeAndFlush(myPack);//向服务器写入数据
        doJobTest(ctx);
    }
 
 
    private void doJobTest(final ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				String str = title;
				int index = 0;
				while(true){
					try {
						int time = (RandomUtils.nextInt(6)+1)*1000;
						Thread.sleep(time);
						byte[] myPack = NettyLengthDefServerHelp.getMyEncodePack(str);
						ctx.writeAndFlush(myPack);
//						str +=str+index;
						index++;
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
    	System.err.println("channelRead");
    	try{
	    	if(msg instanceof String){
	    		String str = (String) msg;
	    		Map<String, Object>  map = new HashMap<String, Object>(8);
	    		map = JSON.parseObject(str, map.getClass());
	    		System.out.println(map);
	    	}
    	}catch(Exception e){
    		System.err.println("解析出错 ："+msg);
    	}
    }
 
 
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	System.err.println("channelReadComplete");
        ctx.flush();
    }
 
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
