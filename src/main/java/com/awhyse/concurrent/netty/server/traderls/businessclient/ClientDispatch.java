package com.awhyse.concurrent.netty.server.traderls.businessclient;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.math.RandomUtils;

import com.awhyse.concurrent.netty.server.traderls.types.TraderMsgType;


import io.netty.channel.ChannelHandlerContext;

/**
 * 处理原始数据，并且负责分发从服务端收到的消息
 * 
 * author:xumin 
 * 2016-10-18 上午10:38:53
 */
public class ClientDispatch {

	//业务主线程池
	public static ExecutorService bsExecutorService = Executors.newCachedThreadPool();
	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-14 上午11:33:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++)
			System.err.println((RandomUtils.nextInt(6)+1)*1000);
	}
	/**
	 * 这之前都是在io线程里面，io线程职责组装完整数据
	 * 其他交给业务线程池比较好
	 * @param ctx
	 * @param map
	 * @param msgType
	 * author:xumin 
	 * 2016-10-14 下午1:58:59
	 */
	public static void decodeAndDispatch(ChannelHandlerContext ctx,
			HashMap<String, Object> map, int msgType) {
		// TODO Auto-generated method stub
		//拿到完整数据包后直接开线程去做
		switch (msgType) {
		case TraderMsgType.Login:
			
			break;
		case TraderMsgType.Logoff:
			
			break;
		default:
			System.err.println("收到未知包："+map);
			break;
		}
	}
}
