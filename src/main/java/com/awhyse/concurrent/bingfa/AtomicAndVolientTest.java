package com.awhyse.concurrent.bingfa;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * volatile:费原子性+确保先行关系
 * 
 * Atomic：原子性
 * author:xumin 
 * 2016-6-1 上午10:35:47
 */
public class AtomicAndVolientTest {

	volatile int count =2; //线程间通讯，原则上都要使用这个修饰
	/**
	 * volatile 变量和 atomic 变量看起来很像，但功能却不一样。Volatile变量可以确保先行关系，即写操作会发生
	 * 在后续的读操作之前, 但它并不能保证原子性。例如用volatile修饰count变量那么 count++ 操作就不是原子性的。
	 * 而AtomicInteger类提供的atomic方法可以让这种操作具有原子性如getAndIncrement()方法会原子性的进行增量操作把当前值加一，
	 * 其它数据类型和引用变量也可以进行相似操作。
	 * xumin  2015-7-9 上午11:39:56
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicInteger intA = new AtomicInteger(2);//原子操作， 就是++位于同步快
		intA.incrementAndGet();
		
	}

}
