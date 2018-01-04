package com.awhyse.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
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
//		mapTar.put("cmd", "c=history('002415','2017-11-14');return c");
		mapTar.put("cmd", "a=15\n" +
				"if(a>10):\n" +
				"    a=a-8\n" +
				"else:\n" +
				"    a=a-4\n" +
				"return a");
		
//		String str = JSON.toJSONString(mapTar);
//		str = URLEncoder.encode(str);
//		System.err.println(str);
		
		String url = "http://localhost:8888/service";
//		String retStr =  NetUtil.sendPost("http://localhost:8080/im/xiaotDoScore", str);
		Map<String,String> mapHeards = new HashMap<>(2);
		postJson(url,null,mapTar);
	}

	/**
	 *
	 * @param url 请求的地址
	 * @param mapHeards  可以为null
	 * @param mapBody  不能为null,请求体
	 * @return 成功就返回   失败返回null
	 */
	public static String postJson(String url,Map<String,String> mapHeards,Map<String, Object> mapBody) {
		//注意这个超时时间
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().
				connectTimeout(7, TimeUnit.SECONDS)
				.readTimeout(7,TimeUnit.SECONDS)
				.writeTimeout(7,TimeUnit.SECONDS)
				.build();
		// 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		RequestBody requestBody = RequestBody.create(JSONType,JSON.toJSONString(mapBody));
		// 创建一个请求对象
		Request request = null;
		if(mapHeards==null) {
			request = new Request.Builder()
					.url(url)
					.post(requestBody).build();
		}else{
			request = new Request.Builder()
					.url(url)
					.headers(SetHeaders(mapHeards))
					.post(requestBody).build();
		}
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
	//----------------------------------------------------------------------

	/**
	 * http请求头，string string的入参map
	 * @param headersParams
	 * @return
	 */
	private static Headers SetHeaders(Map<String, String> headersParams){
		Headers headers=null;
		Headers.Builder headersbuilder=new Headers.Builder();

		Iterator<String> iterator = headersParams.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			headersbuilder.add(key, headersParams.get(key));
//			logger.info("get http", "get_headers==="+key+"===="+headersParams.get(key));
		}
		headers = headersbuilder.build();
		return headers;
	}

}
