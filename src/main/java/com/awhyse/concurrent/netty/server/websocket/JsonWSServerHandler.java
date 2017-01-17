package com.awhyse.concurrent.netty.server.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * Created by whyse on 2017/1/16.
 */
public class JsonWSServerHandler extends ChannelInboundHandlerAdapter {
    private WebSocketServerHandshaker handshaker;
    String RegisterId;
    private static String webSocketURL = "ws://localhost:"+NettyWSServer.port+"/";
    static Logger log = LoggerFactory.getLogger(JsonWSServerHandler.class);

    public JsonWSServerHandler(){
        System.err.print("创建handle对象");
    }
    // ======这两个方法可以管理连接================
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        RegisterId = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
        System.err.println("channelRegistered:" + RegisterId);// channelRegistered:a86f543a
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
        System.err.println("channelUnregistered:" + id);// channelUnregistered:a86f543a
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    // ====================================================================================
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
        System.out.println("server channelRead id:"+id+"____");
        /** * HTTP接入，WebSocket第一次连接使用HTTP连接,用于握手 */
        if(msg instanceof FullHttpRequest){
            log.debug("http握手_RegisterId:"+RegisterId);
            handleHttpRequest(ctx, (FullHttpRequest)msg);
        }
        /** * Websocket 接入 */
        else if(msg instanceof WebSocketFrame){
            log.debug("WebSocketFrame:"+msg);
            handlerWebSocketFrame(ctx, (WebSocketFrame)msg);
        }
    }

    /**
     * 这边是处理已经接入的ws的数据入口
     * @param ctx
     * @param frame
     */
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if(frame instanceof TextWebSocketFrame){
            // 返回应答消息
            String request = ((TextWebSocketFrame) frame).text();
            System.out.println("服务端收到：" + request);
            ctx.channel().write(new TextWebSocketFrame("服务器收到并返回："+request));
        }
        /** * 判断是否关闭链路的指令 */
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(),
                    (CloseWebSocketFrame) frame.retain());
            return;
        }

        /** * 判断是否ping消息 */
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(
                    new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        /** * 本例程仅支持文本消息，不支持二进制消息 */
        if (frame instanceof BinaryWebSocketFrame) {
            throw new UnsupportedOperationException(String.format(
                    "%s frame types not supported", frame.getClass().getName()));
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //如果包头里没有Upgrade，或者不等于websocket，或者错误就返回写死的res
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                webSocketURL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);//这句话执行就是已经建立连接
        }
    }

    /**
     * 用于发送失败的http请求
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
