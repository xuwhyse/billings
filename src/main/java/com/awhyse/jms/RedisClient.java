package com.awhyse.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JedisPool 如果readtimeout出现过多，调大timeout是没用的！！！
 * Created by whyse
 * on 2017/2/9 17:43
 */
public class RedisClient {
    static Logger logger = LoggerFactory.getLogger(RedisClient.class);
    public volatile  boolean  isRedisBusy = false;
    public volatile  long  lastBusyTime;
    public static ExecutorService executorService = Executors.newFixedThreadPool(1000);
    private static JedisPool pool = null;
    private  static final String HOST = "192.168.4.151";
    private  static final int PORT = 6379;

    private  static AtomicInteger ato = new AtomicInteger(0);
    //=======================================================
    public static void main(String[] args) {
        TimeOutTest();
    }

    //--------------------------------------------------------
    private static void TimeOutTest() {
        for(int i=0;i<1000;i++){
            int temp = i;
            executorService.submit(()->{
                try {
                    String key = "test_"+temp;
                    test(key);
                }catch (Exception e){
                    logger.info("---------:"+e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }
    public static void test(String key) {
        try {
            getPool();
//            String key = "RedisTest";
            String value = "key";
            String temp = RedisClient.set(key, value);
            int count = ato.incrementAndGet();
            logger.info("count:"+count);
            if (value.equals(RedisClient.get(key))) {
                long del = RedisClient.delKey(key);
                logger.info("RedisTest is OK");
            }
        }catch (Exception e){
            logger.info("RedisTest not connected!!!");
            e.printStackTrace();
        }
    }

    private static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setBlockWhenExhausted(true);// 连接耗尽时是否阻塞，或者抛出异常
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(100);

            config.setMaxTotal(20);// 设置最大连接数
            config.setMaxIdle(10);// 最大空闲数
            config.setMinIdle(1);

            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(false);
            // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(1800000);
            // 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数
            // 时直接逐出,不再根据MinEvictableIdleTimeMillis判断 (默认逐出策略)
            config.setSoftMinEvictableIdleTimeMillis(1800000);

            // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            config.setTimeBetweenEvictionRunsMillis(1000 * 300);

            //timeOut 主要是这个参数引起
            pool = new JedisPool(config, "127.0.0.1", 6380, 100, "xumin");
//            pool = new JedisPool(config, HOST, PORT, 6000);
        }
        return pool;
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = null;

        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return value;
    }
    public static long delKey(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return 0l;
    }

    public static Object getex(String key) {
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            byte[] data = jedis.get(key.getBytes());
            if (data == null) {
                return null;
            }
            result = byteToObject(data);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;

    }

    /**
     * 可更新，不过期
     * @param key
     * @param value
     * @return
     * author:xumin
     * 2016-12-9 下午2:27:00
     */
    public static String set(String key,String value) {
        Jedis jedis = null;
        String ret = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            ret = jedis.set(key,value);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return ret;
    }
    /**
     * 最常见的设置
     * @param key
     * @param value
     * @param seconds
     * @return
     * author:xumin
     * 2016-12-9 下午2:23:55
     */
    public static String setex(String key,String value,int seconds) {

        Jedis jedis = null;
        String ret = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
//            jedis.setnx()//可以实现分布式锁
            ret = jedis.setex(key,seconds,value);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return ret;
    }
    /**
     * @return
     * @author whyse
     * @Date 2017/2/9 18:10
    */
    public static String setex(String key,Object value,int seconds) {

        Jedis jedis = null;
        String ret = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            ret = jedis.setex(key.getBytes(),seconds,objectToByte(value));
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return ret;
    }

    public static byte[] objectToByte(Object value) {
        return SerializationUtils.serialize(value);
    }
    public static Object byteToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        if (bytes == null) {
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }

}
