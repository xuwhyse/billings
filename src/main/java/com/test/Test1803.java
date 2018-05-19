package com.test;

import java.util.Scanner;

/**
 * Created by whyse
 * on 2018/3/27 下午7:00
 */
public class Test1803 {
    public static void main(String[] args) {
        inParam();
    }

    private static void inParam() {
        System.out.print("输入");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("输入数据："+read);
    }
}
