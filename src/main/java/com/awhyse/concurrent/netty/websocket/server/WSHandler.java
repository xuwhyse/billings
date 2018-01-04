package com.awhyse.concurrent.netty.websocket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by whyse
 * on 2017/12/7 13:42
 */
public class WSHandler extends SimpleChannelInboundHandler<Object> {
    private  static final Logger logger = LoggerFactory.getLogger(WSHandler.class);
    static WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
            WSNettyServer.webUrl, null, false);
    WebSocketServerHandshaker webSocketServerHandshaker;
    //========================================================================
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
//            logger.info("连接建立，收到客户端http请求");
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * 建立连接
     * @param ctx
     * @param request
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        //第一个完整包不带upgrade:websocket就报错
        if(!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))){
            return;
        }
        webSocketServerHandshaker = wsFactory.newHandshaker(request);
        if (webSocketServerHandshaker == null) {
            //不能支持的版本
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            webSocketServerHandshaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * 连接建立后，处理ws的消息
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame) {
            //判断是否是关闭链路的指令
            webSocketServerHandshaker.close(ctx.channel(),(CloseWebSocketFrame) frame.retain());
            return;
        }
        if (frame instanceof PingWebSocketFrame) {
            //判断是否是Ping消息
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (!(frame instanceof TextWebSocketFrame)) {
            // 本例程仅支持文本消息，不支持二进制消息
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }else{
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
            System.err.println("ws 收到WebSocketFrame  :"+textWebSocketFrame.text());
            ctx.channel().writeAndFlush(new TextWebSocketFrame("欢迎使用Netty WebSocket服务"));//返回数据，不关闭连接
        }

    }

}
