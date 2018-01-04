package com.awhyse.concurrent.netty.server.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.protocGoogle.LengthBaseDecoderNew;
import com.awhyse.concurrent.netty.decandenc.protocGoogle.LengthBaseEncoderNew;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * google protoBuf+自定义长度+优化协议。极其重要
 * Created by whyse
 * on 2017/1/24 10:12
 */
public class ProtobufLengthBaseServer {
    public static int port = 8881;

    public static void main(String[] args) {
        init(port);
    }
    //===============================================
    private static void init(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //几百万的连接处理，可以
            ServerBootstrap serverBoot = new ServerBootstrap();
            serverBoot.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,256)  //多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
                    .option(ChannelOption.SO_KEEPALIVE, true)  //当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                    .option(ChannelOption.TCP_NODELAY, true) //小封包自动连接，个别时延敏感的去掉
//                    .option(ChannelOption.SO_RCVBUF, 128) //缓冲队列大小
//                    .option(ChannelOption.SO_SNDBUF, 256) //发送队列大小，滑动窗口设置让内核去搞，别设置死了
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
                            //所有头前4位是长度的协议都是这个LengthFieldBasedFrameDecoder，分拆包
                            //actualContentLength = wholeLength + lengthAdjustment
                            //lengthAdjustment =4 就是lengthFieldLength后面的头长度
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(1024*100, 0 ,
                                    4, 4, 0));//最后那个是偏移量
                            pipeline.addLast(new LengthBaseDecoderNew());
                            pipeline.addLast(new LengthBaseEncoderNew());
                            pipeline.addLast(new ProtobufHandler());
                        }
                    });
            serverBoot.childOption(ChannelOption.SO_KEEPALIVE, true);
            // Start the server.
            ChannelFuture f = serverBoot.bind(port).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
