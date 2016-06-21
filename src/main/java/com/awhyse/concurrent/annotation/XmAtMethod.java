package com.awhyse.concurrent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 1. Annotation型定义为@interface, 所有的Annotation会自动继承java.lang.Annotation这一接口,并且不能再去继承别的类或是接口.
2. 方法成员只能用public或默认(default)这两个访问权修饰
3. 返回参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和String、
Enum、Class、annotations等数据类型,以及这一些类型的数组.
4. 要获取类方法和字段的注解信息，必须通过Java的反射技术来获取 
Annotation对象,因为你除此之外没有别的获取注解对象的方法
5. 注解也可以没有定义成员, 不过这样注解就没啥用了
 */
/**
 *
 *@author hzxumin15
 *2015-4-20上午10:38:37
 */
@Target(ElementType.METHOD) //此处成为元注解 ，注解的定义需要元注解 ElemenetType.CONSTRUCTOR 构造器声明...
@Retention(RetentionPolicy.RUNTIME)//表示该注解的生命周期 VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
public @interface XmAtMethod {

	String uri() default "";//方法没有参数,返回时基本类型,   访问权限默认就是public
	String desc() default "没有注释";
	int age() default 0;//default，默认实现,返回
}

