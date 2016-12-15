package com.billings.billingsystem.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		map.put("volatility", 2.01);//振幅
		map.put("returnRate", 1.01);//收益率
		map.put("shortSymbol", "BB");
		map.put("key", "DCE#BB#2016-08-12");
		
		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>(8);
		Map<String, Object> mapItem = new HashMap<String, Object>(3);
		mapItem.put("side", "buy");
		mapItem.put("price", 12.34);
		mapItem.put("timestamp", System.currentTimeMillis());
		list.add(mapItem);
		
		mapItem = new HashMap<String, Object>(3);
		mapItem.put("side", "close");
		mapItem.put("price", 12.34);
		mapItem.put("timestamp", System.currentTimeMillis());
		list.add(mapItem);
		
		mapItem = new HashMap<String, Object>(3);
		mapItem.put("side", "sell");
		mapItem.put("price", 12.34);
		mapItem.put("timestamp", System.currentTimeMillis());
		list.add(mapItem);
		
		map.put("actions", list);
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
