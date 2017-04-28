package com.awhyse.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by whyse on 2017/1/20.
 */
public class LambdaAbout {
    static Logger logger = LoggerFactory.getLogger(LambdaAbout.class);
    public static void main(String[] args) {
//        sort();
        System.err.println(3*1.1==3.3);
    }

    private static void sort() {
        int size = 1000;
        List<Double> list = new ArrayList<>(size);
        for(int i=0;i<size;i++) {
            list.add(i*1.1);
        }
//        Collections.sort(list, (Comparator<? super String >) (x, y) -> {
//            return x.compareTo(y);
//        });
        Collections.sort(list,(x, y)->{
            return x.compareTo(y);
        });
        list.forEach((x)->{System.out.println(x);});
    }
}
