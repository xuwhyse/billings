package com.awhyse.concurrent.netty.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http:超文本协议
 * 定制的服务器，只支持http协议，最好是server间交互的短连接,安全，性能更高
 * Created by whyse
 * on 2017/12/6 17:39
 */

public class HttpNettyServer {
    public static int port = 8080;
    private  static final Logger log = LoggerFactory.getLogger(HttpNettyServer.class);

    public static void main(String[] args) {
        HttpServerHelper.init();
        HttpNettyServer httpNettyServer = new HttpNettyServer();
        httpNettyServer.start(); 
    }
    private void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true) //小封包自动连接，个别时延敏感的去掉
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-decoder", new HttpRequestDecoder());
                            //粘包，不过最大的内容可以限制.FullHttprequest or FullHttpResponse
                            pipeline.addLast("http-aggregator", new HttpObjectAggregator(64 * 1024));
                            pipeline.addLast("http-encoder", new HttpResponseEncoder());
                            //支持异步发送大码流，但不占用过多java内存
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                            pipeline.addLast(new HttpHandler());
                        }
                    });
            // --------------------------------------------------------------------
//            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // Start the server.
            ChannelFuture f = serverBootstrap.bind(port).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }


}
/*
1.http请求行: Method requesturl  version
2.http消息头:
3.http请求正文:
例子：
GET http://bbs.blueidea.com/connect.php?mod=check&op=cookie HTTP/1.1
Host: bbs.blueidea.com
Connection: keep-alive
User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36
X-Requested-With: XMLHttpRequest
Accept: *//*
Referer: http://bbs.blueidea.com/thread-3048935-1-1.html
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Cookie: tMjG_2132_saltkey=dZA9b0pN;

 */
