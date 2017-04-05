package com.awhyse.cache;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by whyse
 * on 2017/2/23 20:31
 */
public class CacheTest {
    public static void main(String[] args) {
        CacheMapXM map = new CacheMapXM();
        Map<String,Object>  mapT = new HashedMap();
        mapT.put("sd","sdgfdh");
        map.put("key",mapT,30);
        mapT = (Map<String, Object>) map.get("key");
        System.err.println(mapT);

        try {
            Thread.sleep(1000*61);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.remove("key");
        mapT = (Map<String, Object>) map.get("key");
        System.err.println(mapT);
    }
}
