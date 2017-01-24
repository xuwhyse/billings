package com.awhyse.concurrent.netty.traderls.businessclient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;



public class ClientMngServiceImpl {

	private static ReentrantLock newTradeServiceLock;
	private static Map<String, ClientServiceImpl> mapTradeService = new ConcurrentHashMap<String, ClientServiceImpl>(500);

	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-14 下午2:16:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientMngServiceImpl.sycNewTraderClient();
	}

	public static void sycNewTraderClient() {
		// TODO Auto-generated method stub
		String key = "123";
		try {
			if(newTradeServiceLock.tryLock(5, TimeUnit.SECONDS)){
				try{
					if(mapTradeService.containsKey(key)){
						//客户端发起重连,但是服务端没有断开
					}
				}finally{
					newTradeServiceLock.unlock();
				}
			}
			else{
				System.err.println("线程已经超时返回!  key : "+key);
			}
		} catch (InterruptedException e) {
		}
	}

}
