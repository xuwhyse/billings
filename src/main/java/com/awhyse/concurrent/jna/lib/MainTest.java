package com.awhyse.concurrent.jna.lib;

import com.awhyse.concurrent.jna.lib.interfacelib.SystemTest;
import com.awhyse.concurrent.jna.lib.interfacelib.ThostFtdcTraderApi;
import com.awhyse.concurrent.jna.lib.struct.CThostFtdcReqAuthenticateField;
import com.sun.jna.Native;

public class MainTest {

	/**
	 * @param args
	 * author:xumin 
	 * 2016-4-15 下午3:42:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(System.getProperty("user.dir"));
//		System.setProperty("jna.protected", "true");
//		System.setProperty("jna.encoding", "UTF8"); //设置jna默认编码
//		Native.setProtected(true);
		//thostmduserapi    thosttraderapi   "msvcrt" 
		ThostFtdcTraderApi myService = (ThostFtdcTraderApi)Native.loadLibrary("thosttraderapi", ThostFtdcTraderApi.class);
		myService = myService.CreateFtdcTraderApi("");
		myService.Init();
		myService.Release();
		CThostFtdcReqAuthenticateField.ByReference pReqAuthenticateField = new CThostFtdcReqAuthenticateField.ByReference();
		int flag = myService.ReqAuthenticate(pReqAuthenticateField, 1);
		
//		systemTest();
		
	}

	private static void systemTest() {
		// TODO Auto-generated method stub
		SystemTest lib = (SystemTest) Native.loadLibrary("msvcrt", SystemTest.class);
		lib.printf("Hello, World");
	}

}
