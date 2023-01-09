package com.awhyse.aaa.dongtaiguihua;

import java.util.Arrays;

/**
 * @author xumin
 * @date 2022-02-23 19:35
 */
public class ArrayTwo {


    public static void main(String[] args) {
        int[] case1 = {5,8};
        int[] case2 = {1,1};
        System.err.println(getUniquePath(case1));
//        System.err.println(getUniquePath(case2));
    }

    private static int getUniquePath(int[] casexy) {
        int rsp = 0;
        rsp = diguiUnique(casexy[0],casexy[1]);
        System.err.println(rsp);
        
        rsp = dongtaierwei(casexy[0],casexy[1]);
        System.err.println(rsp);

        rsp = dongtaierwei1(casexy[0],casexy[1]);
        System.err.println(rsp);

        rsp = dongtaierwei2(casexy[0],casexy[1]);
        System.err.println(rsp);

        return rsp;
    }

    /**
     * 观察空间复杂度2y的动态方程，发现pre[y],就是上一次的cur[y]
     * 由此数学上得出o(n)的空间负责度方程  cur[y] = cur[y] +cur[y-1]
     * @param x
     * @param y
     * @return
     */
    private static int dongtaierwei2(int x, int y) {
        int[] cur = new int[y];
        Arrays.fill(cur,1);
        for(int i=1;i<x;i++){
            for(int j =1;j<y;j++){
                cur[j] = cur[j]+cur[j-1];
            }
        }
        return cur[y-1];
    }

    /**
     * 动态规划空间转化方程观察
     * f(x,y) = f(x-1,y)+f(x,y-1)
     * 观察两层for循环，当x固定的时候，其实只有x-1那一行，跟y相关； 不需要x-1之前的行
     * @param x
     * @param y
     * @return
     */
    private static int dongtaierwei1(int x, int y) {
        int[] pre = new int[y];
        int[] cur = new int[y];
        Arrays.fill(pre,1);
        Arrays.fill(cur,1);
        for(int i=1;i<x;i++){
            for(int j =1;j<y;j++){
                cur[j] = pre[j]+cur[j-1];
            }
            pre = cur.clone();
        }
        return cur[y-1];
    }

    /**
     * f(x,y) = f(x-1,y)+f(x,y-1) && f(x,1) = 1 = f(1,y)
     * 点评：传统二维数组做法，时间和空间复杂度任然是 x*y
     * @param x
     * @param y
     * @return
     */
    private static int dongtaierwei(int x, int y) {
        int[][] dp = new int[x][y];
        for(int i =0;i<x;i++){
            dp[i][0] = 1;
        }
        for(int j =0;j<y;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<x;i++){
            for(int j =1;j<y;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[x-1][y-1];
    }

    /**
     * 使用了jvm栈，受系统参数限制；空间和时间复杂度x*y
     * @param x
     * @param y
     * @return
     */
    private static int diguiUnique(int x, int y) {
        if(x<1 || y<1){
            return -1;
        }
        if(x==1 || y==1){
            return 1;
        }
        return diguiUnique(x-1,y)+diguiUnique(x,y-1);
    }

}
