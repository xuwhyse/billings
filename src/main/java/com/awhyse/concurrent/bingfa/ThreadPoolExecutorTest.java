package com.awhyse.concurrent.bingfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * corePoolSize ,最大线程(当任务队列满，还有任务提交的时候，增长最大线程),keepalive:队列不满的时候回收到corePoolSize大小
 * 拒绝策略：超出max+队列长度的任务，的任务，就超出了
 * ThreadFactory 可以给线程池命名
 * 单线程线程池可以有效处理具有任务有序性的问题
 * Created by whyse
 * on 2017/6/19 14:11
 */
public class ThreadPoolExecutorTest {
    static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread td = new Thread(r);
            td.setName("自定义单线程池");
            return td;
        }
    };
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            60*60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),threadFactory, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int size = executor.getQueue().size();
            logger.info("当前线程数:"+executor.getActiveCount());
        }
    });

    //==================================================
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                try {
//                    logger.info("work");
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                logger.info("work");
            }
        };

        for(int i=0;i<130;i++)
            executor.execute(runnable);
    }
}
