package com.awhyse.aaa.array;

/**
 * @author xumin
 * @date 2022-05-19 19:16
 */
public class A2ArrayFindK {

    public static void main(String[] args) {
        // a,b 非空
        int[] a = {2,5,7,9,10,45};
        int[] b = {6,7};
        //k>=1
        int k = 6;
        // 求a,b两有序数组，弟k个小的数字

        //每次去掉k/2个数
        int t = findK(a,b,k);
//        int t = findK2(a,b,k);

        System.err.println(t);

    }

    /**
     * 指针逐个排序，获取第k.
     * 一个一个排查
     * 假设a,b不为空
     * @param a
     * @param b
     * @param k
     * @return
     */
    private static int findK2(int[] a, int[] b, int k) {

        if(k>(a.length+b.length)){
            return 0;
        }

        int pA = 0;
        int pB = 0;
        int tar = 0;
        int count = 0;
        while(count!=k){
            //A数组已经遍历完
            if(pA == a.length){
                return b[pB+k-count-1];
            }
            if(pB == b.length){
                return a[pA+k-count-1];
            }

            if(a[pA]<=b[pB]){
                tar = a[pA];
                pA++;
            }else{
                tar = b[pB];
                pB++;
            }
            count++;
        }
        return tar;
    }

    private static int findK(int[] a, int[] b, int k) {

        if(k>(a.length+b.length)){
            return 0;
        }
        int pA = 0;
        int pB = 0;
        int tar = stackGetK(a,pA,b,pB,k);

        return tar;
    }

    /**
     * 每次走k/2步 (排除掉的数据)
     * @param a
     * @param pA 当前a的指针
     * @param b
     * @param pB
     * @param k 剩余步数
     * @return
     */
    private static int stackGetK(int[] a, int pA, int[] b, int pB, int k) {
        if(pA == a.length){
            return b[pB+k-1];
        }
        if(pB == b.length){
            return a[pA+k-1];
        }
        if(k==1){
            return a[pA]<=b[pB]?a[pA]:b[pB];
        }

        int step = k/2;
        if(k%2!=0){
            step++;
        }
        int tA = 0;
        int stepA = step;
        if(pA+step>=a.length){
            tA = a[a.length-1];
            stepA = a.length - pA;
        }else{
            tA = a[pA+step-1];
        }

        int tB = 0;
        int stepB = step;
        if(pB+step>=b.length){
            tB = b[b.length-1];
            stepB = b.length - pB;
        }else{
            tB = b[pB+step-1];
        }

        if(tA<=tB){
            return stackGetK(a,pA+stepA,b,pB,k-stepA);
        }else{
            return stackGetK(a,pA,b,pB+stepB,k-stepB);
        }
    }
}
