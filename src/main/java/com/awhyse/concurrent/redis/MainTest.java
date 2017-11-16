package com.awhyse.concurrent.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by whyse
 * on 2017/8/28 11:45
 */
public class MainTest {
    public static void main(String[] args) {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
            }
        };


    }
}
