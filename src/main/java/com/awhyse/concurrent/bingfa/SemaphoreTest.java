package com.awhyse.concurrent.bingfa;

import java.util.concurrent.Semaphore;

/**
 * 专门用于通知流量的
 * Created by whyse
 * on 2017/12/25 13:34
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        if(semaphore.getQueueLength()>0){
            //等待信号量的线程数
            return;
        }
        try{
            semaphore.acquire();//获取一个资源,如果获取不到就blocking
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();//释放一个资源
        }
    }
}
