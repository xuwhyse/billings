package com.awhyse.jisuan;

/**
 * 求两个数组的，最长公共子序列。
 * 1. 概念说明：  子序列 和 子串的区别，  子串是连续的， 子序列方向一致就行，不需要连续
 *
 * @author xumin
 * @date 2020-11-25 10:25
 */
public class DTGH3Test {

    public static void main(String[] args) {
        int[] listN = {1,-2,3,67,-5,22,57,2,345,-34,-788,35};
        int[] listM = {1,2,345,3546,-34,244,-788,333,35};
        /*
        1. 符号化题目，假设已经有目标
           假设 数组   listN(i,j) 0<=i<=j<=n  是目标的以下标i开始，j结尾的其中子序列
           listM(i1,j1) 0<=i1<=j1<=m  是目标的以下标i1开始，j1结尾的其中子序列
           n>0,m>0,假设n>=m


        2. 根据题意，列出符合题意的其他关系，并符号化
            对于list(n+1)和list(m+1)

        3. 观察下一个list(n+1):  数组  a1,a2...ai,a(i+1)...a(j)...a(n)，a(n+1)
            根据2简化   before1,before2, f(n),after1,after2，a(n+1)
            有关系： before1< f(n),before2<=0, after1<=0,after2<f(n)

            ==> f(n+1) = max (f(n),after2+a(n+1)，f(n)+after1+after2+a(n+1))  ； 其中after2 =
知识更新
         */
//        int sum = test(list);
//        System.err.println(sum);
    }

    private static int test(int[] list) {
        int after1 = 0;
        int after2 = 0;
        int sumx = list[0];
        for(int i=1;i<list.length;i++){
            int ai = list[i];
            if(sumx > after2+ai){
                if(after1+after2+ai>0){
                    sumx = sumx+after1+after2+ai;
                    after1 = 0;
                    after2 = 0;
                }else{
                    sumx = sumx;
                    after1 = after1;
                    after2 = after2+ai;
                }
            }else{
                if(sumx+after1>0){
                    sumx = sumx+after1+after2+ai;
                    after1 = 0;
                    after2 = 0;
                }else {
                    sumx = after2 + ai;
                    after1 = 0;
                    after2 = 0;
                }
            }
        }
        return sumx;
    }
}
