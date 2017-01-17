package com.awhyse.concurrent.netty.client.websocket;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;

/**
 * Created by whyse on 2017/1/16.
 */
public class WSClientHandle extends WebSocketClientHandshaker{
    /**
     * Base constructor
     *
     * @param uri                   URL for web socket communications. e.g "ws://myhost.com/mypath". Subsequent web socket frames will be
     *                              sent to this URL.
     * @param version               Version of web socket specification to use to connect to the server
     * @param subprotocol           Sub protocol request sent to the server.
     * @param customHeaders         Map of custom headers to add to the client request
     * @param maxFramePayloadLength
     */
    protected WSClientHandle(URI uri, WebSocketVersion version, String subprotocol, HttpHeaders customHeaders, int maxFramePayloadLength) {
        super(uri, version, subprotocol, customHeaders, maxFramePayloadLength);
    }
/*
public static void main(String[] args) {
//        new WebSocketClientProtocolHandler();
}
*/

    @Override
    protected FullHttpRequest newHandshakeRequest() {
        return null;
    }

    @Override
    protected void verify(FullHttpResponse response) {

    }

    @Override
    protected WebSocketFrameDecoder newWebsocketDecoder() {
        return null;
    }

    @Override
    protected WebSocketFrameEncoder newWebSocketEncoder() {
        return null;
    }
}
