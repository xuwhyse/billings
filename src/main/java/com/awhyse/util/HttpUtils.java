package com.awhyse.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

	private static final MediaType MEDIA_TYPE_JSON = MediaType
			.parse("application/x-www-form-urlencoded; charset=utf-8");// mdiatype
																		// 这个需要和服务端保持一致
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");// mdiatype 这个需要和服务端保持一致
	public static final MediaType JSONType = MediaType
			.parse("application/json; charset=utf-8");
	private static final String TAG = HttpUtils.class.getSimpleName();
	private static final String BASE_URL = "http://xxx.com/openapi";// 请求接口根地址
	private static volatile HttpUtils mInstance;// 单利引用
	public static final int TYPE_GET = 0;// get请求
	public static final int TYPE_POST_JSON = 1;// post请求参数为json
	public static final int TYPE_POST_FORM = 2;// post请求参数为表单
	private OkHttpClient mOkHttpClient;// okHttpClient 实例
//	private Handler okHttpHandler;// 全局处理子线程和M主线程通信

	/**
	 * @param args
	 *            author:xumin 2016-12-16 上午11:44:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> mapTar = new HashMap<String, Object>(8);
		mapTar.put("returnRate", 0.0);
		mapTar.put("volatility", 0);
		mapTar.put("market", "FC");
		mapTar.put("fdtId", "test");
		
		mapTar.put("exchangeCode", "DCE");
		mapTar.put("symbol", "A");
		mapTar.put("key", "DCE#A#2016-05-19");
		
		List<Map<String, Object>>  actions = new ArrayList<Map<String,Object>>(2);
		mapTar.put("actions", actions);
		
		Map<String, Object> map = new HashMap<String, Object>(3);
		map.put("timestamp", 1482912911901l);
		map.put("side", "buy");
		map.put("price", 878.5);
		
//		actions.add(map);
		
		
		
		String str = JSON.toJSONString(mapTar);
//		str = URLEncoder.encode(str);
		System.err.println(str);
		
		String url = "http://localhost:8880/";
//		String retStr =  NetUtil.sendPost("http://localhost:8080/im/xiaotDoScore", str);
		postJson(url, str);
	}
	/**
	 * 
	 * @param url  "http://192.168.0.102:8080/TestProject/JsonServlet"
	 * @param json
	 * author:xumin 
	 * 2016-12-16 下午1:32:15
	 */
	public static String postJson(String url,String json) {
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().
				connectTimeout(5, TimeUnit.SECONDS)
				.readTimeout(5,TimeUnit.SECONDS)
				.build();
		// 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		RequestBody requestBody = RequestBody.create(JSONType, json);
		// 创建一个请求对象
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody).build();
		// 发送请求获取响应
		try {
			Response response = okHttpClient.newCall(request).execute();
			// 判断请求是否成功
			if (response.isSuccessful()) {
				// 打印服务端返回结果
				String str = response.body().string();
				System.err.println(str);
				return str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
