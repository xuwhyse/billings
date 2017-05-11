package com.awhyse.concurrent.Locks;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by whyse
 * on 2017/4/28 18:04
 */
public class ReEnterLockCAS {
    Unsafe unsafe; //Unsafe.getUnsafe();//直接使用会报错;;java.lang.SecurityException: Unsafe
    volatile long lockStats = 0;
    long stateOffset;//这个是地址

    volatile Thread lockTdOwner = null;
    volatile int lockCount = 0;
    static int count = 0;

    public ReEnterLockCAS(){
        try {
            //要反射获取
            unsafe = getUnsafeInstance();
            //获取state的地址，unsafe要用
            stateOffset = unsafe.objectFieldOffset(ReEnterLockCAS.class.getDeclaredField("lockStats"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------
    public static void main(String[] args) {
        ReEnterLockCAS reEnterLockCAS = new ReEnterLockCAS();
        Runnable runnableNon = new Runnable() {
            @Override
            public void run() {
                count++;
            }
        };
        Runnable runnableLock = new Runnable() {
            @Override
            public void run() {
                try {
                    reEnterLockCAS.lock();
                    count++;
                } catch (Exception e){

                } finally{
                    reEnterLockCAS.unlock();
                }
            }
        };

        for(int i=0;i<100000;i++){
            TestTwoMy.executorService.execute(runnableLock);//runnableLock;runnableNon
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.print(count);
    }
    //------------------------------------------------

    /**
     * 这是一个可重入锁
     * @throws InterruptedException
     */
    public void lock() throws InterruptedException {
        //一定得死循环，这个锁是自旋+部分互斥锁
        Thread td = Thread.currentThread();
        while(true) {
            if ((lockTdOwner!=null && td.equals(lockTdOwner))
                    || unsafe.compareAndSwapInt(this, stateOffset, 0, 1)) {
                //获取了锁
                lockTdOwner = td;
                lockCount++;
                return;
            } else {
                //除非AQS调用，线程入队列，pack();
//                wait();//这种操作一定得是sysnc里面的
            }
        }
    }
    public void unlock() {
        Thread td = Thread.currentThread();
        if(lockTdOwner!=null && td.equals(lockTdOwner)){
            lockCount--;
            if(lockCount==0){
                lockTdOwner = null;
                lockStats = 0;
//                notifyAll();
            }
        }
    }




    //===================================
    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
