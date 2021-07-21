package com.awhyse.jisuan.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xumin
 * @date 2020-11-30 14:26
 */
public class Test1 {

    public static void main(String[] args) {
        int[] a = {-1,-100,3,99};

//        int num = deleteFromOrderList();//原地移动，读写双指针

//        int num = maxProfit(a);//贪心算法（找相邻规律），遍历一遍

//        rotate(a,2);
        rotateA(a,2);
        printList(a);
        System.err.println();
    }

    public static int[] intersect(int[] nums1, int[] nums2) {

        if(nums1 == null || nums1.length==0 || nums2==null || nums2.length==0){
            return new int[0];
        }
        int[] longList = nums1;
        int[] shortList = nums2;
        if(nums1.length<nums2.length){
            longList = nums2;
            shortList = nums1;
        }
        Set<Integer> set = new HashSet<>(longList.length);
        for(int a : longList){
            set.add(a);
        }
        Set<Integer> setT  = new HashSet<>(shortList.length);
        for(int a : shortList){
            if(set.contains(a)){
                setT.add(a);
            }
        }
        int[] tar = new int[setT.size()];
        int index=0;
        for(int i : setT){
            tar[index] = i;
            ++index;
        }
        return tar;

    }

    private static void printList(int[] a) {
        for(int x : a){
            System.err.print(x+" ");
        }
        System.err.println("");
    }

    private static int deleteFromOrderList() {
        Integer[] a = {1,3,3,4,4,4,7,8,12,12,34,34,34};

        Integer temp = a[0];
        int wIndex = 0;
        for(int i=1;i<a.length;i++){
            if(!temp.equals(a[i])){
                wIndex++;
                a[wIndex] = a[i];
                temp = a[i];
            }
        }
        return ++wIndex;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int buy = -1;
        int profit = 0;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i]<prices[i+1] && buy==-1){
                buy = prices[i];
            }
            if(prices[i]>prices[i+1] && buy!=-1){
                profit = profit+prices[i]- buy;
                buy = -1;
            }
            if(i+1 == prices.length-1 && buy!=-1){
                return profit+prices[i+1]- buy;
            }
        }
        return profit;
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * o1,空间，原地算法，循环移动
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        if(nums==null || nums.length<2){
            return;
        }
        k = k%nums.length;
        for(int i=0;i<k;i++){
            int temp = nums[nums.length-1];
            for(int j=nums.length-1;j>0;j--){
                nums[j] = nums[j-1];
            }
            nums[0] = temp;
        }
    }

    private static void rotateA(int[] nums, int k) {
        if(nums==null || nums.length<2){
            return;
        }
        k = k%nums.length;

        int oldIndex = 0;
        int oldV = nums[0];
        int newIndex = 0;
        int newV = 0;

        for(int i=0;i<nums.length;i++){
            newIndex = (oldIndex+k)%nums.length;
            newV = nums[newIndex];
            nums[newIndex] = oldV;
            oldIndex = newIndex;
            oldV = newV;

            printList(nums);
        }
    }
}
