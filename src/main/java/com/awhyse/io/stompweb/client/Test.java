package com.awhyse.io.stompweb.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whyse
 * on 2017/3/10 17:26
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        List<String> listHead = new ArrayList<>(1);
        listHead.add("sdfgr@&8945");
        headers.put("sdgfhgf",listHead);

        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(standardWebSocketClient);
        String dev = "devxiaot.forexmaster.cn/xiaots";
        String local = "localhost:12306/dev";
        String url = "ws://" +dev;

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

            stompSession.send("/queue/test/send","hello 上登录感觉符合看".getBytes());

            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
