package com.hundsun.internet.billingsystem.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hundsun.internet.billingsystem.security.AdminService;
import com.hundsun.internet.billingsystem.security.CookieUtil;
import com.hundsun.internet.billingsystem.security.ConstantSecurity;
import com.hundsun.internet.billingsystem.security.TokenUtils;
import com.hundsun.internet.billingsystem.service.billingsoper.SysAccountMng;

/**
 * 验证权限
 * @author xunmin
 * @createTime 下午12:54:01
 */
public class RightInterceptor implements HandlerInterceptor {

	@Autowired
	SysAccountMng sysAccountMng;
    /**
     * 视图解析完成后.
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 句柄
     * @param exception 异常对象
     * @exception Exception 异常
     */
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
//        System.out.println("request:" + request);
    }

    /**
     * Controller方法执行之后.
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 句柄
     * @param modelAndView 模型视图对象
     * @exception Exception 异常
     */
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

//    	System.out.println("sdgfh");
    }

    /**
     * Controller方法执行之前.
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 句柄
     * @exception Exception 异常
     * @return 是否执行
     */
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	
		String authToken = CookieUtil.getCookieValue(request,ConstantSecurity.SecurityUserKey);
		System.err.println(authToken);
		String name = TokenUtils.getUsernameFromToken(authToken);
		if(name==null){
			AdminService.RedirectLogin(request,response);
			return false;
		}
		boolean flag = TokenUtils.validateToken(authToken, ConstantSecurity.admins.get(name), "127.0.0.1");
		if(!flag)
			AdminService.RedirectLogin(request,response);
		return flag;
    }

}
