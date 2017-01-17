package com.awhyse.concurrent.netty.server.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 
 * author:xumin 2016-3-29 下午2:48:36
 */
public class NettyWSServer {

	static final boolean SSL = System.getProperty("ssl") != null;
	public static int port = 8880;

	public void init() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);// 接收发来的连接请求
		EventLoopGroup workerGroup = new NioEventLoopGroup();// 用于处理boss接受并且注册给worker的连接中的信息
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
					ChannelPipeline p = ch.pipeline();
					if (sslCtx != null) {
						p.addLast(sslCtx.newHandler(ch.alloc()));
					}
					// p.addLast(new
//					 LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
					p.addLast("http-codec",new HttpServerCodec());//请求和应答的消息编码或者解码为HTTP消息
					//解决分包拆包，总包大小.  变成FullHttpRequest
					p.addLast("aggregator",new HttpObjectAggregator(1024*1024));
					//表示向客户端发送HTML5文件，主要用于支持浏览器和服务端进行websocket通信
					p.addLast("http-chunked",new ChunkedWriteHandler());
					//-----------
//					p.addLast(new HttpRequestHandler("/ws"));
//					p.addLast(new WebSocketServerProtocolHandler("/ws"));
					p.addLast("handler",new JsonWSServerHandler());
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
		NettyWSServer server = new NettyWSServer();
		server.init();
	}

}
