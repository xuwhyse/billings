package com.awhyse.concurrent.Locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * testTwo是错的，线程串行执行
 * 只需要volatile变量就行=》自旋锁+条件出队列是最快的
 * Created by whyse
 * on 2017/5/11 10:20
 */
public class ThreadSerVoli {
    static Logger logger = LoggerFactory.getLogger(ThreadSerVoli.class);
    static volatile int state = 1;
    static List<String>  list = new ArrayList<>(20);

    public static void main(String[] args) {
        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                while(true){
                    //自旋，直到state==1
                    if(state==1) {
//                        logger.info("A");
                        list.add("A");
                        state = 2;
                        break;
                    }
                }
            }
        };

        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                while(true){
                    //自旋，直到state==1
                    if(state==2) {
//                        logger.info("B");//这样不行，因为线程打印出来的数据不对
                        list.add("B");
                        state = 3;
                        break;
                    }
                }
            }
        };

        Runnable runnableC = new Runnable() {
            @Override
            public void run() {
                while(true){
                    //自旋，直到state==1
                    if(state==3) {
                        list.add("C");
                        state = 1;
                        break;
                    }
                }
            }
        };

        for(int i=0;i<7;i++){
            TestTwoMy.executorService.execute(runnableA);
            TestTwoMy.executorService.execute(runnableB);
            TestTwoMy.executorService.execute(runnableC);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<list.size();i++){
            System.err.print(list.get(i));
        }
    }
}
