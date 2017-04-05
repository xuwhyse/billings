package com.awhyse.concurrent.bingfa.Ali;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by whyse
 * on 2017/3/10 22:30
 */
public class TestTwoMy {
    static ExecutorService executorService = Executors.newFixedThreadPool(3);
    static volatile  int  order = 1;
    static volatile  int  index = 0;
    public static void main(String[] args) {
        int threadNum = 3;
        getStringAli(threadNum);
    }

    /**
     * 题目2：有3个线程和1个公共的字符数组。线程1的功能就是向数组输出A，线程2的
     * 功能就是向字符输出l，线程3的功能就是向数组输出i。要求按顺序向数组赋值AliAliAli
     * ，Ali的个数由线程函数1的参数指定。
     * @param threadNum
     * @return
     */
    private static void getStringAli(int threadNum) {
        final int size = 3*threadNum;
        final byte[] byteAli = new byte[3*threadNum];
//        final TestTwo testTwo = new TestTwo();
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condAHasOver  = reentrantLock.newCondition();
        final Condition condBHasOver  = reentrantLock.newCondition();
        final Condition condCHasOver  = reentrantLock.newCondition();


        final Runnable runA = new Runnable() {
            public void run() {
                while(index<size) {
                    reentrantLock.lock();//lock是阻塞的
                        try {
                            while (order != 1) {
//                                condAHasOver.signal();
                                System.out.println("A获得锁释放A");
                                condCHasOver.await();
                            }
                            if(index>=size){
                                break;
                            }
                            System.out.print("a");
                            byteAli[index++] = 'A';
                            order = 2;
//                            testTwo.notifyAll();
                            condAHasOver.signal();
//                            System.out.println(index.get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            reentrantLock.unlock();
                        }
                    }
                }
        };

        //============================
        final Runnable runl = new Runnable() {

            public void run() {
                while(index<size) {
                    reentrantLock.lock();//lock是阻塞的
                        try {
                            while (order != 2) {
//                                testTwo.wait();
//                                condBHasOver.signal();
                                System.out.println("B获得锁释放B");
                                condAHasOver.await();
                            }
                            if(index>=size){
                                break;
                            }
                            System.out.print("l");
                            byteAli[index++] = 'l';
                            order = 3;
//                            testTwo.notifyAll();
                            condBHasOver.signal();
//                            System.out.println(index.get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            reentrantLock.unlock();
                        }
                }
            }
        };
        //==========================================
        final Runnable runi = new Runnable() {
            public void run() {
                while(index<size) {
                    reentrantLock.lock();//lock是阻塞的
                        try {
                            while (order != 3) {
//                                testTwo.wait();
//                                condCHasOver.signal();
                                System.out.println("C获得锁释放C");
                                condBHasOver.await();
                            }
                            if(index>=size){
                                break;
                            }
                            System.out.print("i");
                            byteAli[index++] = 'i';
                            order = 1;
//                            testTwo.notifyAll();
                            condCHasOver.signal();
//                            System.out.println(index.get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            reentrantLock.unlock();
                        }
                }
            }
        };
        executorService.submit(runA);
        executorService.submit(runl);
        executorService.submit(runi);

    }
}
