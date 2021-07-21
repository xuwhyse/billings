package com.awhyse.jisuan;

import java.util.HashMap;

/**
 * 动态规划，斐波那契数列问题
 * @author xumin
 * @date 2020-11-23 21:03
 */
public class Dtgu1Test {


    public static void main(String[] args) {
        int n = 45;
        int x;
        HashMap<Integer,Integer> map = new HashMap(77);

        long time = System.currentTimeMillis();


//        x = test1(n);//最普通的暴力递归
//        x = test2(n, map);//递归中发现有重复计算，所以重复计算的做存储
        x = test3(n);// 发现演进中的中途结果的相互关系，真正的动态规划



        System.err.println(x+"  cost: "+(System.currentTimeMillis()-time));
    }

    /**
     * 动态规划核心：  找到了先后两步之间的关系，并且利用少量存储来计算
     * @param n
     * @return
     */
    private static int test3(int n) {
        if(n == 1 || n==2){
            return 1;
        }else{
            int thread = 0;
            int sec = 1;
            int one = 1;
            for(int i=3;i<=n;i++){
                thread = one+sec;
                one = sec;
                sec = thread;
            }
            return thread;
        }
    }

    /**
     * 基于递归，发现其实很多函数被重复计算，所以用个存储器记录已经计算过的值
     * 变的很快，但不是最优
     * @param n
     * @param map
     * @return
     */
    private static int test2(int n, HashMap<Integer,Integer> map) {
        if(n == 1 || n==2){
            return 1;
        }else{
            if(map.containsKey(n)){
                return map.get(n);
            }else{
                int temp = test2(n-1,map)+test2(n-2,map);
                map.put(n,temp);
                return temp;
            }
        }
    }

    /**
     * 这种方式是直接使用递归，不好
     * @param n
     * @return
     */
    private static int test1(int n) {
        if(n == 1 || n==2){
            return 1;
        }else{
            return test1(n-1)+test1(n-2);
        }
    }

}
