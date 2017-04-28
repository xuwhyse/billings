package com.awhyse.io.stompweb.xiaot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

/**
 * Created by whyse
 * on 2017/3/14 13:52
 */
//extends StompSessionHandlerAdapter
public class StompSessionHandlerMyAdapter implements StompSessionHandler{
    private static Logger logger = LoggerFactory.getLogger(StompSessionHandlerMyAdapter.class);
    private final ClientXiaot clientXiaot;

    public StompSessionHandlerMyAdapter(ClientXiaot clientXiaot) {
        this.clientXiaot = clientXiaot;
    }

    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        logger.info("Now connected");
    }
    /**
     * This implementation returns String as the expected payload type
     * for STOMP ERROR frames.
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        logger.info("handleFrame");
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers,
                                byte[] payload, Throwable exception) {
        logger.info("handleException");
    }

    /**
     * 断线的时候回收到这边的消息
     * @param session
     * @param exception
     */
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.info("handleTransportError");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientXiaot.doConnect();//重连
    }
}
