package com.billings.billingsystem.bean.util;

import java.util.HashMap;
import java.util.Map;

import com.billings.billingsystem.model.ScoreBean;
import com.billings.billingsystem.model.SysAccountBean;



/**
 * 存放全局线程局部变量
 * 每个方法都能从里面获取基本对象，比如用户信息（操作管理员的和被充值用户的信息）
 * @author xunmin
 * @createTime 上午10:00:24
 */
public class GlobalObjects {

	public static String SysAccount = "SysAccountBean";
	/**
	 * 每个线程都会有个map,map第一次创建后就可以循环使用,包括里面的key值等
	 */
	private static ThreadLocal<Map<String, Object>>  mapThreadLocal = new ThreadLocal<Map<String, Object>>();
	/**
	 * 当前被充值账户信息
	 * @author xunmin
	 * @createTime 上午10:05:44
	 * @return
	 */
	public static ScoreBean getCurrentUserScore(){
		return null;
	}
	/**
	 * 当前使用本系统的合作商信息
	 * @author xunmin
	 * @createTime 上午10:05:58
	 * @return
	 */
	public static SysAccountBean getCurrentSysAccount(){
		return (com.billings.billingsystem.model.SysAccountBean) mapThreadLocal.get().get(SysAccount);
	}
	/**
	 * 给当前线程设置map中的对象
	 * @author xunmin
	 * @createTime 上午10:19:27
	 * @param key
	 * @param bean
	 */
	public static void setToMap(String key, Object bean) {
		// 若果没有map就创建
		if(mapThreadLocal.get()==null){
			Map<String, Object> map = new HashMap<String, Object>();
			mapThreadLocal.set(map);
		}
		//不管有没有key值，一概覆盖原来的值
		mapThreadLocal.get().put(key, bean);
	}
}
