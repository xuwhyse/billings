package com.awhyse.util;

import java.util.HashMap;

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
		HashMap<String, Object>  map = new HashMap<String, Object>(3);
		map.put("123", "124");
		map.put("1222", null);
		String str = JSON.toJSONString(map);
		System.err.println(str);
		str = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
		System.err.println(str);
		map = JSON.parseObject(str, map.getClass());
		System.err.println(map);
	}

}
