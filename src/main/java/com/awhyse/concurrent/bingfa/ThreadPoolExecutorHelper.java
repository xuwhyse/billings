package com.awhyse.concurrent.bingfa;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xumin
 * @date 2019-06-06 11:30
 */
@Slf4j
public class ThreadPoolExecutorHelper {

    /**
     *
     * @param corePoolSize
     * @param maxPoolSize
     * @param keepAliveSec
     * @param queueSize 阻塞队列长度
     * @param threadNamePreStr 线程池名字前缀
     * @param offerTimeoutSec
     * @return
     */
    public static ThreadPoolExecutor getInstance(int corePoolSize, int maxPoolSize, int keepAliveSec, int queueSize,
                                                 String threadNamePreStr, int offerTimeoutSec) {

        BlockingQueue consumerQueue = new ArrayBlockingQueue(queueSize);
        AtomicLong count = new AtomicLong(0);

        ThreadFactory tf = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread td = new Thread(r);
                td.setName(threadNamePreStr + count.incrementAndGet());
                return td;
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveSec, TimeUnit.SECONDS, consumerQueue, tf, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.warn("消费队列满 consumerQueue size :"+consumerQueue.size());
                try {
                    //主线程回调，如果超时就丢弃任务。注意:蛀牙消费的时间<7s,永远不会丢弃任务
                    boolean isAdd = consumerQueue.offer(r,offerTimeoutSec,TimeUnit.SECONDS);
                    if(isAdd) {
                        log.info("{} consumerQueue.offer 成功",threadNamePreStr);
                    }else{
                        log.error("{} consumerQueue.offer timeout",threadNamePreStr);
                    }
                } catch (InterruptedException e) {
                    log.error("error",e);
                    // clean up state...
                    Thread.currentThread().interrupt();
                }
            }
        });

        return threadPoolExecutor;
    }

}
