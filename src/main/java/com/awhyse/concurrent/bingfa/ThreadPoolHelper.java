package com.awhyse.concurrent.bingfa;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * corePoolSize ,最大线程(当任务队列满，还有任务提交的时候，增长最大线程),keepalive:队列不满的时候回收到corePoolSize大小
 * 最大任务处理数= 队列长度+最大线程数
 * 拒绝策略：超出max+队列长度的任务，的任务，就超出了
 * ThreadFactory 可以给线程池命名
 * 单线程线程池可以有效处理具有任务有序性的问题
 * Created by whyse
 * on 2017/6/19 14:11
 */
@Slf4j
public class ThreadPoolHelper {

    private static BlockingQueue consumerQueue;
    /**
     * 融资工程异步任务队列
     */
    public static ThreadPoolExecutor threadPoolExecutor;
    static {
        consumerQueue = new ArrayBlockingQueue(1);
        ThreadFactory tf = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread td = new Thread(r);
                td.setName("loan_ThreadPoolHelper");
                return td;
            }
        };
        threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                60 * 60, TimeUnit.SECONDS, consumerQueue, tf, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.error("消费队列满 consumerQueue :"+consumerQueue.size());
                try {
                    //如果超时就丢弃任务
                    boolean isAdd = consumerQueue.offer(r,1,TimeUnit.SECONDS);
                    if(isAdd) {
                        log.info("consumerQueue.offer 成功");
                    }else{
                        log.error("consumerQueue.offer timeout ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private static BlockingQueue commonQueue;
    /**
     * 融资工程excel异步任务队列
     */
    public static ThreadPoolExecutor excelThreadPoolExecutor;
    static {
        commonQueue = new ArrayBlockingQueue(2000);
        ThreadFactory tf = r -> {
            Thread td = new Thread(r);
            td.setName("excelThreadPoolExecutor");
            return td;
        };
        excelThreadPoolExecutor = new ThreadPoolExecutor(10, 10,
                60*3, TimeUnit.SECONDS, commonQueue, tf, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.error("消费队列满 commonQueue :"+commonQueue.size());
            }
        });
    }

    public static void main(String[] args) {

        for(int i=0;i<5;i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("start {}",System.currentTimeMillis());
                        Thread.sleep(2000);
                        log.info("end {}",System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
