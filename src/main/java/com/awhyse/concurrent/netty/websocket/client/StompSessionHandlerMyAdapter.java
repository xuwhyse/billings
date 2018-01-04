package com.awhyse.concurrent.netty.websocket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * Created by whyse
 * on 2017/3/14 13:52
 */
public class StompSessionHandlerMyAdapter extends StompSessionHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(StompSessionHandlerMyAdapter.class);

    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        logger.info("Now connected");
    }
}
