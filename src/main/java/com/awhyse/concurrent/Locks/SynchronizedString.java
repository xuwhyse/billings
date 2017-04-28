package com.awhyse.concurrent.Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 同一个对象的方法锁，要讲是静态方法（类锁）还有成员方法（对象锁），持有的锁不一样分析
 * 
 * author:xumin 
 * 2016-6-1 上午10:00:23
 */
public class SynchronizedString {

	static ExecutorService executorService = Executors.newFixedThreadPool(3);
	int sig = 0;
	/**
	 * 实验证明synchronized(Key) 能做到账号锁
	 * @param args
	 * author:xumin 
	 * 2016-8-1 下午8:28:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		synStringTest();
		
		//以下证明，类锁一加，所有对象锁都不能获取。反之，类锁要等到所有对象锁释放才行
		SynchronizedString item = new SynchronizedString();
		item.synNorTest();
		staticSync();
		
	}
	private synchronized void synNorTest() {
		System.err.println("normal");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private synchronized static void staticSync() {
		System.err.println("static");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void synStringTest() {
		// TODO Auto-generated method stub
		final String key = "123";
		final String key1 = "123";
		String key2 = "123";
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				synchronized(key){
					System.err.println("key");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Runnable runnable2 = new Runnable() {
			
			@Override
			public void run() {
				synchronized(key1){
					System.err.println("key1");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		//以下充分显示，竞争同一个锁就顺序执行，静态的类所跟对象锁不一样
		executorService.submit(runnable);
		executorService.submit(runnable2);
		
		executorService.shutdown();
	}

}
