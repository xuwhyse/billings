package com.awhyse.concurrent.bingfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by whyse
 * on 2017/6/19 14:11
 */
public class ThreadPoolExecutorTest {
    static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
            200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(7), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            int size = executor.getQueue().size();
            logger.info("超出队列长度:"+size);
        }
    });

    //==================================================
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("work");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for(int i=0;i<20;i++)
            executor.execute(runnable);
    }
}
