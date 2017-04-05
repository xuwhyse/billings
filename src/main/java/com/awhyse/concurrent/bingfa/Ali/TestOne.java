package com.awhyse.concurrent.bingfa.Ali;

/**
 * Created by whyse
 * on 2017/3/10 19:58
 */
public class TestOne {
    public static void main(String[] args) {
        double priceIn = 12.49;
        //题目考虑到实际情况，不考虑超出LONG型的数字
        double priceOut = getFavourablePrice(priceIn);
        System.out.println(priceOut);
    }

    /**
     * 题目1：实现个特别的金额处理方法，返回最接近的形如*.49,*.99的人民币数值。
     * 常用于超市打折场景。
     * 如输入 12.1，输出 11.99；输入12.311，输出12.49。
     * 输入12.74，输出12.99。
     * @param priceIn
     * @return
     */
    public static double getFavourablePrice(double priceIn) {
        if(priceIn<=0){
            return 0;
        }
        if(priceIn<=0.49){
            return 0.49;
        }
        long prePrice = (long) priceIn;
        double priceOne = prePrice-1+0.99;
        double priceTwo = prePrice+0.49;
        double priceTh = prePrice+0.99;
        if(priceIn>priceTwo){
            double temp1 = priceTh-priceIn;
            double temp2 = priceIn-priceTwo;
            //12.74是中位数 题目说输出12.99，因此取大的一位数
            return temp1<=temp2?priceTh:priceTwo;//
        }else{
            double temp1 = priceIn-priceOne;
            double temp2 = priceTwo-priceIn;
            return temp1<temp2?priceOne:priceTwo;
        }
    }
}
