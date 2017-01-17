package com.awhyse.concurrent.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * author:xumin 2016-3-29 下午2:48:36
 */
public class NettyJsontServer {

	static final boolean SSL = System.getProperty("ssl") != null;
	public static int port = 8870;

	public NettyJsontServer(int port) {
		this.port = port;
	}

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

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// SocketChannel客户端注册进来，相关对象的绑定
					ChannelPipeline p = ch.pipeline();
					if (sslCtx != null) {
						p.addLast(sslCtx.newHandler(ch.alloc()));
					}
					// p.addLast(new
//					 LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
					// 最大json的长度,注意！！
					// p.addLast(new JsonObjectDecoder(1024));

					// p.addLast(new
					// LineBasedFrameDecoder(1024));//没有这个，就会发过来什么，收到什么
					p.addLast(new StringDecoder());// addLast添加到队列ChannelHandler尾部
					p.addLast(new StringEncoder());
					p.addLast(new JsonServerHandler());
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
		NettyJsontServer server = new NettyJsontServer(port);
		server.init();
	}

}

class JsonServerHandler extends ChannelInboundHandlerAdapter {

	//这样能防止超长字符窜多次调用，乱码的问题
	Map<String, Map<String, Object>> packageMap = new HashMap<String, Map<String, Object>>(
			100);

	// ======这两个方法可以管理连接================
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("length", 0);
		List<Object>  list = new ArrayList<Object>(5);
		map.put("list", list);
		packageMap.put(id, map);
		System.out.println("channelRegistered:" + id);// channelRegistered:a86f543a
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		packageMap.remove(id);
		System.out.println("channelUnregistered:" + id);// channelUnregistered:a86f543a
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

		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		Map<String, Object>  map = packageMap.get(id);
		int lengthR = (Integer) map.get("length");
		List<Object>  list = (List<Object>) map.get("list");
		byte[] obj = msg.toString().getBytes();//获取字节，1byte 1字节
		int length = lengthR+obj.length;
		map.put("length", length);
		list.add(obj);
		System.out.println("server channelRead:" + id + "  length:" + length);
		// System.out.println(msg);
	}

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	System.err.println("server channelReadComplete");
    	String id = ctx.channel().id().asShortText();//这个id可以做key，区分不同连线
    	Map<String, Object>  map = packageMap.get(id);
    	int lengthR = (Integer) map.get("length");
		List<Object>  list = (List<Object>) map.get("list");
		byte[]  bytes = new byte[lengthR];
		int destPos = 0;
		for(Object item : list){
			byte[] tar = (byte[]) item;
			System.arraycopy(tar, 0, bytes, destPos, tar.length);
			destPos += tar.length;
		}
		System.out.println(new String(bytes));
		//----清理数据--------------
		map.put("length", 0);
		list.clear();
		//-----------------------
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
