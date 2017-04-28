package com.awhyse.concurrent.bingfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by whyse
 * on 2017/4/19 17:14
 */
public class ExecutorServiceLamda {
    static Logger logger = LoggerFactory.getLogger(ExecutorServiceLamda.class);
    public static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        lamdaTest();
    }

    private static void lamdaTest() {
        for(int i=0;i<5;i++){
            int n = i;
            executorService.submit(()->{
                try {
                    lamdaTest2(n);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }

    private static void lamdaTest2(int n) {
        logger.info(n+"_step1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1/0;
        logger.info(n+"_step2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(n+"_step3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
