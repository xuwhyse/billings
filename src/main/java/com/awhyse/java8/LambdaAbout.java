package com.awhyse.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by whyse on 2017/1/20.
 */
public class LambdaAbout {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(4);
        list.add("sf");
        list.add("asfg");
//        Collections.sort(list, (Comparator<? super String >) (x, y) -> {
//            return x.compareTo(y);
//        });
        Collections.sort(list,(String x,String y)->{
            return x.compareTo(y);
        });
    }
    public static void  printA(Object ob){
        System.out.println(ob);
    }
    public static Object  getA(Object ob){
        return new Date();
    }
}
