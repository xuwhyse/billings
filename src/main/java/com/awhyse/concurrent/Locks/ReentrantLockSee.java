package com.awhyse.concurrent.Locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by whyse
 * on 2017/12/5 17:38
 */
public class ReentrantLockSee {
    static Logger logger = LoggerFactory.getLogger(ReentrantLockSee.class);

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();//
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("1111");
                    reentrantLock.lock();
                    logger.info("sleep 10 s");
                    Thread.sleep(20*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        };
        Thread td1 = new Thread(run1);
        td1.setName("t1");
        td1.start();

        //==============================================
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("pre work");
                    reentrantLock.lock();
                    logger.info("do work");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
            }
        };
        Thread td2 = new Thread(run2);
        td2.setName("t2");
        td2.start();
    }
}
