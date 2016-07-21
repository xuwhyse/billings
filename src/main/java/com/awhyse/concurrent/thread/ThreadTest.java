package com.awhyse.concurrent.thread;

public class ThreadTest {

	/**
	 * @param args
	 * author:xumin 
	 * 2016-6-24 上午10:05:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(1000*20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(run);
		t.setName("111");
		t.start();
		
		
		run = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(1000*50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t = new Thread(run);
		t.setName("111");
		t.start();
	}

}
