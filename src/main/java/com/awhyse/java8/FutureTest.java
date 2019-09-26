package com.awhyse.java8;

import com.awhyse.concurrent.bingfa.ExecutorServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by whyse
 * on 2017/2/23 16:47
 */
public class FutureTest {
    static Logger logger = LoggerFactory.getLogger(FutureTest.class);
    public static void main(String[] args) throws Exception {
        Future<?> fut = getFutureInfo();
    }

    private static Future<?> getFutureInfo() throws Exception{
        long time = System.currentTimeMillis();
        Future<?> fut = ExecutorServiceTest.executorService.submit(()->{
            System.err.println("开始执行");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("开始执行");
            return "dsgf";
        });
        System.err.println(System.currentTimeMillis()-time);
        if(fut.get(7, TimeUnit.SECONDS)==null) {
            System.err.println("空");
        }
        System.err.println(System.currentTimeMillis()-time);
        return fut;
    }
}
