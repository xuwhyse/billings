package com.hundsun.internet.billingsystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hundsun.internet.billingsystem.common.Constant;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;


/**
 * 系统消息组件拦截器.
 * @author: shangke
 * @since: 2014年3月3日 下午3:11:45
 * @history:
 */
public class MessageInterceptor implements HandlerInterceptor {

    /**
     * @Fields messageService : 消息服务
     */
    @Autowired
    MessageService messageService;

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
        System.out.println("request:" + request);
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

        String poolId = messageService.getMyPoolId();

        if (poolId == null) {
            poolId = request.getParameter(Constant.POOL_ID_KEY_IN_MODEL);
        }

        if (modelAndView == null || modelAndView.getViewName() == null) {
            return;
        }

        if (modelAndView.getViewName().startsWith("redirect:")) {
            // 如果VIEW是重定向，则设置poolId;
            if (!modelAndView.getModel().containsKey(
                Constant.POOL_ID_KEY_IN_MODEL)
                    && poolId != null) {
                modelAndView.getModel().put(Constant.POOL_ID_KEY_IN_MODEL,
                    poolId);
            }
        } else {
            if (poolId != null) {
                modelAndView.addObject("msgList",
                    messageService.getMessageList(poolId));
            }
        }
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
        String poolId = request.getParameter(Constant.POOL_ID_KEY_IN_MODEL);
        if (poolId != null && !poolId.equals("")) {
            messageService.setMyPoolId(poolId);
        }
        return true;
    }

}
