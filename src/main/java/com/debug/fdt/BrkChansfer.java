package com.debug.fdt;

import com.awhyse.util.RedisClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whyse
 * on 2017/10/24 10:33
 */
public class BrkChansfer {
    public static void main(String[] args) {
        RedisClient.HOST = "10.139.10.23";
        RedisClient.PORT = 16379;
        RedisClient.AUTH = "g*tOnQN9kEFb";

        brkOpt();
    }

    private static void brkOpt() {
        String key = "US_D_BRK.B.US";
        List<String> list = RedisClient.lrange(key,0,20);
        List<String> listTar = new ArrayList<>(list.size());
        for(int i=list.size()-1;i>=0;i--){
            System.err.println(list.get(i));
            listTar.add(list.get(i));
        }
        key = "US_D_BRK/B.US";
        RedisClient.lPush(key,listTar);
    }
}
