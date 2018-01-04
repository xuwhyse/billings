package com.awhyse.concurrent.netty.stepfast;

import com.awhyse.concurrent.netty.stepfast.common.StepCommon;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * 每个客户端将会有这么一个对象
 * Created by whyse
 * on 2017/1/24 18:23
 */
public class SCMarketClientHandler extends ChannelInboundHandlerAdapter {
    public ChannelHandlerContext ctx=null;
    //==========================================================
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
//        System.out.println(msg);
        System.out.println(1);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Map<Integer,String> map = StepCommon.getLogon();
        ctx.writeAndFlush(map);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
