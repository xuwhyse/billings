package com.billings.billingsystem.security;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author xunmin
 * @createTime 下午1:42:18
 */
public class ConstantSecurity {

	public static final String SecurityUserKey = "sfddf";//验证账户
	public static final String CookiePath = "/";
	public static final String CookieDomain = "";//设置/就会出错，找不到域
	public static final int CookieAge = 86400*2;//两天默认
	public static final String R_Super = "role_super";//超级用户,一切权限
	public static final String R_Admin = "role_admin";//管理员，后台
	public static final String R_VIP = "role_vip";//调用接口
	public static final String R_Gester = "role_gester";

	public static Map<String,SecurityUser>  admins = new HashMap<String, SecurityUser>();//后台用户
	static{
		SecurityUser user = new SecurityUser();
		user.setUserName("xumin");
		user.setPassword("adminxm");
		user.getAuthoritieMap().put(R_Super, 1);
		user.getAuthoritieMap().put(R_Admin, 1);
		admins.put(user.getUserName(), user);
	}
}
