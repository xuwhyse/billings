package com.awhyse.concurrent.netty.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 启一个线程，观察某个soket最近一次读和写的时间
 * 如果超时就调用
 * Created by whyse
 * on 2017/5/23 11:41
 */
public class HeartBeatHandler extends ChannelDuplexHandler {
    private  static final Logger log = LoggerFactory.getLogger(HeartBeatHandler.class);
    /**
     * 10秒内没写，就写一个包发送过去。这样保证对方能读到，就不会断线
     */
    public static final int writerIdleTimeSeconds = 3;
    /**
     * 15秒内没有收到任何信息，就主动断掉
     */
    public static final int readerIdleTimeSeconds = writerIdleTimeSeconds+5;

    static Map<String,String> map = new HashMap<>(1);
    static{
        map.put("type","heart-beat");
    }
    public HeartBeatHandler(){
        log.info("HeartBeatHandler----");
    }
    //=======================================================

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                log.warn("读超时，没有收到信息，主动关闭连接" + ctx.channel().remoteAddress().toString());
                ctx.close();
            }
            if (e.state() == IdleState.WRITER_IDLE) {
                log.warn("写超时========>>>>>>>" + ctx.channel().remoteAddress().toString());
                ctx.channel().writeAndFlush(JSON.toJSONString(map));
            }
        }
    }
}