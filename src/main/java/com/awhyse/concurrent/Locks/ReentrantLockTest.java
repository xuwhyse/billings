package com.awhyse.concurrent.Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 1.理解java中断:  一个线程正在运行时（包括阻塞），被另外一个线程通知中断（就是被设置了中断标示为true）。该线程知道要中断了，合适时间检测到，
 * 并且按照自己的中断逻辑进行线程的中断。并不是一下子就被另外的线程给干掉了.
 * 
 * 2.理解死锁: 对象需要执行一个事情比如转账，需要同时获得两把以上的锁。在并发的情况下不做任何处理，1线程获取A等待B锁 2线程获取B等待A锁
 * 1,2都不会自我中断，都不会释放资源-》 死锁。 线程废了。 ->线程要有超时，自我中断+获取锁要有顺序（一次性获取所有资源，不要获取一半，获取锁要有顺序）
 * 
 * 3.线程并发争夺锁的时候有三种情况: 1.没有就阻塞 2.没有就返回(非阻塞) 3.没有就等一段时间，然后返回
 * 4.阻塞唤醒机制，有两种: 一种是盲目唤醒， 一种是唤醒指定线程
 * 
 * 竞态条件 & 临界区
	当两个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。导致竞态条件发生的代码区称作临界区。
	上例中add()方法就是一个临界区,它会产生竞态条件。在临界区中使用适当的同步就可以避免竞态条件。
 */
/**
 * 1.某个线程在等待一个锁的控制权的这段时间需要中断 
2.需要分开处理一些wait-notify，ReentrantLock里面的Condition应用，能够控制notify哪个线程 
3.具有公平锁功能，每个到来的线程都将排队等候 
 * @author xumin 2015-7-7 上午11:18:07
 */
public class ReentrantLockTest {
	Object lock;
	static ReentrantLock reentrantLock = new ReentrantLock();//
	{
		Condition condition  = reentrantLock.newCondition();
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);//线程池，需要介绍APIexecute方法的阻塞情况
		Runnable p1 = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println(Thread.currentThread().getId()+"线程开始睡");
					Thread.currentThread().sleep(7000);
					System.err.println(Thread.currentThread().getId()+"线程睡了5秒，醒了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		
		Runnable p3 = new Runnable() {
			//这个就是允许别人中断，感觉2才是最实用的
			@Override
			public void run() {
				try {
					System.err.println(Thread.currentThread().getId()+"线程申请可被打断的锁，并阻塞");
					reentrantLock.lockInterruptibly();
					try{
						
					}finally{
						reentrantLock.unlock();
					}
				} catch (InterruptedException e) {
					System.err.println(Thread.currentThread().getId()+"线程被打断了!");
					e.printStackTrace();
				}
			}
		};
		final Thread t3 = new Thread(p3);
		Runnable p2 = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(1000);
					if(reentrantLock.tryLock(2, TimeUnit.SECONDS)){
						//2秒内如果能得到锁就执行
						try{
							//这个是在这个时间段内阻塞
							System.err.println(Thread.currentThread().getId()+"线程尝试获取成功!");
						}finally{
							reentrantLock.unlock();
						}
					}else{
						System.err.println(Thread.currentThread().getId()+"线程已经超时返回!");
						t3.interrupt();//超市后打断
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		
		//=================================
		executorService.execute(p1);
		executorService.execute(p2);
		t3.start();
		System.err.println("等待线程池关闭");
//		executorService.shutdownNow();//这个是正在执行的也停止
		executorService.shutdown();//这个是在提交任务前的任务执行完之后就停止
	}
	//普通synchronized的劣势
	public void prouce(){
		synchronized (lock){
			//同步方法块，，区别于同步方法;这种都是要死等下去的，别的线程interrupt（）这个线程也不响应.
		}
	}
}
