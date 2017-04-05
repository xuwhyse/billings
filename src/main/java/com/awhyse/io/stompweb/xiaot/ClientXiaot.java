package com.awhyse.io.stompweb.xiaot;

import com.alibaba.fastjson.JSON;
import com.awhyse.io.stompweb.client.StompSessionHandlerMyAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by whyse
 * on 2017/3/31 16:54
 */
public class ClientXiaot {
    String gameId;
    String userId;
    int userType;
    String headimgurl;
    String userName;
    StompSession stompSession;
    private static Logger logger = LoggerFactory.getLogger(ClientXiaot.class);

    public ClientXiaot(String gameId, String userId, int userType, String headimgurl, String userName) {
        this.gameId = gameId;
        this.userId = userId;
        this.userType = userType;
        this.headimgurl = headimgurl;
        this.userName = userName;
    }

    public void connect(String currentEnv) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        List listHead = new ArrayList<>(1);
        listHead.add(new WebSocketExtension("permessage-deflate"));
        listHead.add(new WebSocketExtension("client_max_window_bits"));
        headers.setSecWebSocketExtensions(listHead);
        headers.set("Accept-Encoding", "gzip, deflate, sdch");
//        headers.put("sdgfhgf",listHead);

        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        webSocketContainer.setDefaultMaxTextMessageBufferSize(512*1024);
        webSocketContainer.setDefaultMaxBinaryMessageBufferSize(512*1024);

        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient(webSocketContainer);
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(standardWebSocketClient);

//        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
//        te.setPoolSize(1);
//        te.setThreadNamePrefix("wss-heartbeat-thread-");
//        te.initialize();
//        int heartBeatTime = 1000*10;
//        webSocketStompClient.setTaskScheduler(te); // for heartbeats

        webSocketStompClient.setInboundMessageSizeLimit(1024*1024);//64*1024

        String url = "ws://" +currentEnv;

        try {
            stompSession = webSocketStompClient.connect(url,headers, new StompSessionHandlerMyAdapter()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送给服务端信息
     * @param destination
     * @param msg
     */
    private void sendToServer(String destination, String msg) {
        stompSession.send(destination, msg.getBytes());
    }
    //===========================================================================================================
    /**
     * 必须要准备，准备后进入
     */
    public void doReady() {
        String des = "/user/queue/game/doReady";
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
//                logger.info("===>>>>  "+des+"__"+ new String((byte[]) o));
            }
        });

        Map<String,Object> mapReady = new HashedMap(6);
        mapReady.put("gameId",gameId);
        mapReady.put("userId",userId);
        mapReady.put("userType",userType);
        mapReady.put("headimgurl",headimgurl);
        mapReady.put("userName",userName);

        sendToServer("/queue/game/doReady",JSON.toJSONString(mapReady));
    }

    /**
     * 准备阶段，接受的准备用户列表信息
     */
    public void subReadyInfoTest() {
        String des = "/topic/game/readyInfo/"+gameId;
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                String str  = new String((byte[]) o);
                logger.info("===>>>>  "+des+"__"+ str);
            }
        });
    }

    /**
     * 订阅了排行榜的信息，信息量比较大，io大
     */
    public void subListInfoTest() {
        String des = "/topic/game/listInfo/"+gameId;
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                String str  = new String((byte[]) o);
                logger.info("===>>>>  "+des+"__"+ str.length());
            }
        });
    }

    /**
     * 模拟用户在收到行情订阅后，应该发送收益率等，使list排名运转起来
     */
    public void subTimePointTest() {
        String des = "/topic/game/timePoint/"+gameId;
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("===>>>>  "+des+"__"+ new String((byte[]) o));
            }
        });
    }

    public void subListInfoLimit() {
        String des = "/topic/game/listInfoLimit/"+gameId;
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("===>>>>  "+des+"__"+ new String((byte[]) o));
            }
        });
    }

    public void subListInfoSimple() {
        String des = "/topic/game/listInfoSimple/"+gameId;
        stompSession.subscribe(des, new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                logger.info("===>>>>  "+des+"__"+ new String((byte[]) o));
            }
        });
    }
}
