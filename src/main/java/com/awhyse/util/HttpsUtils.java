package com.awhyse.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 处理https
 * @author xumin
 */
public class HttpsUtils {

	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static final MediaType MEDIA_TYPE_JSON = MediaType
			.parse("application/x-www-form-urlencoded; charset=utf-8");// mdiatype
	// 这个需要和服务端保持一致
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");// mdiatype 这个需要和服务端保持一致
	public static final MediaType JSONType = MediaType
			.parse("application/json; charset=utf-8");


	static class MyTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}

	/**
	 * @param args
	 *            author:xumin 2016-12-16 上午11:44:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://help.zcy.gov.cn/help-center/api/cmsProductDynamics/getProductDynamicsContentsByCode?pageSize=6&categoryCode=help_loan";
		String str = postJson(url,null,null);
		System.err.println(str);
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
		OkHttpClient okHttpClient = getAllTrustOkHttpClient();
//		okHttpClient = getTrustClient(null);
		// 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
		RequestBody requestBody = RequestBody.create(JSONType, JSON.toJSONString(mapBody));
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
				return str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}



	//----------------------------------------------------------------------------

	/*
		上述代码中的证书的 InputStream 可通过两种方式获得
		将证书放到工程中例如 assets 目录中，然后通过如下代码获得输入流
		InputStream inputStream = context.getAssets().open("srca.cer");
		通过命令行在证书所在目录运行
		keytool -printcert -rfc -file srca.cer srca.cer换成自己的证书名
		得到证书内的字符串内容，将字符串内容通过如下代码转换成 InputStream
		InputStream ins = new Buffer()
				.writeUtf8(comodoRsaCertificationAuthority)
				.writeUtf8(entrustRootCertificateAuthority)
				.inputStream();
	 */
	/**
	 * 对外提供的获取支持自签名的okhttp客户端
	 * @param certificate 自签名证书的输入流
	 * @return 支持自签名的客户端
	 */
	public static OkHttpClient getTrustClient(InputStream certificate) {
		X509TrustManager trustManager;
		SSLSocketFactory sslSocketFactory;
		try {
			trustManager = trustManagerForCertificates(certificate);
			SSLContext sslContext = SSLContext.getInstance("TLS");
			//使用构建出的trustManger初始化SSLContext对象
			sslContext.init(null, new TrustManager[]{trustManager}, null);
			//获得sslSocketFactory对象
			sslSocketFactory = sslContext.getSocketFactory();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
		return new OkHttpClient.Builder()
				.connectTimeout(7, TimeUnit.SECONDS)
				.readTimeout(7,TimeUnit.SECONDS)
				.writeTimeout(7,TimeUnit.SECONDS)
				.sslSocketFactory(sslSocketFactory, trustManager)
				.build();
	}
	/**
	 * 相信所有证书，这个不安全，可用于开发测试的时候
	 * @return
	 */
	private static OkHttpClient getAllTrustOkHttpClient() {

		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
				.connectTimeout(7, TimeUnit.SECONDS)
				.readTimeout(7,TimeUnit.SECONDS)
				.writeTimeout(7,TimeUnit.SECONDS)
				.sslSocketFactory(createSSLSocketFactory(), new MyTrustManager())
				.hostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String s, SSLSession sslSession) {
						return true;
					}
				})
				.build();
		return okHttpClient;
	}

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

	/**
	 * https请求，相信所有证书的代码
	 * @return
	 */
	private static SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}
		return ssfFactory;
	}


	/**
	 * 获取信任自签证书的trustManager
	 * @param in 自签证书输入流
	 * @return 信任自签证书的trustManager
	 * @throws GeneralSecurityException
	 */
	private static X509TrustManager trustManagerForCertificates(InputStream in)
			throws GeneralSecurityException {
		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		//通过证书工厂得到自签证书对象集合
		Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
		if (certificates.isEmpty()) {
			throw new IllegalArgumentException("expected non-empty set of trusted certificates");
		}
		//为证书设置一个keyStore
		char[] password = "password".toCharArray(); // Any password will work.
		KeyStore keyStore = newEmptyKeyStore(password);
		int index = 0;
		//将证书放入keystore中
		for (Certificate certificate : certificates) {
			String certificateAlias = Integer.toString(index++);
			keyStore.setCertificateEntry(certificateAlias, certificate);
		}
		// Use it to build an X509 trust manager.
		//使用包含自签证书信息的keyStore去构建一个X509TrustManager
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
				KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, password);
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
			throw new IllegalStateException("Unexpected default trust managers:"
					+ Arrays.toString(trustManagers));
		}
		return (X509TrustManager) trustManagers[0];
	}

	private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			InputStream in = null; // By convention, 'null' creates an empty key store.
			keyStore.load(null, password);
			return keyStore;
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

}
