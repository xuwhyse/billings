package com.awhyse.concurrent.netty.client.websocket;

import com.awhyse.concurrent.netty.server.websocket.NettyWSServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import javax.net.ssl.SSLException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonWSClient {

	static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = NettyWSServer.port;
    static final String URL = System.getProperty("url", "ws://127.0.0.1:8880/websocket");
	static ExecutorService executorService = Executors.newFixedThreadPool(3);

	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws SSLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JsonWSClient c1 = new JsonWSClient();
		c1.init();
	}

	public void init() throws Exception{
		final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        }
        else {
            sslCtx = null;
        }

        URI uri = new URI(URL);
        String scheme = uri.getScheme() == null? "ws" : uri.getScheme();
        final String host = uri.getHost() == null? "127.0.0.1" : uri.getHost();
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
//                    // p.addLast(new
//					 LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
					p.addLast("http-codec",new HttpClientCodec());//请求和应答的消息编码或者解码为HTTP消息
					//解决分包拆包，总包大小.  变成FullHttpRequest
					p.addLast("aggregator",new HttpObjectAggregator(1024*1024));
					//表示向客户端发送HTML5文件，主要用于支持浏览器和服务端进行websocket通信
//					p.addLast("http-chunked",new ChunkedWriteHandler());
                    p.addLast(WebSocketClientCompressionHandler.INSTANCE);
					p.addLast("handler",new JsonWSClientHandler());
                    }
                });
 
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }
        finally {
            group.shutdownGracefully();
        }
	}

}
