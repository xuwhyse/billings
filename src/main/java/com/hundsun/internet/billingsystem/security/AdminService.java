package com.hundsun.internet.billingsystem.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台安全等
 * @author xunmin
 * @createTime 下午1:42:18
 */
public class AdminService {

	public static int login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		SecurityUser user = ConstantSecurity.admins.get(username);
		if(user!=null){
			if(user.getPassword().equals(password)){
				CookieUtil.addSecurityUser(username,password,request,response);
				return 0;
			}
			else
				return 1;
		}
		return -1;
	}

	public static void RedirectLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("/login.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logout(HttpServletResponse response) {
		CookieUtil.deleteCookie(response, ConstantSecurity.SecurityUserKey, 
				ConstantSecurity.CookieDomain, ConstantSecurity.CookiePath);
	}

}
