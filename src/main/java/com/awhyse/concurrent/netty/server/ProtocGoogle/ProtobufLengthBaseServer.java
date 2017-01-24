package com.awhyse.concurrent.netty.server.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.LengthBaseDecoderNew;
import com.awhyse.concurrent.netty.decandenc.LengthBaseEncoderNew;
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
    public static int port = 8886;

    public static void main(String[] args) {
        init(port);
    }
    //===============================================
    private static void init(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBoot = new ServerBootstrap();
            serverBoot.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
                            //所有头前4位是长度的协议都是这个LengthFieldBasedFrameDecoder，分拆包
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
