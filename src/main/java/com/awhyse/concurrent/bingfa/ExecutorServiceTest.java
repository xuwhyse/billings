package com.awhyse.concurrent.bingfa;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 线程池: 一个进程大约有2000个线程资源。 2.tomcat的线程池是提交任务后不返回，任务进入线程池任务队列
 * 3.dbcp线程池，外部线程需要等待线程池返回处理结果，继续执行
 * @author xumin 2015-7-8 上午10:47:12
 *
 */
public class ExecutorServiceTest {

	//这个实例告诉我们，线程池要设置
	static ExecutorService executorService = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(3);
	/**
	 * xumin  2015-7-8 上午10:47:02
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(6000);
					System.err.println(Thread.currentThread().getId()+" thread over");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		for(int i=0;i<5;i++){
			executorService.execute(runnable);
			System.err.println("接受任务:"+i);
		}
		executorService.shutdown();
//		CallableWaiTest();
		
	}
	/**
	 * 主线程请求执行任务，等待返回后超时，撤销任务，主线程继续
	 * xumin  2015-7-8 上午11:52:13
	 */
	private static void CallableWaiTest() {
		Callable<Integer> task = new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				Thread.currentThread().sleep(6000);
				System.err.println("我获取于远程数据库的连接，执行sql后返回");
				return 1;
			}
		};
		Future<Integer>  res = executorService.submit(task);//其实这个还是直接返回的
		
		int temp;
		try {
			//一般谨慎使用超时，超时一般时间设置很长。超时后就不知道任务执行了没，即使撤销任务也无济于事:对于已经执行了的东西
			temp = res.get(3, TimeUnit.SECONDS);//主线程阻塞是调用res获取状态阻塞的,这个相当重要！！！！！！！！！！！！！
			System.err.println(temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("等待线程返回结果超时，我不等了");
			System.err.println("尝试撤销任务执行"+res.cancel(true));
			e.printStackTrace();
		}
		executorService.shutdown();
	}

}
