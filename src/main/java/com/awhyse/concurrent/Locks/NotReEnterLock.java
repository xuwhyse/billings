package com.awhyse.concurrent.Locks;

/**
 * 不可重入锁，跟线程无关
 * 同一个线程也不能再获取锁，而且锁可以被其他线程释放
 * Created by whyse
 * on 2017/4/28 17:38
 */
public class NotReEnterLock {
    private boolean isLocked = false;
//    volatile Thread lockTdOwner = null;//


    public synchronized void lock() throws InterruptedException{
//        Thread td = Thread.currentThread();
         while(isLocked){
             //一定要用while,这样wait激活后，还能进入条件判断，进入lock
            wait();//阻塞，释放cpu资源，可以被唤醒
        }
        isLocked = true;
    }
    public synchronized void unlock(){
        isLocked = false;
        notifyAll();
   }
}
