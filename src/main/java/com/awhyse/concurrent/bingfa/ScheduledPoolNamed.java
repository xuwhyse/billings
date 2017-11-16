package com.awhyse.concurrent.bingfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 可疑给线程池命名
 * Created by whyse
 * on 2017/8/30 10:12
 */
public class ScheduledPoolNamed {
    static Logger logger = LoggerFactory.getLogger(ScheduledPoolNamed.class);
    public static void main(String[] args) {
        startDelThreadWork();
        logger.info("startDelThreadWork: ");
    }
    private static void startDelThreadWork() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    long now = System.currentTimeMillis();
                    //检查持续了多久
                    logger.info("startDelThreadWork: " + (System.currentTimeMillis() - now));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread rd = new Thread(r);
                rd.setName("pool_haha_td_1");
                return rd;
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(threadFactory);
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 5, 2, TimeUnit.SECONDS);
    }
}
