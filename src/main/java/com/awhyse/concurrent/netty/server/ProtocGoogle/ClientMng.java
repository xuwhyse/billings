package com.awhyse.concurrent.netty.server.ProtocGoogle;

import com.awhyse.concurrent.netty.decandenc.protocGoogle.ProtoBufTransfer;
import com.awhyse.concurrent.netty.google.protobuf.PeopleBuf;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 负责各客户端的注册和登出，异步读写
 * Created by whyse
 * on 2017/1/25 11:29
 */
public class ClientMng {
    static Logger logger = LoggerFactory.getLogger(ClientMng.class);
    static ConcurrentHashMap<String,ProtobufHandler> protobufHandlerMap = new ConcurrentHashMap<>(500);
    public static ExecutorService bsExecutorService = Executors.newCachedThreadPool();
    //======================================================
    public static void channelRegistered(ProtobufHandler protobufHandler) {
        String id = protobufHandler.ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
        logger.debug("channelRegistered_id:"+id);
        protobufHandlerMap.put(id,protobufHandler);
    }
    public static void channelUnregistered(ChannelHandlerContext ctx) {
        String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
        protobufHandlerMap.remove(id);
        logger.debug("channelUnregistered_id:"+id);
    }

    public static void channelReadAndDispatch(ChannelHandlerContext ctx, ProtoBufTransfer msg) {
        switch (msg.protoId){
            case 1:
                logger.debug("获取到协议1数据");
                break;
            default:
                try {
                    PeopleBuf.PeopleMsg people2 = PeopleBuf.PeopleMsg.parseFrom(msg.protoBytes);//用包装对象解码
                    logger.debug(msg.protoId+"__该协议号服务端未定义:"+people2.toString());
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }

        }
    }
}
