package com.awhyse.concurrent.Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 同一个对象的方法锁，要讲是静态方法（类锁）还有成员方法（对象锁），持有的锁不一样分析
 * 
 * author:xumin 
 * 2016-6-1 上午10:00:23
 */
public class SynchronizedTest {

	static ExecutorService executorService = Executors.newFixedThreadPool(3);
	int sig = 0;
	/**
	 * @param args
	 * author:xumin 
	 * 2016-6-1 上午9:56:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		synchronized("123"){
			
		}
		final SynchronizedTest ob = new SynchronizedTest();
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				ob.test1();
			}
		};
		Runnable runnable2 = new Runnable() {
			
			@Override
			public void run() {
				SynchronizedTest.testStatic1();
			}
		};
		Runnable runnable3 = new Runnable() {
			
			@Override
			public void run() {
				ob.test2();
			}
		};
		//以下充分显示，竞争同一个锁就顺序执行，静态的类所跟对象锁不一样
		executorService.submit(runnable);
		executorService.submit(runnable2);
		executorService.submit(runnable3);
		
		executorService.shutdown();
	}
	public static synchronized void testStatic1(){
		System.err.println("testStatic1");
	}
	public synchronized void test1(){
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("test1");
	}
	public static synchronized void testStatic2(){
		System.err.println("testStatic2");
	}
	public synchronized void test2(){
		System.err.println("test2");
	}

}
