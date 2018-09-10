package com.awhyse.concurrent.bingfa.Ali;

/**
 * Created by whyse
 * on 2018/8/24 下午9:06
 */
public class Solution {

    public static void main(String[] args) {
        try{
            throw new RuntimeException();
//            System.err.print("a");

        }catch (Exception e){
            System.err.print("b");
        }finally {
            System.err.print("c");
        }
        System.err.print("d");
    }
}
