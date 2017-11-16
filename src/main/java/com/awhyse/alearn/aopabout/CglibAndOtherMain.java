package com.awhyse.alearn.aopabout;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * jdk中的动态代理通过反射类Proxy和InvocationHandler回调接口实现，要求委托类必须实现一个接口，只能对该类
 * 接口中定义的方法实现代理，这在实际编程中有一定的局限性。
 *
 * 注意：真正的spring mvc常用实现servlet.HandlerInterceptor 接口的类，就能预先定义拦截器
 * 通过addInterceptors 把实现预定义接口的类放入某个调用类的队列.这样，速度更快，既不是reflect，又不是asm
 * Created by whyse
 * on 2017/4/24 18:37
 */
public class CglibAndOtherMain {
    public static void main(String[] args) {
//        dtdl();
        cglibTest();
    }

    /**
     * 底层采用asm字节码生成框架生成代理类的字节码
     * 使用了继承,所以final不能被代理
     */
    private static void cglibTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AopCglib.class);//先设置超类,使用继承生成代理
        enhancer.setCallback(new CGlibMethodInterceptor());
        AopCglib aopCglib = (AopCglib)enhancer.create();
        aopCglib.a();//被拦截代理
        aopCglib.b();//能调用，但是调用了超类的方法，没有被切面拦截代理
    }

    /**
     * 这个使用了组合模式，使用的是反射，被代理类必须实现一个接口，后面将以接口的
     * 形式引用，是局限性
     */
    private static void dtdl() {
        AopTestImpl target=new AopTestImpl();   //被代理对象
        AopNormalProxy handler=new AopNormalProxy(target);  //将目标业务类和横切代码编织到一起
        AopTestI proxy=(AopTestI) Proxy.newProxyInstance(   //创建代理实例
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
//        proxy.toString();
        proxy.a();
//        proxy.b();
    }
}
