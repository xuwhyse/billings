package com.awhyse.concurrent.netty.server.lengthbase;

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
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * 自定义包头，包含长度这种.和异构语言交互
 * 
 * author:xumin 
 * 2016-10-13 下午4:34:09
 */
public class NettyLengthDefServer {

	static final boolean SSL = System.getProperty("ssl") != null;
	public static int port = 8870;


	public void init() {
		// 处理I/O操作的多线程事件环 即为Netty4里的线程池
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);// 接收发来的连接请求
		/*
		 * DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt(
		 * "io.netty.eventLoopThreads", Runtime.getRuntime()
		 * .availableProcessors() * 2)); 源码默认值
		 */
		EventLoopGroup workerGroup = new NioEventLoopGroup();// 用于处理boss接受并且注册给worker的连接中的信息
		// Configure SSL.
		final SslContext sslCtx;

		try {
			if (SSL) {
				SelfSignedCertificate ssc = new SelfSignedCertificate();
				sslCtx = SslContext.newServerContext(ssc.certificate(),
						ssc.privateKey());
			} else {
				sslCtx = null;
			}

			ServerBootstrap bootstrap = new ServerBootstrap();// 这个是ServerBootstrap，跟普通的不一样
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 128).handler(
					new LoggingHandler(LogLevel.INFO));
			// -------------------------------------------------------------------
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				final static private int maxFrameLength=1024*640;
				final static private int lengthFieldOffset = 0;
				final static private int lengthFieldLength = 4;

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// SocketChannel客户端注册进来，相关对象的绑定
					ChannelPipeline p = ch.pipeline();
					if (sslCtx != null) {
						p.addLast(sslCtx.newHandler(ch.alloc()));
					}
					// 最大json的长度,注意！！
//					 p.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息

//					 p.addLast(new StringDecoder());// addLast添加到队列ChannelHandler尾部
//					 p.addLast(new StringEncoder());
//					 p.addLast(new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset , 
//							 lengthFieldLength , lengthFieldOffset, lengthFieldLength));
				
					p.addLast(new ObjectEncoder());//addLast添加到队列ChannelHandler尾部
	                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
					p.addLast(new LengthServerHandler());
				}
			});
			// --------------------------------------------------------------------
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Start the server.
			ChannelFuture f = bootstrap.bind(port).sync();
			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 *             author:xumin 2016-3-30 下午3:34:45
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		NettyLengthDefServer server = new NettyLengthDefServer();
		server.init();
//		byteTest();
	}
	/**
	 * 包头8个字节
	 * 包头{4个字节包体长度（不包含包头）|1个字节压缩类型（0为不压缩）|1字节加密类型（0为不加密）
	 * |1个字节序列化方法（0为json）|1个字节保留} +包体。
压缩方式可以采用开源的压缩库比如zlib，对于某些比较小的消息不压缩，比如心跳之类的。
加密可以采用DES加密。报文体一般先压缩后加密

	 * author:xumin 
	 * 2016-10-13 下午5:16:50
	 */
	private static void byteTest() {
		String a = "我";
		byte[] tar = NettyLengthDefServerHelp.getMyEncodePack(a);
		for(byte temp : tar){
			System.out.println(temp);
		}
	}

}

class LengthServerHandler extends ChannelInboundHandlerAdapter {

	// ======这两个方法可以管理连接================
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		NettyLengthDefServerHelp.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		NettyLengthDefServerHelp.channelUnregistered(ctx);
	}

	// ===================================================
	/**
	 * 注意这边数据可能接收到的还没完全，channelReadComplete才是接受完全，可以解析了
	 * 
	 * @param ctx
	 *            相当于response,可以直接写回,客户端立马能读到,不用ctxflush
	 * @param msg
	 *            对方传过来的对象 author:xumin 2016-3-30 下午3:36:51
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		NettyLengthDefServerHelp.channelRead(ctx,msg);
	}

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	System.err.println("server channelReadComplete");
    	NettyLengthDefServerHelp.decodeAndDispatch(ctx);
//        ctx.flush();//这句flush后直接把ctx写入的消息池发送给远程客户端
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		System.err.println("server exceptionCaught");
		cause.printStackTrace();
		ctx.close();
	}
}
