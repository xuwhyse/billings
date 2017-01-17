package com.awhyse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonFastUtil {

	/**
	 * @param args
	 * author:xumin 
	 * 2016-8-5 上午11:17:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HashMap<String, Object>  map = new HashMap<String, Object>(3);
//		map.put("volatility", 2.01);//振幅
//		map.put("returnRate", 1.01);//收益率
//		map.put("shortSymbol", "BB");
//		map.put("key", "DCE#BB#2016-08-12");
//		
//		List<Map<String, Object>>  list = new ArrayList<Map<String,Object>>(8);
//		Map<String, Object> mapItem = new HashMap<String, Object>(3);
//		mapItem.put("side", "buy");
//		mapItem.put("price", 12.34);
//		mapItem.put("timestamp", System.currentTimeMillis());
//		list.add(mapItem);
//		
//		mapItem = new HashMap<String, Object>(3);
//		mapItem.put("side", "close");
//		mapItem.put("price", 12.34);
//		mapItem.put("timestamp", System.currentTimeMillis());
//		list.add(mapItem);
//		
//		mapItem = new HashMap<String, Object>(3);
//		mapItem.put("side", "sell");
//		mapItem.put("price", 12.34);
//		mapItem.put("timestamp", System.currentTimeMillis());
//		list.add(mapItem);
//		
//		map.put("actions", list);
//		
//		String str = JSON.toJSONString(map);
//		System.err.println(str);
		
		HashMap<String, Object>  map = new HashMap<String, Object>(3);
		map.put("code", "CZC#FG#2016-11-12");
		
		String str = JSON.toJSONString(map);
		System.err.println(str);
		
//		map = JSON.parseObject(str, map.getClass());
//		System.err.println(map);
	}

}
