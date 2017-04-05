package com.awhyse.concurrent.Locks;

import com.awhyse.concurrent.bingfa.ExecutorServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用条件锁的例子。 多线程做任务，等待某个条件成熟
 * lock,unlock,await 都可以占用或者释放锁
 * Created by whyse
 * on 2017/2/13 13:44
 */
public class ConditonsTest {
    static Logger logger = LoggerFactory.getLogger(ConditonsTest.class);

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ReentrantLock lock = new ReentrantLock();
        Condition catEnd = lock.newCondition();
        ExecutorServiceTest.executorService.submit(()->{
            try {
                Thread.sleep(2000);
                lock.lock();
                try {
                    ai.incrementAndGet();
                    catEnd.signal();
                }finally {
                    logger.info("1:释放锁，增加1");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ExecutorServiceTest.executorService.submit(()->{
            try {
                Thread.sleep(5000);
                lock.lock();
                try {
                    ai.incrementAndGet();
                    catEnd.signal();
                }finally {
                    logger.info("2:释放锁，增加1");
                    lock.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while(ai.get()<2){
            lock.lock();//IllegalMonitorStateException ,想使用锁条件，首先得获得锁使用权
            try {
                try {
                    logger.info("判断条件成熟一次");
                    catEnd.await();//放弃锁的使用权并且阻塞。  没有锁lock就没有获取，就不能调用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                logger.info("0:释放锁，判断一次");
                lock.unlock();
            }
        }
        logger.info("条件成熟了！__"+ai.get());
    }
}
