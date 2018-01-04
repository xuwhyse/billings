package com.awhyse.concurrent.netty.websocket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by whyse on 2017/1/16.
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private  static final Logger logger = LoggerFactory.getLogger(WebSocketClientHandler.class);
    ChannelHandlerContext ctx;
    String RegisterId;
    private static String webSocketURL = "ws://localhost:8088/websocket";
    static Logger log = LoggerFactory.getLogger(WebSocketClientHandler.class);
    WebSocketClientHandshaker webSocketClientHandshaker;

    public WebSocketClientHandler(){
        URI uri = null;
        try {
            uri = new URI(webSocketURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Connect with V13 (RFC 6455 aka HyBi-17). You can change it to V08 or V00.
        // If you change it to V00, ping is not supported and remember to change
        // HttpResponseDecoder to WebSocketHttpResponseDecoder in the pipeline.
        webSocketClientHandshaker  = WebSocketClientHandshakerFactory.newHandshaker(uri,
                WebSocketVersion.V13, null, false,new DefaultHttpHeaders());
    }
    // ======这两个方法可以管理连接================
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //底层连接建立
        webSocketClientHandshaker.handshake(ctx.channel());//发起握手
    }
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) {
//        handshakeFuture = ctx.newPromise();
//    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
//        if (!handshakeFuture.isDone()) {
//            handshakeFuture.setFailure(cause);
//        }
        ctx.close();
    }
    // ====================================================================================
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object frame) throws Exception {
        if(!webSocketClientHandshaker.isHandshakeComplete()){
            //这边切换的编解码
            webSocketClientHandshaker.finishHandshake(ctx.channel(), (FullHttpResponse) frame);
//            handshakeFuture.setSuccess();
            //---------------------
            TextWebSocketFrame msg = new TextWebSocketFrame("hello server");
            ctx.channel().writeAndFlush(msg);
            //-------------------------
            return;
        }
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            String textReceived = textFrame.text();
            System.err.println(textReceived);
        }
    }

}
