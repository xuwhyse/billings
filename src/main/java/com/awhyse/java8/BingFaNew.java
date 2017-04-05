package com.awhyse.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * 主要是concurrenthashmap 的改进  map.mappingCount() 获取long size
 * forEachEntry  遍历每项
 * map
 * reduce: 就是遍历各种key对应的结果，再遍历一次处理，结果将会多线程fork/join获得
 * 提供原子更新操作
 * 批量操作，map reduce等
 * @author whyse
 * @date 2017/1/23 10:22
 */

public class BingFaNew {
    static Logger logger = LoggerFactory.getLogger(BingFaNew.class);

    public static void main(String[] args) {
        replaceCAS();
        wordIncrement();
        mapReduce();
//        arraySort();
        mapForEach();
    }

    /**
     * forEachEntry 完美做到多线程遍历
     */
    private static void mapForEach() {
        ConcurrentHashMap<String,LongAdder>  map = new ConcurrentHashMap<>(8);
        map.putIfAbsent("word",new LongAdder());
        map.get("word").increment();
        map.get("word").increment();
        map.get("word").increment();

        map.putIfAbsent("key",new LongAdder());
        map.get("key").increment();

        map.putIfAbsent("key1",new LongAdder());
        map.get("key1").increment();

        map.putIfAbsent("key2",new LongAdder());
        map.get("key2").increment();

        map.putIfAbsent("key3",new LongAdder());
        map.get("key3").increment();

        //多线程遍历完毕
        map.forEachEntry(1,item->{
            item.getValue().add(4);
        });

        logger.debug(map.toString());
    }

    private static void arraySort() {
        Map<String,Object>[] list = new HashMap[3];
        Map<String,Object> map1 = new HashMap(2);
        map1.put("key",2);

        Map<String,Object> map2 = new HashMap(2);
        map2.put("key",3);

        Map<String,Object> map3 = new HashMap(2);
        map3.put("key",1);

        list[0] = map1;
        list[1] = map2;
        list[2] = map3;
        //这个是对真的list做的
//        Collections.sort(list, new Comparator<Map<String,Object>>() {
//            @Override
//            public int compare(Map<String,Object> o1, Map<String,Object> o2) {
//                return 0;
//            }
//        });
        Arrays.parallelSort(list,(Map<String,Object> v1,Map<String,Object> v2)->{
            int vv1 = (int) v1.get("key");
            int vv2 = (int) v2.get("key");
            return vv2-vv1;
            });
        for(Map item : list){
            logger.debug(item.toString());
        }

    }
    /**
     * 有自己的foreachItem遍历，不过reduce操作能获取到上一次自定义结算的结果，并非只是遍历
     * 批量数据操作，search:会对每和键或者值应用一个函数，知道函数返回一个非空
     * reduce : 会通过提供的累计函数，将键或者值累加
     * forEach : 会对所有键或值应用一个函数
     * 第一个参数是并行阀值，long.max_value 时就是一个线程，如果为1就是 尽可能多的线程
     * @param
     * @return
     * @author whyse
     * @Date 2017/1/23 10:01
    */
    private static void mapReduce() {
        ConcurrentHashMap<String,LongAdder>  map = new ConcurrentHashMap<>(8);
        map.putIfAbsent("word",new LongAdder());
        map.get("word").increment();
        map.get("word").increment();
        map.get("word").increment();

        map.putIfAbsent("key",new LongAdder());
        map.get("key").increment();

        map.putIfAbsent("key1",new LongAdder());
        map.get("key1").increment();

        map.putIfAbsent("key2",new LongAdder());
        map.get("key2").increment();

        map.putIfAbsent("key3",new LongAdder());
        map.get("key3").increment();
        /*
        第一个参数是阀值
        第二个方法参数是转化器： 就是统计的值需要经过一个计算和转化才能被返回可统计的类型参数
        第三个方法参数:  参数是v1:上一次结果返回值  v2:下一次转化器的返回值。 如果是第一次，则上一次结果是第一个转化值
         */
        String count = map.reduceValues(Long.MAX_VALUE,(v)->{return v.toString();},
                (v1,v2)->{return v1+v2+"_";});
        logger.info(count.toString());

        /*
        这个例子更高级，过滤key，然后把结果累计处理。难能可贵是结果阻塞，过程多线程处理的
        对统计相当有杀伤力
         */
        Long str = map.reduceKeys(1,(key)->{
                    if (key.startsWith("key")) {
                        return map.get(key).longValue();
                    }
            return null;},
                Long::sum);//该方法还是多线程的阻塞方法

        logger.info(str.toString());

        //这个是简单的，上面例子自定义程度更高
//        Long temp = map.reduceValues(Long.MAX_VALUE,(LongAdder v)->{return v.longValue();},Long::sum);
//        map.mappingCount();
    }

    private static void wordIncrement() {
        ConcurrentHashMap<String,LongAdder>  map = new ConcurrentHashMap<>(8);
        //对某个单次统计，如果没有就创建对象返回，有就+1
        LongAdder v = map.putIfAbsent("word",new LongAdder());
        map.get("word").increment();
//        map.putIfAbsent("word",new LongAdder()).increment();//合成一句不行，空指针
        logger.debug(map.get("word").toString());
    }
    /**
     * cas乐观锁，设置key的值
     */
    private static void replaceCAS() {
        ConcurrentHashMap<String,String>  map = new ConcurrentHashMap<>(8);
        map.putIfAbsent("key","key");
        String olderV = "sd";//map.get("key");
        while(!map.replace("key",olderV,"key2")){
            logger.debug("出现并发");
            olderV = map.get("key");
        }
        logger.debug(map.get("key"));
    }
}
