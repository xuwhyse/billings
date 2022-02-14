package com.awhyse.aaa.yuhuo;

/**
 * &(与 相等于 &&) 或运算，基本就是二进制， 还有<< (2进制 /2) 和    >>
 *
 *  与或是针对2进制操作的
 * @author xumin
 * @date 2022-02-10 16:08
 */
public class YuhuoCommon {

    public static void main(String[] args) {
        int num1 = 512;
        int num2 = 233;
        int num3 = 0;
        int num4 = -123;
        System.err.println(isNum2Time(num1));
        System.err.println(isNum2Time(num2));
        System.err.println(isNum2Time(num3));
        System.err.println(isNum2Time(num4));
    }

    /**
     *  判断num是否是2的整数被
     * @param num
     * @return
     */
    private static boolean isNum2Time(int num) {
        if(num<0){
            return false;
        }
        if(num == 0){
            return true;
        }
        //如果num是2的整数倍，那2进制表现是首位1，其余0
        //num-1 是全部1;  两数相& 就==0
        int tar = num & (num-1);
        if(tar == 0){
            return true;
        }
        return false;
    }
}
