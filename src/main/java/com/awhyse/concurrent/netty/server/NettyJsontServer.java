package com.awhyse.concurrent.netty.server;

import java.util.HashMap;
import java.util.Map;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate; 


import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 
 * author:xumin 
 * 2016-3-29 下午2:48:36
 */
public class NettyJsontServer {
	
	 static final boolean SSL = System.getProperty("ssl") != null;
	 int port = 8870;
	 
	public NettyJsontServer(int port) {
		this.port = port;
	}
	public void init() {
		 //处理I/O操作的多线程事件环 即为Netty4里的线程池
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);//接收发来的连接请求 
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于处理boss接受并且注册给worker的连接中的信息
		// Configure SSL.
		 final SslContext sslCtx;
		
		 try{
	        if (SSL) {
	            SelfSignedCertificate ssc = new SelfSignedCertificate();
	            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
	        } else {
	            sslCtx = null;
	        }
       
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 128).handler(new LoggingHandler(LogLevel.INFO));
			//-------------------------------------------------------------------
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
	
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// SocketChannel客户端注册进来，相关对象的绑定
					 ChannelPipeline p = ch.pipeline();
	                 if (sslCtx != null) {
	                     p.addLast(sslCtx.newHandler(ch.alloc()));
	                 }
//		                 p.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印
	                 //最大json的长度,注意！！
//	                 p.addLast(new JsonObjectDecoder(1024));
	                 
//	                 p.addLast(new LineBasedFrameDecoder(1024));//没有这个，就会发过来什么，收到什么
	                 p.addLast(new StringDecoder());//addLast添加到队列ChannelHandler尾部
	                 p.addLast(new StringEncoder());
	                 p.addLast(new JsonServerHandler());
				}
			});
			//--------------------------------------------------------------------
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Start the server.
	        ChannelFuture f = bootstrap.bind(port).sync();
	        // Wait until the server socket is closed.
	        f.channel().closeFuture().sync();
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally{
        	// Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
	}
	/**
	 * 
	 * @param args
	 * @throws Exception
	 * author:xumin 
	 * 2016-3-30 下午3:34:45
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		NettyJsontServer server = new NettyJsontServer(8870);
		server.init();
	}



}

class JsonServerHandler extends ChannelInboundHandlerAdapter {
	 	@Override
	    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
	       System.err.println("channelRegistered");
	    }
	 	@Override
	    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
	 		System.err.println("channelUnregistered");
	    }
	/**
	 * 
	 * @param ctx 相当于response,可以直接写回,客户端立马能读到,不用ctxflush
	 * @param msg 对方传过来的对象
	 * author:xumin 
	 * 2016-3-30 下午3:36:51
	 */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	System.err.println("server channelRead");
    	System.err.println(msg);
    }
 
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	System.err.println("server channelReadComplete");
//    	Map<String, Object>  map = new HashMap<String, Object>(2);
//    	map.put("111", "111协议");
//    	map.put("222", "data");
//    	String json = JsonUtil.ObToJson(map);
//    	json = json+"\t\n";
//    	ctx.write(json);//ctx直接写入队列池，调用多少次将会在客户端循环调用多少次的channelRead
//    	
//    	map.put("111", "122协议");
//    	map.put("333", "data");
//    	json = JsonUtil.ObToJson(map);
//    	json = json+"\t\n";
//    	ctx.write(json);
    	
        ctx.flush();//这句flush后直接把ctx写入的消息池发送给远程客户端
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
    	System.err.println("server exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
