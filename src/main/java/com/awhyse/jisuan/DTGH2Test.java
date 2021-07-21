package com.awhyse.jisuan;

/**
 * 求最大子串和：
 *
 * 给出一串长度为n的整数数组，求最大子串的和 （是连续子串，而且和最大）
 * @author xumin
 * @date 2020-11-25 10:25
 */
public class DTGH2Test {

    public static void main(String[] args) {
        int[] list = {1,-2,3,67,-5,22,57,2,345,-34,-788,35};
        /*
        1. 符号化题目，假设已经有目标
            假设有随意整数数组 ： a1,a2...ai,a(i+1)...a(j)...a(n)    【1<=i<=j<=n】
            有sum(i,j)是 数组 list(n)的最大子串和 为 f(n)

        2. 根据题意，列出符合题意的其他关系，并符号化
            ==> 有任意序号b, 1<=b<i ,sum(b,i-1)<=0  , sum(1,b-1)<sum(i,j)
            ===>有任意序号z,j+1<=z<=n, sum(j+1,z)<=0  sum(z+1,n)<sum(i,j)

        3. 观察下一个list(n+1):  数组  a1,a2...ai,a(i+1)...a(j)...a(n)，a(n+1)
            根据2简化   before1,before2, f(n),after1,after2，a(n+1)
            有关系： before1< f(n),before2<=0, after1<=0,after2<f(n)

            ==> f(n+1) = max (f(n),after2+a(n+1)，f(n)+after1+after2+a(n+1))  ； 其中after2 =
知识更新
         */
        int sum = test(list);
        System.err.println(sum);
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
