package com.hundsun.internet.billingsystem.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author xunmin
 * @createTime 下午1:42:18
 */
public class CookieUtil {

	public static Cookie getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		String name;
		for(int i=0;i<cookies.length;i++){
			name = cookies[i].getName();
			if(name.equals(key))
				return cookies[i];
		}
		return null;
	}
	public static String getCookieValue(HttpServletRequest request,String key) { 
		Cookie co = getCookie(request,key);
		if(co!=null)
			return co.getValue();
		return null;
	}
	public static void addCookie(HttpServletResponse response, String key, String value, int age,
			String domain, String path) {
		Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        if (age > 0) {
            cookie.setMaxAge(age);
        }
        if (domain != null && !domain.equals("")) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
	}
	public static void deleteCookie(HttpServletResponse response, String key,String domain, String path) {
		Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath(path);
        if (domain != null && !domain.equals("")) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
	}
	/**
	 * 后台登陆，保存账户信息
	 * @author xunmin
	 * @createTime 下午2:56:03
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 */
	public static void addSecurityUser(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		SecurityUser userDetails = new  SecurityUser();
		userDetails.setUserName(username);
		userDetails.setPassword(password);
		String value = TokenUtils.createMyToken(userDetails, ConstantSecurity.CookieAge, "127.0.0.1");
		addCookie(response, ConstantSecurity.SecurityUserKey, value, 
				ConstantSecurity.CookieAge, ConstantSecurity.CookieDomain, ConstantSecurity.CookiePath);
	}

}
