package com.awhyse.concurrent.jna.lib.struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
/**
 * 客户端认证请求结构体
 * author:xumin 
 * 2016-4-15 下午3:24:56
 */
public class CThostFtdcReqUserLoginField extends Structure{
	///交易日  TThostFtdcDateType  9
		public String	TradingDay;
		///经纪公司代码 TThostFtdcBrokerIDType
		public String	BrokerID;
		///用户代码 TThostFtdcUserIDType
		public String	UserID;
		///密码 TThostFtdcPasswordType
		public String	Password;
		///用户端产品信息 TThostFtdcProductInfoType
		public String	UserProductInfo;
		///接口端产品信息 TThostFtdcProductInfoType
		public String	InterfaceProductInfo;
		///协议信息 TThostFtdcProtocolInfoType
		public String	ProtocolInfo;
		///Mac地址 TThostFtdcMacAddressType
		public String	MacAddress;
		///动态密码 TThostFtdcPasswordType
		public String	OneTimePassword;
		///终端IP地址 TThostFtdcIPAddressType
		public String	ClientIPAddress;
	
	//输入参数，结构体传指针 需要使用的对象
    public static class ByReference extends CThostFtdcReqUserLoginField implements Structure.ByReference { }  
    //输入参数，结构体传值  需要使用的对象
    public static class ByValue extends CThostFtdcReqUserLoginField implements Structure.ByValue{ }
    /*
     * 参考
     * Rect.ByReference pRect = new Rect.ByReference(); //指针对象  
		Rect.ByValue rect =  new Rect.ByValue(); //传值对象  
		function3(pRect, rect);  
     */
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList(new String[]{"BrokerID", "UserID", "UserProductInfo", "AuthCode"});  
	}


}
