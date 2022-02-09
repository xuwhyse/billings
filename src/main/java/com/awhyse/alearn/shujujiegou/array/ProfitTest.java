package com.awhyse.alearn.shujujiegou.array;

import java.util.HashMap;

/**
 * Created by whyse
 * on 2018/5/18 下午9:27
 */
public class ProfitTest {

    static int arr[] = new int[10];

    public static void main(String[] args) {
        System.out.println("hello");
        synchronized (new HashMap<>()){

        }
    }

    /**
     * 思路： 动态规划； 找到fn的最大利润 和 fn+1最大利润的关系
     * ====>  math.max( v(n+1)- array.min,maxn )
     *
     * 假设你有一个数组，其中第\ i i 个元素是股票在第\ i i 天的价格。
     * 你有一次买入和卖出的机会。（只有买入了股票以后才能卖出）。请你设计一个算法来计算可以获得的最大收益。
     *
     * @param prices int整型一维数组
     * @return int整型
     */
    public int maxProfit (int[] prices) {
        // write code here
        // fn 的最大利润是 maxn ,那 fn+1的最大利润是 v(n+1)- array.min,和maxn
        if(prices==null || prices.length<2){
            return 0;
        }
        int profitM = 0;
        int minV = prices[0];
        for(int i = 1;i<prices.length;i++){
            if(minV>prices[i]){
                minV = prices[i];
            }
            int temp = prices[i]-minV;
            if(temp > 0){
                profitM = Math.max(profitM,temp);
            }

        }
        return profitM;
    }
}
