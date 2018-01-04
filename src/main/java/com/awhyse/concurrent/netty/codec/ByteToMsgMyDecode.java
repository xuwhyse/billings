package com.awhyse.concurrent.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by whyse
 * on 2017/12/11 17:41
 */
public class ByteToMsgMyDecode extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        byte[] req = new byte[length];
        in.readBytes(req);
        System.err.println(new String(req));
    }
}
