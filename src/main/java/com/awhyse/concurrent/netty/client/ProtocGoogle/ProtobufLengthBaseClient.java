package com.awhyse.concurrent.netty.client.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.protocGoogle.LengthBaseDecoderNew;
import com.awhyse.concurrent.netty.decandenc.protocGoogle.LengthBaseEncoderNew;
import com.awhyse.concurrent.netty.server.ProtocGoogle.ProtobufLengthBaseServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 稳定的长连接使用心跳判断超时。 客户端发起，服务端回复，客户端没收到而已选择重新建立建立连接
 * google protoBuf+自定义长度+优化协议。极其重要
 * Created by whyse
 * on 2017/1/24 10:12
 */
public class ProtobufLengthBaseClient {
    public static int port = 8886;
    public static String host = "127.0.0.1";//"127.0.0.1";

    public static void main(String[] args) {
        init(port);

//        Runnable run = ()->{init(port);};
//        ClientMng.bsExecutorService.execute(run);
    }
    //===============================================
    private static void init(int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            Bootstrap boot = new Bootstrap();
            boot.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));//注册日志打印,开启可以看到更多解析信息
                            //所有头前4位是长度的协议都是这个LengthFieldBasedFrameDecoder，分拆包
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(1024*100, 0 ,
                                    4, 4, 0));//最后那个是偏移量
                            pipeline.addLast(new LengthBaseDecoderNew());
                            pipeline.addLast(new LengthBaseEncoderNew());
                            pipeline.addLast(new ProtobufClientHandler());
                        }
                    });
            // Start the client.
            ChannelFuture f = boot.connect(host, ProtobufLengthBaseServer.port).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Shut down all event loops to terminate all threads.
            workerGroup.shutdownGracefully();
        }
    }

}
