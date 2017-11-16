package com.awhyse.concurrent.redis;

import com.awhyse.util.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by whyse
 * on 2017/8/30 14:30
 */
public class PublisAndSubMain {
    static Logger logger = LoggerFactory.getLogger(PublisAndSubMain.class);
    public static void main(String[] args) throws InterruptedException {
//        RedisClient.HOST = "192.168.4.79";
//        RedisClient.PORT = 6380;
//        RedisClient.AUTH = "Opendoor2017";

//        RedisClient.HOST = "192.168.4.153";
//        RedisClient.PORT = 16379;
//        RedisClient.AUTH = "hzfdt1205";

        RedisClient.HOST = "10.139.10.26";
        RedisClient.PORT = 16379;
        RedisClient.AUTH = "g*tOnQN9kEFb";

        pubAndSubTest();
//        System.out.println(TimeUtil.getFadeLoginTimeByLong(1503296100335L));
//        testWork();//同一个redis客户端不起作用

    }

    private static void pubAndSubTest() {
        String channel = "tradeDetail";
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
//                if(message.contains("002176.SZ")) {
//                    System.err.println(message);
////                    logger.info(message);
//                }
                System.err.println(message);
            }
        };
        RedisClient.subscribe(jedisPubSub,channel);
    }
}
