package com.awhyse.concurrent.netty.websocket.client;

import com.awhyse.concurrent.netty.websocket.server.WSNettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;

public class WebSocketClient {

    private  static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    static final String HOST = "127.0.0.1";
    static final int PORT = WSNettyServer.port;

	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws SSLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebSocketClient c1 = new WebSocketClient();
		c1.init();
	}

	public void init() throws Exception{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new HttpClientCodec());//请求和应答的消息编码或者解码为HTTP消息
                            //解决分包拆包，总包大小.  变成FullHttpRequest
    //                        p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new HttpObjectAggregator(1024*1024));
                            //表示向客户端发送HTML5文件，主要用于支持浏览器和服务端进行websocket通信
                            p.addLast(new WebSocketClientHandler());
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
