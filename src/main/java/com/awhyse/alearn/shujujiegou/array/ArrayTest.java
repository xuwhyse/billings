package com.awhyse.alearn.shujujiegou.array;


import java.util.HashMap;

/**
 * Created by whyse
 * on 2018/5/18 下午9:27
 */
public class ArrayTest {

    static int arr[] = new int[10];

    public static void main(String[] args) {
//        int[] A = {1,2,3};
//        int[] B = {2,5,6};
//        merge(A,3,B,3);

        int[] a = {1,2,3,4,5,6};
        int[] b = {3,4,5,6,7,8};
        findMedianinTwoSortedAray(a,b);
        System.out.println("hello");
    }

    /**
     * 思路： 一般数组
     * 最长无重复子数组
     *https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4?tpId=188&&tqId=38553&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength (int[] arr) {
        // write code here
        //假设有最长子串a(i,j)，则往前加一个或者往后加一个都会重复
        //得出推论，第一次遍历后记录最长字段，而且下次遍历时是从出现第一个重复数字之后开始就行了
        HashMap<Integer,Integer> map = new HashMap<>(arr.length);
        int max = 0;
        for(int i = 0;i<arr.length;){
            int temp = arr[i];
            if(map.containsKey(temp)){
                if(max<map.size()){
                    max = map.size();
                }
                i = map.get(temp)+1;
                map.clear();
            }else{
                map.put(temp,i);
                ++i;
            }
        }
        if(max==0){
            return map.size();
        }
        return max;
    }

    /**
     * 合并两个有序的数组
     *
     *  int[] A = {1,2,3};
     *         int[] B = {2,5,6};
     *         merge(A,3,B,3);
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        if(n==0){
            return;
        }
        if(m==0){
            for(int i=0;i<m+n;i++){
                A[i] = B[i];
            }
            return;
        }
        int pa = 0;
        int pb = 0;
        int[] C = new int[m+n];
        int pc = 0;

        while(pa<m && pb<n){
            while(pa<m && pb<n && A[pa]<=B[pb]){
                C[pc] = A[pa];
                pc++;
                pa++;
                if(pa == m){
                    while(pb<n){
                        C[pc] = B[pb];
                        pb++;
                        pc++;
                    }
                    break;
                }
            }
            while(pa<m && pb<n && A[pa]>B[pb]){
                C[pc] = B[pb];
                pc++;
                pb++;
                if(pb == n){
                    while(pa<m){
                        C[pc] = A[pa];
                        pa++;
                        pc++;
                    }
                    break;
                }
            }
        }

//        for(int i=0;i<m+n;i++){
//            A[i] = C[i];
//        }

    }


    /**
     * find median in two sorted array
     * @param arr1 int整型一维数组 the array1
     * @param arr2 int整型一维数组 the array2
     * @return int整型
     */
    public static int findMedianinTwoSortedAray (int[] arr1, int[] arr2) {
        // write code here
        // 时间复杂度o(logn那就是二分法)：
        //r1 和 r2的中位数 =  两数组二分后，两目标数组的中位数
        //出口是，数组indexl 和 r 相等去两个的最大值，或者mid值相等直接返回
        int r1l = 0;
        int r1r = arr1.length-1;
        int r2l = 0;
        int r2r = r1r;
        int r1m = 0;
        int r2m = 0;

        while(r1l < r1r && r2l<r2r){
            r1m = (r1l+r1r)/2;
            int vr1m = arr1[r1m];
            r2m = (r2l+r2r)/2;
            int vr2m = arr2[r2m];

            //之间的数量是单数:双闭  ；双数，小开，大闭
            boolean isSingle = true;
            if((r1r - r1l)%2!=0){
                isSingle = false;
            }

            if(vr1m == vr2m){
                return  vr1m;
            }else if(vr1m < vr2m){

                if(isSingle){
                    r1l = r1m;
                    r2r = r2m;
                }else{
                    r1l = r1m + 1;
                    r2r = r2m ;
                }

            }else{
                if(isSingle){
                    r2l = r2m ;
                    r1r = r1m;
                }else{
                    r2l = r2m + 1;
                    r1r = r1m ;
                }

            }
        }

        return Math.max(arr1[r1m],arr2[r2m]);
    }
}
