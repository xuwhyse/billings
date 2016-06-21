package com.awhyse.concurrent.bingfa;

/**
 * 单例模式，在并发的时候，可能创建了多个实例。
 * 事实上需要双检锁！！！ 同步就是不容易，写好一个简单的要要详细考虑
 * @author xumin 2015-7-9 下午12:03:44
 *
 */
public class InstanceDoubleCheckLock {

	static  InstanceDoubleCheckLock instance = null;
	private  InstanceDoubleCheckLock(){
		
	}
	/**
	 * xumin  2015-7-9 下午12:03:22
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		instance = getInstance1();
		getInstance2();
		getInstanceTrue();
	}
	private static InstanceDoubleCheckLock getInstanceTrue() {
		if(instance==null){
			//每个对象都由一把锁，争夺对象锁的线程阻塞。  没有争夺对象锁的并发
			synchronized (InstanceDoubleCheckLock.class) {
				//再次判断是因为，如果多个线程阻塞在同步那边，那么还会进来创建
				if(instance==null)
					instance = new InstanceDoubleCheckLock();
			}
		}
		return instance;
	}
	/**
	 * 锁粒度打，获取也不支持并发，增大开销
	 * xumin  2015-7-9 下午12:07:34
	 * @return
	 */
	private static synchronized InstanceDoubleCheckLock getInstance2() {
		if(instance==null){
			instance = new InstanceDoubleCheckLock();
		}
		return instance;
	}
	/**
	 * 并发就出问题
	 * xumin  2015-7-9 下午12:07:56
	 * @return
	 */
	private static InstanceDoubleCheckLock getInstance1() {
		if(instance==null){
			instance = new InstanceDoubleCheckLock();
		}
		return instance;
	}

}
