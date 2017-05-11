package com.awhyse.concurrent.Locks;

/**
 * 锁跟线程相关，线程每尝试获取锁，要么wait,要么重入+1
 * 每释放锁，要么出错，要么重入-1，==0时就是锁释放了
 * Created by whyse
 * on 2017/4/28 17:41
 */
public class ReEnterLockMy {
    volatile boolean islocked = false;
    volatile Thread lockTdOwner = null;
    volatile int lockCount = 0;

    //==========================

    /**
     * 将锁资源跟一个线程绑定，可重入+1
     * lock始终是个循环，直到出口位置
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        //synchronized 重量级操作，线程的挂起，唤醒需要内核态操作再返回。  不过jdk1.6后，优化了
        //有自适应自旋锁，偏向所，锁消除等优化
        Thread td = Thread.currentThread();
        while(islocked && lockTdOwner!=null && !lockTdOwner.equals(td)){
            wait();
        }
        islocked = true;
        lockTdOwner = td;
        lockCount++;
    }

    public synchronized void unlock() throws Exception {
        Thread td = Thread.currentThread();
        if(td.equals(lockTdOwner)){
            lockCount--;
            if(lockCount==0){
                islocked = false;
                lockTdOwner = null;

                notifyAll();
            }
        }else{
            throw new Exception();
        }
    }

}
