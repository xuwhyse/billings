package com.awhyse.aaa;

/**
 * char 和 int 之间的转化不常用，但基础算法特别是 array要用到
 * @author xumin
 * @date 2021-12-24 17:36
 */
public class CharIntStrHelper {

    public static void main(String[] args) {
        System.err.println(getInt('8'));
        System.err.println(getChar(7));
    }


    /**
     * char -> int
     * @param a '8'
     * @return 8
     */
    public static int getInt(char a){
        return a - 48;
    }

    public static int getInt(String a){
        return Integer.parseInt(a);
    }

    /**
     * int -> char
     * @param a 8
     * @return '8'
     */
    public static char getChar(int a){
        return (char)(a + 48);
    }


}
