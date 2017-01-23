package com.awhyse.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 根据我的观察，stream是java8之后的list等容器的辅助工具，用于统计，过滤等,parallel().forEach遍历
 * 就如concurrenthashmap 是 map后的新增强并发map一样
 * 提取子流，limit,skip等操作
 * 不同点是返回新的stream对象，意味着空间浪费
 * Stream.concat(a,b) : 合并stream
 * Created by whyse
 * on 2017/1/23 13:36
 */
public class StreamAbout {
    static Logger logger = LoggerFactory.getLogger(BingFaNew.class);
    //====================================================
    public static void main(String[] args) {
//        streamFilterCount();
//        newSreamAsList();
        streamMap();
    }
    /**
     * map操作，操作原有stream的item，返回新的stream
     * 与filter不同：返回条件子集
     * @author whyse
     * @Date 2017/1/23 16:50
    */
    private static void streamMap() {
        List<Integer> list = new ArrayList<>(6);
        for(int i=0;i<6;i++)
            list.add(i);

        Stream  stream = list.parallelStream().map(v->v+3);
        stream.forEach(item->logger.debug(String.valueOf(item)));
    }

    /*
    日志从可以看到线程名，parallel非常好的使用了系统提供的线程
     */
    private static void newSreamAsList() {
        Stream<String> streamS = Stream.of("1","2","3","4","5","6");
        streamS.parallel().forEach(v->logger.debug(v));//加一个
//        streamS.forEach(v->logger.debug(v));//串行的
    }

    private static void streamFilterCount() {
        List<Map<String,Object>> list = new ArrayList<>(6);
        Map<String,Object> map1 = new HashMap(2);
        map1.put("key",2);

        Map<String,Object> map2 = new HashMap(2);
        map2.put("key",3);

        Map<String,Object> map3 = new HashMap(2);
        map3.put("key",1);

        list.add(map1);
        list.add(map2);
        list.add(map3);
        //stream , 和filter 产生的stream是不同的，每次都是类似深拷贝，筛选
//        Long count = list.stream().filter((v)->{
//            return Integer.parseInt(v.get("key").toString())>=1;
//        }).count();

        //并行执行
        Long count = list.parallelStream().filter((v)->{
            return Integer.parseInt(v.get("key").toString())>=1;
        }).count();

        logger.debug(count.toString());
    }
}