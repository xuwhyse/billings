package com.hundsun.internet.billingsystem.common;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;



public class JsonUtil {

	static ObjectMapper objectMapper = new ObjectMapper();
	static{
//		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * @author xunmin
	 * @createTime 上午10:51:45
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Object>  map = new HashMap<String, Object>(3);
		map.put("123", "124");
		map.put("1222", null);
		String str = ObToJson(map);
		System.err.println(str);
	}

	public static Object JsonToOb(String json,Class<?> Ob) {
		Object tar = null;
		try {
			tar = objectMapper.readValue(json, Ob);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
