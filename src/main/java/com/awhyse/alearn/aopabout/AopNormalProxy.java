package com.awhyse.alearn.aopabout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by whyse
 * on 2017/4/24 19:19
 */
public class AopNormalProxy implements InvocationHandler {
    static Logger logger = LoggerFactory.getLogger(AopNormalProxy.class);
    private Object target;       //target为目标业务类
    public AopNormalProxy(Object target){
        this.target=target;
    }
    /**
     * 定义切面
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before:" + method);
        Object obj= method.invoke(target, args);  //⑤通过反射调用业务类的目标方法
        logger.info("After:" + method);
        return obj;
    }
}
