package com.awhyse.concurrent.netty.stepfast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对接上海股票行情和深圳股票行情的
 */
public class SCMarketClient {

	Logger logger = LoggerFactory.getLogger(SCMarketClient.class);
	final String HOST = "127.0.0.1";
	final int PORT = 9129;//9129,9130,8888
	static ExecutorService executorService = Executors.newFixedThreadPool(3);

	/**
	 * author:xumin 
	 * 2016-3-30 下午1:41:54
	 * @throws SSLException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				SCMarketClient c1 = new SCMarketClient();
				try {
					c1.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		executorService.execute(run);
	}

	public void init() throws Exception{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
//                       p.addLast(new LengthFieldBasedFrameDecoder(1024*100, 0 ,
//						4, 4, 0));//最后那个是偏移量
						p.addLast(new StepSCDecoder());
						p.addLast(new StepSCEncoder());
						p.addLast(new SCMarketClientHandler());
                    }
                });
 
            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }
        finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
	}

}


