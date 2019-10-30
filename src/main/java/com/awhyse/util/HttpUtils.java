package com.awhyse.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtils {

	private static final MediaType MEDIA_TYPE_JSON = MediaType
			.parse("application/x-www-form-urlencoded; charset=utf-8");
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");
	public static final MediaType JSONType = MediaType
			.parse("application/json; charset=utf-8");

	private static final int timeOut = 9;

	/**
	 * 单例客户端
	 */
	private static OkHttpClient okHttpClient = null;
	static {
		okHttpClient = new OkHttpClient().newBuilder().
				connectTimeout(timeOut, TimeUnit.SECONDS)
				.readTimeout(timeOut,TimeUnit.SECONDS)
				.writeTimeout(timeOut,TimeUnit.SECONDS)
				.build();
	}

	/**
	 * @param args
	 *            author:xumin 2016-12-16 上午11:44:06
	 */
	public static void main(String[] args) {
		postJson("http://www.baidu.com",null,"hello");
	}

	/**
	 *
	 * @param url 请求的地址
	 * @param mapHeard  可以为null
	 * @param json  不能为null,请求体
	 * @return 成功就返回   失败返回null
	 */
	public static String postJson(String url,Map<String,String> mapHeard,String json) {
		// 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		if(json == null){
			json = "";
		}
		RequestBody requestBody = RequestBody.create(JSONType, json);
		// 创建一个请求对象
		Request request = null;
		if(mapHeard==null) {
			request = new Request.Builder()
					.url(url)
					.post(requestBody).build();
		}else{
			request = new Request.Builder()
					.url(url)
					.headers(SetHeaders(mapHeard))
					.post(requestBody).build();
		}
		// 发送请求获取响应
		try {
			Response response = okHttpClient.newCall(request).execute();
			// 判断请求是否成功
			if (response.isSuccessful()) {
				// 打印服务端返回结果
				String str = response.body().string();
				return str;
			}else{
				System.err.println(response.toString());
			}
		} catch (IOException e) {
			log.error("error",e);
		}
		return null;

	}

	/**
	 *
	 * @param url 请求的地址
	 * @param mapHeard  可以为null
	 * @return 成功就返回   失败返回null
	 */
	public static String get(String url,Map<String,String> mapHeard) {
		// 创建一个请求对象
		Request request = null;
		if(mapHeard==null) {
			request = new Request.Builder()
					.url(url)
					.build();
		}else{
			request = new Request.Builder()
					.url(url)
					.headers(SetHeaders(mapHeard))
					.build();
		}
		// 发送请求获取响应
		try {
			Response response = okHttpClient.newCall(request).execute();
			// 判断请求是否成功
			if (response.isSuccessful()) {
				// 打印服务端返回结果
				String str = response.body().string();
				return str;
			}else{
				System.err.println(response.toString());
			}
		} catch (IOException e) {
			log.error("error",e);
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
		}
		headers = headersbuilder.build();
		return headers;
	}

}
