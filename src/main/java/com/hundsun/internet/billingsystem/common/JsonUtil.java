package com.hundsun.internet.billingsystem.common;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @author xunmin
	 * @createTime 上午10:51:45
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub  
//		BillingsDetailInfoBean Ob = new BillingsDetailInfoBean();
//		Ob.setAccount("隧道股份");
//		Ob.setTotalMoney(345);
//		BillingsOperBean bill = new BillingsOperBean();
//		bill.setAccount("dgf");
//		bill.setGoodLuckCoinNum(12534);
//		for(int i=0;i<50;i++)
//			Ob.getListWaterBills().add(bill);
//		String str = ObToJson(Ob);
//		System.err.println(str);
//		BillingsDetailInfoBean  tar = (BillingsDetailInfoBean) JsonToOb(str, BillingsDetailInfoBean.class);
//		System.err.println(tar.getListWaterBills().get(0).getAccount());
	}

	public static Object JsonToOb(String json,Class<?> Ob) {
		Object tar = null;
		try {
			tar = objectMapper.readValue(json, Ob);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tar;
	}

	public static String ObToJson(Object ob) {
		String str = null;
		try {
			str = objectMapper.writeValueAsString(ob);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

}
