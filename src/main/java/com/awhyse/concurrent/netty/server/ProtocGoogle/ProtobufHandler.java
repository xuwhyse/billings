package com.awhyse.concurrent.netty.server.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.protocGoogle.ProtoBufTransfer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 每个客户端将会有这么一个对象
 * Created by whyse
 * on 2017/1/24 18:23
 */
public class ProtobufHandler extends ChannelInboundHandlerAdapter {
    // ======这两个方法可以管理连接================
    static Logger logger = LoggerFactory.getLogger(ProtobufHandler.class);
    public ChannelHandlerContext ctx=null;
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        ClientMng.channelRegistered(this);
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ClientMng.channelUnregistered(ctx);
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
        Runnable run = ()->{ClientMng.channelReadAndDispatch(ctx,(ProtoBufTransfer)msg);};
        ClientMng.bsExecutorService.execute(run);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("server exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
