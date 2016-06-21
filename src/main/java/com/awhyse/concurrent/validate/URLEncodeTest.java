package com.awhyse.concurrent.validate;

import java.net.URLEncoder;

import org.springframework.security.crypto.codec.Base64;


/**
 * url请求，怎么判断这个请求就是目标人请求过来的呢？：如果没有cookie的支持，只能参数上做文章了。
 * 参数加一个用户识别码，里面用des编解码+魔幻key来达到目标
 * @author xumin 2015-7-9 下午3:45:22
 *
 */
public class URLEncodeTest {

	/**
	 * xumin  2015-7-9 下午3:45:12
	 * @param args
	 */
	public static void main(String[] args) {
		//url中的参数，在传递过程中需要加URLEncoder.encode  ，服务器端会自动解码
		String fkl_user_id;
		try {
			fkl_user_id = new String(Base64.encode(DESPlus.encryptDES("1565876", "myKeyddd")));//这边的key必须8为
			fkl_user_id = URLEncoder.encode(fkl_user_id, "utf-8");//编码后可能在传输中出现问题，所以在urlencode
			System.err.println(fkl_user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
