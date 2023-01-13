package com.awhyse.cache;

import java.lang.annotation.*;

/**
 * 自定义redis缓存标签需要jdk1.8
 * 支持动态设置key，设置缓存超时时间，设置动态刷新缓存
 * 注意 ：由于采用aop实现，同一个类中调用标签方法将不起到缓存的作用 !
 * @author xumin 和歌
 * @date 2019-07-17 15:17
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 默认会加前缀web-loan_
     * key键值，里面可以设置动态参数,使用{}里面是参数
     * 如test--{para.intentionCode}--aa : para对象中的intentionCode值--> web-loan_test--值--aa
     * 注意：{}只允许一个，暂不支持多个
     * @return
     */
    String key() default "";

    /**
     * 过期时间,默认30分钟
     * @return
     */
    int expired() default 30*60;

    /**
     * 默认都是超时更新，如果该值为1,则立马更新redis的值
     * 支持动态读取参数:如"{update}",读取方法参数名为update的值
     * @return
     */
    String update() default "0";
}

