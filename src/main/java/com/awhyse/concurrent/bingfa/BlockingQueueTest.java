package com.awhyse.concurrent.bingfa;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.billings.billingsystem.bean.util.UserBean;

/*
 * ArrayBlockingQueue和LinkedBlockingQueue： 后者生产和消费是锁分离。 前者因为开销小，用了同一个锁。 前者消费生产不开销，后者会开销对象
 * 其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，
 * take方法在队列空的时候会阻塞，直到有队列成员被放进来。
 */
/**
 * BlockingQueue  LinkedBlockingQueue，int.max-> 受制于初始化大小。  最佳生产者，消费者模型实现。 
 * 线程没法生产或者消费时会阻塞。  唤醒？ 是否无序
 * @author hzxumin15
 * 2015-4-8下午4:25:58
 */
public class BlockingQueueTest {

	//这个有些操作是阻塞，有些操作是超时后返回
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		simpleTest();
		springMVCMNTest();
	}
	/**
	 * 反射方法+对象传入
	 * author:xumin 
	 * 2016-6-15 下午1:51:46
	 */
	private static void springMVCMNTest() {
		UserBean user = new UserBean();
		user.setCnname("123");
		Method[]  methods = UserBean.class.getMethods();
		Map<String, Method>  map = new HashMap<String, Method>(methods.length);
		for(Method item : methods){
			String name = item.getName();
			map.put(name, item);
		}
		try {
			// method_name+Object[] args + 对象
			Method m1 = map.get("getCnname");
			Object[] args = null;
			System.err.println(m1.invoke(user, args));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//==============================================================
		final BlockingQueue<Integer> blockingQueueShare = new LinkedBlockingQueue<Integer>(2);
		Thread prodThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				while()
				for(int i=0;i<14;i++){
					try {
						System.err.println("add："+(i+1)+"  size"+blockingQueueShare.size());
						blockingQueueShare.offer(i+1);//如果满了，会发生notFull阻塞；成功发出notEmpty信号，通知来消费
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread consThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				while(true){
					try {
						//将会在take处等待，直到等待队列非空条件收到信息
						System.err.println(i++);
						System.err.println("xiaofei："+blockingQueueShare.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		prodThread.setName("prodThread");
		prodThread.start();
		consThread.setName("consThread");
		consThread.start();
	}
	/**
	 * 生产者不停放入队列，直到满，reenterLock发出等待队列空的条件信号阻塞
	 * 消费者不停消费，直到发出等待队列有条件信号，阻塞
	 * 
	 * author:xumin 
	 * 2016-6-15 上午11:27:17
	 */
	private static void simpleTest() {
		final BlockingQueue<Integer> blockingQueueShare = new LinkedBlockingQueue<Integer>(2);
		Thread prodThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				while()
				for(int i=0;i<14;i++){
					try {
						System.err.println("add："+(i+1)+"  size"+blockingQueueShare.size());
						blockingQueueShare.put(i+1);//如果满了，会发生notFull阻塞；成功发出notEmpty信号，通知来消费
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread consThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						//将会在take处等待，直到等待队列非空条件收到信息
						System.err.println("xiaofei："+blockingQueueShare.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		prodThread.setName("prodThread");
		prodThread.start();
		consThread.setName("consThread");
		consThread.start();
	}

}
