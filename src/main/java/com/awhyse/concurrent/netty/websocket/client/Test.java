package com.awhyse.concurrent.netty.websocket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

/**
 * Created by whyse
 * on 2017/3/10 17:26
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(standardWebSocketClient);
        String url = "ws://localhost:12306/xiaots";

        try {
            StompSession stompSession = webSocketStompClient.connect(url,headers,
                    new StompSessionHandlerMyAdapter()).get();

            stompSession.subscribe("/user/queue/test/send", new StompFrameHandler() {

                public Type getPayloadType(StompHeaders stompHeaders) {
                    return byte[].class;
                }

                public void handleFrame(StompHeaders stompHeaders, Object o) {
                    logger.info("Received greeting " + new String((byte[]) o));
                }
            });

            stompSession.send("/queue/test/send","hello !".getBytes());

            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
