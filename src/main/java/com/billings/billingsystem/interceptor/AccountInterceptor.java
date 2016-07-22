package com.billings.billingsystem.interceptor;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.billings.billingsystem.bean.util.GlobalObjects;
import com.billings.billingsystem.common.Util;
import com.billings.billingsystem.model.SysAccountBean;


/**
 * 
 * @author xunmin
 * @createTime 下午10:21:00
 */
public class AccountInterceptor implements HandlerInterceptor {

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
		String sysAccountId = request.getParameter("sysAccountId");//这个不是主键，是账户；主键要另外查出来
		String password = request.getParameter("password");
		//检查合作商账户是否正常,并第一次存入
		boolean flag = isOkAccount(sysAccountId,password);
		if(!flag){
			Map<String, String>  map = new HashMap<String, String>();
			map.put("flag", "-11");//"合作账号验证失败！"
			Util.responseToClientNow(map,request,response);
			return false;
		}
		return flag;
    }

	private boolean isOkAccount(String sysAccountId, String password) {
//		if(mapAccount==null || mapAccount.size()==0)
//			sysAccountMng.updateSysAccounts();
//		SysAccountBean bean = mapAccount.get(sysAccountId);
//		if(bean==null)
//			return false;
//		GlobalObjects.setToMap(GlobalObjects.SysAccount,bean);
//		if(bean.getAccountPwd().equals(password))
//			return true;
		return false;
	}

}
