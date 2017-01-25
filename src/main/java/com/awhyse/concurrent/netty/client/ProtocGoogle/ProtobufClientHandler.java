package com.awhyse.concurrent.netty.client.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.protocGoogle.ProtoBufTransfer;
import com.awhyse.concurrent.netty.google.protobuf.PeopleBuf;
import com.awhyse.concurrent.netty.server.ProtocGoogle.ClientMng;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * 每个客户端将会有这么一个对象
 * Created by whyse
 * on 2017/1/24 18:23
 */
public class ProtobufClientHandler extends ChannelInboundHandlerAdapter {
    // ======这两个方法可以管理连接================
    public ChannelHandlerContext ctx=null;
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
//        ClientMng.channelRegistered(this);
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        ClientMng.channelUnregistered(ctx);
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
        System.out.println(msg);
//        Runnable run = ()->{ClientMng.channelReadAndDispatch(ctx,(ProtoBufTransfer)msg);};
//        ClientMng.bsExecutorService.execute(run);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ProtoBufTransfer buf = new ProtoBufTransfer();
        buf.protoId=1;

        PeopleBuf.PeopleMsg.Builder peopleMsgBuilder = PeopleBuf.PeopleMsg.newBuilder();
        peopleMsgBuilder.setName("xumin");
        peopleMsgBuilder.setAge(27);
        PeopleBuf.PeopleMsg people = peopleMsgBuilder.build();


        byte[] encodeBytes = people.toByteArray();//对peopleMSG对象进行编码

        buf.protoBytes = encodeBytes;

        //====================================


        ctx.writeAndFlush(buf);

        Runnable run = ()->{
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buf.protoId = i;

                peopleMsgBuilder.setName(UUID.randomUUID().toString());
                peopleMsgBuilder.setAge(i);


                buf.protoBytes = peopleMsgBuilder.build().toByteArray();
                ctx.writeAndFlush(buf);
            }
        };
        ClientMng.bsExecutorService.execute(run);

    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
