package com.awhyse.concurrent.bingfa;

import java.util.concurrent.CountDownLatch;


/**
 * 1. 主线程下令开始工作
 * 2. 多个线程完成后，才通知主线程继续工作
 * 线程间条件完成后通信
 * Created by whyse
 * on 2018/9/10 下午3:48
 */
public class CountDownLatchTest {
    static CountDownLatch countDownLatchStart = new CountDownLatch(1);
    static CountDownLatch countDownLatchEnd = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        testCount();
    }

    private static void testCount() throws InterruptedException {

        for (int i = 0; i < 3; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("worker waiting......");
                        countDownLatchStart.await();//等待条件满足
                        System.out.println("worker done......");
                        countDownLatchEnd.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(1000);

        System.out.println("用于触发处于等待状态的线程开始工作......");
        System.out.println("用于触发处于等待状态的线程工作完成，等待状态线程开始工作......");
        countDownLatchStart.countDown();
        countDownLatchEnd.await();
        System.out.println("Bingo!");

    }
}
