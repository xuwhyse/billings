package com.awhyse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JedisPool 如果readtimeout出现过多，调大timeout是没用的！！！
 * Created by whyse
 * on 2017/2/9 17:43
 */
public class RedisClient {
    static Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private static JedisPool pool = null;
    //4.72,4.142
    public  static  String HOST = "192.168.4.142";
    public  static  int PORT = 6379;
    public  static  String AUTH = "Opendoor2017";

    private  static AtomicInteger ato = new AtomicInteger(0);
    //=======================================================
    public static void main(String[] args) {
        TimeOutTest();
    }

    //--------------------------------------------------------
    private static void TimeOutTest() {
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
            config.setMaxWaitMillis(8000);

            config.setMaxTotal(5);// 设置最大连接数
            config.setMaxIdle(5);// 最大空闲数
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
            pool = new JedisPool(config, HOST, PORT, 8000, AUTH);
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
    public static void setLeftListRecordByIndex(String key, int index, String obj) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.lset(key, (long) index, obj);
        } catch (Exception var9) {
            logger.info("setLeftListRecordByIndex error, key={}", key, var9);
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

    }

    /**
     *
     * @param key LTD_QUOTE_SZ;LTD_QUOTE_SH...
     * @param field 300250.SZ
     * @return
     */
    public static String hget(String key,String field) {
        String value = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.hget(key,field);
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
    public static long hset(String key,String field,String value) {
        long ret = -1;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            ret = jedis.hset(key,field,value);
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
    public static long delKey(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            logger.info("delete key :"+key);
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
     * 从队列左边获取
     * @param key
     * @param start 队列头部
     * @param end
     * @return
     */
    public static List<String> lrange(String key,long start,long end) {
        Jedis jedis = null;
        List<String> ret = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            ret = jedis.lrange(key,start,end);
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
    public static Set<String> getKeyByFilter(String pat) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.keys(pat);
        } catch (Exception e) {
            logger.info("+++++++++"+e.getMessage());
            e.printStackTrace();
        } finally {
            // 返还到连接池,新的方法
            if(jedis!=null)
                jedis.close();
        }
        return null;
    }

    /**
     * 加入有序集合
     * @param key
     * @param id
     * @param valueJson
     */
    public static void addToSorted(String key, double id, String valueJson) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.zadd(key, id, valueJson);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", key, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取有序集合的大小
     * @param key
     * @return
     */
    public static long zcard(String key) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.zcard(key);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", key, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * 逆序排列的集合，获取排名从start 到end的集合元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> zRevRange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.zrevrange(key,start,end);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", key, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 顺序队列，返回[minScore,maxScore] 的元素
     * @param key
     * @param minScore
     * @param maxScore
     * @return
     */
    public static Set<String> zRangeByScore(String key, double minScore, double maxScore) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.zrangeByScore(key,minScore,maxScore);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", key, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 删除 分数在 [minScore,maxScore] 的元素
     * @param key
     * @param minScore
     * @param maxScore
     * @return
     */
    public static long ZRemRangeByScore(String key, double minScore, double maxScore) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.zremrangeByScore(key,minScore,maxScore);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", key, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public static Set<String> getKeys(String pattern) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.keys(pattern);
        } catch (Exception e) {
            logger.error("getKeys, key={}", pattern, e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return Collections.emptySet();
    }

    /**
     * 向channel发布信息
     * @param channel
     * @param valueJson
     * @return
     */
    public static long publish(String channel, String valueJson) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            return jedis.publish(channel,valueJson);
        } catch (Exception exception) {
            logger.info("publish error, key={}", channel, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
    public static void subscribe(JedisPubSub jedisPubSub, String channel) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            jedis.subscribe(jedisPubSub,channel);
        } catch (Exception exception) {
            logger.info("addToSorted error, key={}", channel, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 定制的批量发送channel命令
     * @param channel
     * @param values
     */
    public static void pipPublish(String channel, List<String> values) {
        Jedis jedis = null;
        try {
            long time = System.currentTimeMillis();
            pool = getPool();
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for(String item : values){
                pipeline.publish(channel,item);
            }
            pipeline.sync();
            long def = System.currentTimeMillis() - time;
//            logger.info("pipPublish size:"+values.size()+"   time:"+def);
        } catch (Exception exception) {
            logger.info("publish error, key={}", channel, exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void zAddOrderForTrans(List<Map<String, Object>> listCacheTransInRedis) {
        Jedis jedis = null;
        try {
            long time = System.currentTimeMillis();
            pool = getPool();
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for(Map<String, Object> map : listCacheTransInRedis){
                String key = map.get("key").toString();
                double idScore = (double) map.get("idScore");
                String valueJson = map.get("valueJson").toString();
                pipeline.zadd(key,idScore,valueJson);
            }
            pipeline.sync();
            long def = System.currentTimeMillis() - time;
//            logger.info("zAddOrderForTrans size:"+listCacheTransInRedis.size()+"   time:"+def);
        } catch (Exception exception) {
            logger.info("zAddOrderForTrans error, key={}", exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    /**
     * 获取队列第一个元素
     * @param key
     * @param index
     * @return
     */
    public static String lindexGet(String key, int index) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            String recordStr = jedis.lindex(key, index);
            return recordStr;
        } catch (Exception e) {
            logger.info("getFirstLeftListRecord error, key={}", key, e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public static void lPush(String key, List<String> list) {
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            Pipeline pipeline = jedis.pipelined();
            for(String item : list){
                pipeline.lpush(key, item);
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.info("lPush error, key={}", key, e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
