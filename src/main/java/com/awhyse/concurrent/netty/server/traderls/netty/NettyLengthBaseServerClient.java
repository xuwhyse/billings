package com.awhyse.concurrent.netty.server.traderls.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;


/**
 * 自定义包头，包含长度这种.和异构语言交互
 * 
 * author:xumin 
 * 2016-10-13 下午4:34:09
 */
public class NettyLengthBaseServerClient {

	//业务主线程池
	public static ExecutorService bsExecutorService = Executors.newCachedThreadPool();
	//101.231.55.6 19999
	static final boolean SSL = System.getProperty("ssl") != null;
	static final String host = "101.231.55.6";//"127.0.0.1";
    static final int port = 19999;//NettyLengthDefServer.port;
    //=====================================================
	public void init() {
		// 处理I/O操作的多线程事件环 即为Netty4里的线程池
		final SslContext sslCtx;
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
        	if (SSL) {
                sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
            }
            else {
                sslCtx = null;
            }
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        if (sslCtx != null) {
                            p.addLast(sslCtx.newHandler(ch.alloc(), host, port));
                        }
//                         p.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
                         p.addLast(new LengthFieldBasedFrameDecoder(1024*100, 0 , 
    							 4, 4, 0));//最后那个是偏移量
                         p.addLast(new MsgDecoderLengthBase());
                         p.addLast(new MsgEncoder());//addLast添加到队列ChannelHandler尾部
                         p.addLast(new LengthServerClientHandler());
                    }
                });
 
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
 
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
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
		NettyLengthBaseServerClient server = new NettyLengthBaseServerClient();
		server.init();
	}

}

class LengthServerClientHandler extends ChannelInboundHandlerAdapter {

	// ======这两个方法可以管理连接================
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		TraderLSServerClientHelp.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		TraderLSServerClientHelp.channelUnregistered(ctx);
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
	public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
		Runnable command = new Runnable() {
			
			@Override
			public void run() {
				TraderLSServerClientHelp.decodeAndDispatch(ctx,(String) msg);
			}
		};
		NettyLengthBaseServerClient.bsExecutorService.execute(command);
	}

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	System.err.println("server channelReadComplete");
//    	TraderLSServerClientHelp.decodeAndDispatch(ctx);
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		System.err.println("server exceptionCaught");
		cause.printStackTrace();
//		ctx.close();
	}
}
