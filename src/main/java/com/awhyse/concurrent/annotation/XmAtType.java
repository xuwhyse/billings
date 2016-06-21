package com.awhyse.concurrent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *@author hzxumin15
 *2015-4-20上午10:38:37
 */
@Target(ElementType.TYPE) //类，接口(包括注解类型)或enum声明
@Retention(RetentionPolicy.RUNTIME)//表示该注解的生命周期 VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
public @interface XmAtType {

	String uri() default "";//方法没有参数,返回时基本类型,   访问权限默认就是public
	String desc() default "没有注释";
	int age() default 0;//default，默认实现,返回
}

