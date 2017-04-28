package com.awhyse.javabugs;

import com.awhyse.util.DoubleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * java浮点数直接相减比较等会出现问题
 * 一定要用BigDecimal  或者math的来精准计算
 */
public class DoubleAndFloatCompare {
    static Logger logger = LoggerFactory.getLogger(DoubleAndFloatCompare.class);
    public static void main(String[] args) {
        sort();
//        errorTest1();

    }

    private static void errorTest1() {
//        System.err.println((3.3==3.3);//false
        //----------------------------------
        System.err.println("++："+ DoubleUtils.add(3,1.1));
        System.err.println("--："+DoubleUtils.sub(3,1.1));
        System.err.println("**："+DoubleUtils.mul(3,1.1));
        System.err.println("//："+DoubleUtils.div(3,1.1));
        //-------------------------------
        BigDecimal d1 = new BigDecimal(Double.toString(3.3));
    }

    private static void sort() {
        int size = 1000;
        List<Double> list = new ArrayList<>(size);
        for(int i=0;i<size;i++) {
            list.add(i*1.1);
        }
        int t=0;
        int tt=-1;
        System.err.println(-t);
        System.err.println(-tt);
        Collections.sort(list,(x, y) -> {
            return -Double.compare(x,y);
        });
//        Collections.sort(list,(x, y)->{
//            return x > y ? -1:1;
//        });
        list.forEach((x)->{System.out.println(x);});
    }
}
