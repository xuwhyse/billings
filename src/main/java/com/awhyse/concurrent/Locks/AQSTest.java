package com.awhyse.concurrent.Locks;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 1、public V get(Object key)不涉及到锁，也就是说获得对象时没有使用锁；

 2、put、remove方法要使用锁，但并不一定有锁争用，原因在于ConcurrentHashMap将缓存的变量分到多个Segment，
 每个Segment上有一个锁，只要多个线程访问的不是一个Segment就没有锁争用，就没有堵塞，各线程用各自的锁，ConcurrentHashMap缺省情况下生成16个Segment，也就是允许16个线程并发的更新而尽量没有锁争用；

 3、Iterator对象的使用，不一定是和其它更新线程同步，获得的对象可能是更新前的对象，
 ConcurrentHashMap允许一边更新、一边遍历，也就是说在Iterator对象遍历的时候，
 ConcurrentHashMap也可以进行remove,put操作，且遍历的数据会随着remove,put操作产出变化，
 所以希望遍历到当前全部数据的话，要么以ConcurrentHashMap变量为锁进行同步(synchronized该变量)，
 要么使用CopiedIterator包装iterator，使其拷贝当前集合的全部数据，但是这样生成的iterator不可以进行remove操作。
 * Created by whyse
 * on 2017/5/10 11:29
 */
public class AQSTest extends AbstractQueuedSynchronizer {

    /**
     * 自定义AQS同步器：自旋锁。实现四个tryAcquire;不用关心失败后线程入队列，park(),阻塞等
     * @param acquires
     * @return
     */
    protected boolean tryAcquire(int acquires) {
        //
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }
        else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0) // overflow
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    /**
     * 经典的死循环(自旋锁,并没放弃CPU)，cas,先获取预先voli值，然后计算预期值
     * 调用四通cas看是否能更新预期值成功
     * @param releases
     * @return
     */
    public boolean tryReleaseShared(int releases) {
//        for (;;) {
//            int c = getState();
//            if (c == 0)
//                return false;
//            int nextc = c-1;
//            if (compareAndSetState(c, nextc))
//                return nextc == 0;
//        }
        while(true){
            int c = getState();
            if (c == 0)
                return false;
            int nextc = c-1;
            if (compareAndSetState(c, nextc))
                return nextc == 0;
        }
    }
}
