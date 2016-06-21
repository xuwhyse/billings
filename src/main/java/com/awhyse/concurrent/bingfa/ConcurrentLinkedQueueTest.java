package com.awhyse.concurrent.bingfa;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 秒杀，并发插入大的首选
 * Queue中元素按FIFO原则进行排序．采用CAS操作，来保证元素的一致性。
 * 并行和并发区别:
1、并行是指两者同时执行一件事，比如赛跑，两个人都在不停的往前跑；
2、并发是指资源有限的情况下，两者交替轮流使用资源，比如一段路(单核CPU资源)同时只能过一个人，A走一段后，让给B，B用完继续给A ，交替使用，目的是提高效率
 * @author xumin 2015-7-7 下午5:39:45
 *
 */
public class ConcurrentLinkedQueueTest {

	//这个是实现了无锁的安全队列，单个操作都是安全的，但组合就不一定安全了
	private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	/**
	 * xumin  2015-7-7 下午5:39:32
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(queue.isEmpty());
		queue.offer(1);
		System.err.println(queue.isEmpty());
		queue.poll();
		System.err.println(queue.isEmpty());
	}

}
