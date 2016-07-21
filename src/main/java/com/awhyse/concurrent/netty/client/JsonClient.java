package com.awhyse.concurrent.netty.client;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.internet.billingsystem.common.JsonUtil;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class JsonClient {

	static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8870"));

	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 // Configure SSL.git
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
                         p.addLast(new JsonClientHandler());
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
class JsonClientHandler extends ChannelInboundHandlerAdapter {
	 
 
 
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    	System.err.println("channelActive");
    	Map<String, Object> map = new HashMap<String, Object>(2);
    	map.put("123", "hello jsonServer");
    	String msg = JsonUtil.ObToJson(map);
    	msg +="\t\n";
        ctx.writeAndFlush(msg);//向服务器写入数据
    }
 
 
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	System.err.println("channelRead");
    	if(msg instanceof String){
    		String str = (String) msg;
    		Map<String, Object>  map = new HashMap<String, Object>(8);
    		map = (Map<String, Object>) JsonUtil.JsonToOb(str, map.getClass());
    		System.out.println(map);
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
