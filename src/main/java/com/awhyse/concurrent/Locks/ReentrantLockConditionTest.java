package com.awhyse.concurrent.Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 1.理解java中断:  一个线程正在运行时（包括阻塞），被另外一个线程通知中断（就是被设置了中断标示为true）。该线程知道要中断了，合适时间检测到，
 * 并且按照自己的中断逻辑进行线程的中断。并不是一下子就被另外的线程给干掉了.
 * 
 * 2.理解死锁: 至少两个线程，至少竞争两个以上资源.对象需要执行一个事情比如转账，需要同时获得两把以上的锁。在并发的情况下不做任何处理，1线程获取A等待B锁 2线程获取B等待A锁
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
public class ReentrantLockConditionTest {
	Object lock;
	static ExecutorService executorService = Executors.newFixedThreadPool(5);
	static boolean isAOver = false;
	static boolean isBOver = false;

	public static void main(String[] args) {
		conditionTestNotifySm();
//		reentrantLockReEnterTest();
//		joinThread3();
		
	}
	/**
	 * 让3个线程串行执行
	 * 
	 * author:xumin 
	 * 2016-6-30 下午5:53:02
	 */
	private static void joinThread3() {
		// TODO Auto-generated method stub
		//1.思路，公平锁，保证请求顺序
		final ReentrantLock reentrantLock = new ReentrantLock(true);//公平锁
		final Runnable A = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println("C OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		final Runnable B = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println("C OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		final Runnable C = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println("C OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
	}
	/**
	 * 这个就是可重入的意思
	 * 线程里面套线程,实现串行化
	 * 借助一个可重入锁，线程里套线程
	 * author:xumin 
	 * 2016-6-2 下午2:40:18
	 */
	private static void reentrantLockReEnterTest() {
		final ReentrantLock reentrantLock = new ReentrantLock();
		
		final Runnable C = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println("C OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		
		final Runnable B = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					executorService.submit(C);
					System.err.println("B OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		
		Runnable A = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					executorService.submit(B);
					System.err.println("A OVER");
				}finally{
					reentrantLock.unlock();
				}
			}
		};
		
		
		
		executorService.submit(A);
//		executorService.submit(B);
//		executorService.submit(C);
//		executorService.shutdown();
	}
	/**
	 * 这个是生产者和消费者的实现模型
	 * 对于线程而言:1.获取锁，判断条件成熟没，没有就等待条件，释放锁
	 * 2.获取锁，条件成熟，工作，通知其他线程我已经工作了，释放锁
	 * author:xumin 
	 * 2016-6-2 下午1:55:16
	 */
	private static void conditionTestNotifySm() {
		// TODO Auto-generated method stub
		final ReentrantLock reentrantLock = new ReentrantLock();//创建公平锁
		
		final Condition condAHasOver  = reentrantLock.newCondition();
		final Condition condBHasOver  = reentrantLock.newCondition();
		
		Runnable A = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				while(!isAOver){
					reentrantLock.lock();//lock是阻塞的
					try{
						System.err.println("123");
						isAOver =true;
						condAHasOver.signal();//这句话只告诉reentrantLock.lock()处的某个因为condAHasOver.wait()的线程可以执行了
						System.err.println("condAHasOver");
					}finally{
						reentrantLock.unlock();
					}
				}
			}
		};
		Runnable B = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				while(!isBOver){
					reentrantLock.lock();//lock是阻塞的
					try{
						System.err.println("B获得锁并等待A");
						condAHasOver.await();//线程释放锁
						System.err.println("678");
						isBOver = true;
						condBHasOver.signal();
					}catch(Exception e){
						
					}
					finally{
						reentrantLock.unlock();
					}
				}
				System.err.println("B结束");
			}
		};
		
		Runnable C = new Runnable() {
			//跟sy那个关键字没啥区别,死等
			@Override
			public void run() {
				reentrantLock.lock();//lock是阻塞的
				try{
					System.err.println("c获得锁并等待B");
						condBHasOver.await();//执行finally了
						System.err.println("9 10 11");
				}catch(Exception e){
					
				}
				finally{
					reentrantLock.unlock();
				}
			}
		};
		for(int i=0;i<3;i++){
			executorService.submit(C);
			executorService.submit(B);
			executorService.submit(A);
		}
		executorService.shutdown();
	}
}
