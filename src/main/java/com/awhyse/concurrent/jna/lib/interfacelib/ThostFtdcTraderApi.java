package com.awhyse.concurrent.jna.lib.interfacelib;

import com.awhyse.concurrent.jna.lib.struct.*;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * 
 * 
 * author:xumin 2016-4-15 下午1:49:07
 */
public interface ThostFtdcTraderApi extends Library {
	///创建TraderApi
	///@param pszFlowPath 存贮订阅信息文件的目录，默认为当前目录
	///@return 创建出的UserApi
	public ThostFtdcTraderApi CreateFtdcTraderApi(final String pszFlowPath);
		
	// /删除接口对象本身
	// /@remark 不再使用本接口对象时,调用该函数删除接口对象
	public void Release();

	// /初始化
	// /@remark 初始化运行环境,只有调用后,接口才开始工作
	public void Init();

	// /等待接口线程结束运行
	// /@return 线程退出代码
	public int Join();

	// /客户端认证请求
	public int ReqAuthenticate(
			CThostFtdcReqAuthenticateField.ByReference pReqAuthenticateField,
			int nRequestID);

	// /用户登录请求
	// int ReqUserLogin(CThostFtdcReqUserLoginField pReqUserLoginField, int
	// nRequestID);

	// /登出请求
	// int ReqUserLogout(CThostFtdcUserLogoutField pUserLogout, int nRequestID);
}
