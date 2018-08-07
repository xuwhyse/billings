package com.awhyse.util;

import java.text.MessageFormat;

/**
 * MessageFormat.format   这个ok
 * Created by whyse
 * on 2018/5/29 下午12:13
 */
public class StringutilsMy {
//    private static final String ZK_ERROR_STRING_TEMPLATE= "failed to parse zk config - key:%s , value:%s";
    private static final String ZK_ERROR_STRING_TEMPLATE= "failed to parse zk config - key:{0} , value:{1}";

    public static void main(String[] args) {
//        String str = String.format(ZK_ERROR_STRING_TEMPLATE,"key","value$~^");//有坑
        String str = MessageFormat.format(ZK_ERROR_STRING_TEMPLATE,"s","value$~^");
        System.err.print(str);
    }
}
