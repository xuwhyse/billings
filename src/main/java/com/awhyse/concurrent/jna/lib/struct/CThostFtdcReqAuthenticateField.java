package com.awhyse.concurrent.jna.lib.struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
/**
 * 客户端认证请求结构体
 * ByReference
 * author:xumin 
 * 2016-4-15 下午3:24:56
 */
public class CThostFtdcReqAuthenticateField extends Structure{
	//Structure子类中的公共字段的顺序，必须与C语言中的结构的顺序一致，否则会报错！
	//(Rect[])new Rect().toArray(len); 
	///经纪公司代码TThostFtdcBrokerIDType  char[11]
	public	String BrokerID;
	///用户代码TThostFtdcUserIDType  char[16]
	public String UserID;
	///用户端产品信息TThostFtdcProductInfoType  char[11]
	public String UserProductInfo;
	///认证码TThostFtdcAuthCodeType   char[17]
	public String AuthCode;
	
	//输入参数，结构体传指针 需要使用的对象
    public static class ByReference extends CThostFtdcReqAuthenticateField implements Structure.ByReference { }  
    //输入参数，结构体传值  需要使用的对象
    public static class ByValue extends CThostFtdcReqAuthenticateField implements Structure.ByValue{ }
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
