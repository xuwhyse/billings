package com.awhyse.java8;

import java.text.MessageFormat;

/**
 * @author xumin
 * @description
 * @date 2018/10/18 下午7:08
 */
public class StringUtils {
    public static void main(String[] args) {
        template();

    }

    private static void template() {
        String message = MessageFormat.format("您好{0}，晚上好！您目前余额：{1,number,#.##}元，积分：{2}", "张三", 10.155, 10);
        System.out.println(message);
    }
}
