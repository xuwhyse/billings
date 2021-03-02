package com.awhyse.alearn.jisuan.hard;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 指定k次交易
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * @author xumin
 * @date 2020-11-25 10:25
 */
public class DTGH2GpTest {

    public static void main(String[] args) {
        int[] list = {1,-2,3,67,-5,22,57,2,345,-34,-788,35};
        /*
        1. 凡是最优解的，考虑动态规划。
        2. 假设好得到结果的前1，2，3步之间的关系最重要； 假设错了，弯了就白玩

        ----》  int[] prices n个，最多交易k次 ， 求最大利润
        肯定有遍历prices，二维是k次的限制。 适合用二维数组
        1. k> n/2 使用贪心
        2. 其他的k可能不够
            1. 最后一次利润肯定是第k次的卖出   （需要找到第k次的买入，第k-1次的卖出）
            2. 使用第三维度买卖状态，来标记所有日子的可能情况。 pd[i][j][2]  i<n  j<k 是第i天，第j次交易，买入或者卖出的利润
            3.
            pd_1_1_1  = - prices[j]
            pd_1_1_0 = prices[j] + pd[0][0][1]

         */
        int sum = maxProfit(2,list);
        System.err.println(sum);
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以交易k 次完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    private static int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length<2 || k==0){
            return 0;
        }
        if(k >= prices.length/2){
            return maxProfit(prices);
        }
        int length = prices.length;

        for(int i=0;i<length;i++){

        }
        return 0;

    }


    /**
     * 贪心算法
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
}
