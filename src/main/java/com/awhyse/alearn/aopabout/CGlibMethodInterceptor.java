package com.awhyse.alearn.aopabout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 定义切面,比如before和after要干些什么
 * Created by whyse
 * on 2017/4/24 19:04
 */
public class CGlibMethodInterceptor implements MethodInterceptor{
    static Logger logger = LoggerFactory.getLogger(CGlibMethodInterceptor.class);
    @Override
    public Object intercept(Object o, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
        logger.info("Before:" + method);
        Object object = methodProxy.invokeSuper(o, arg);//o 是生成的被代理对象，
        logger.info("After:" + method);
        return object;
    }
}
