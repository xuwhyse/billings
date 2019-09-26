package com.awhyse.l2019;

import com.awhyse.concurrent.bingfa.ThreadPoolExecutorTest;
import com.billings.billingsystem.model.BaoyueBean;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * https://juejin.im/post/5ca47aa0e51d457131257269
 * 异步方法
 * @author xumin
 * @date 2019-04-22 18:34
 */

public class CompletableFutureTest {

    public static void main(String[] args) {
        BaoyueBean user = new BaoyueBean();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
          String s = timeLong("1"); user.setSourceuuid(s);return "sd";}, ThreadPoolExecutorTest.executor);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
            String s =  timeLong("2");user.setCreatetime(111L);});

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2);

        try {
            System.out.println(user.toString());
            combinedFuture.get();
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String timeLong(String s) {
        try {
            int i = new Random().nextInt()%10*1000;
            System.err.println(i+Thread.currentThread().getName());
            Thread.sleep(Math.abs(i));
            return s;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
