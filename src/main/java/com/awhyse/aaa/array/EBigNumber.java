package com.awhyse.aaa.array;

/**
 * @author xumin
 * @date 2021-12-24 17:26
 */
public class EBigNumber {

    public static void main(String[] args) {
        String a = "1";
        String b = "99";
        String c = solve(a,b);
        System.err.println(c);
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public static String solve (String s, String t) {
        // write code here
        if(s == null){
            return t;
        }
        if(t == null){
            return s;
        }
        int lengthMin = s.length();
        char[] minArray = s.toCharArray();
        char[] maxArray = t.toCharArray();
        if(s.length() > t.length()){
            lengthMin = t.length();
            minArray = t.toCharArray();
            maxArray = s.toCharArray();
        }
        int biggerTen = 0;
        int j = maxArray.length - 1;
        for(int i = lengthMin-1 ;i>=0;i--){
            int temp = minArray[i]-48 + maxArray[j] - 48 + biggerTen;
            if(temp>=10){
                biggerTen = 1;
            }else{
                biggerTen = 0;
            }
            maxArray[j] = (char)(temp%10 + 48);
            j--;
        }
        if(biggerTen == 0){
            return new String(maxArray);
        }
        while(j>=0 && biggerTen == 1){
            int temp = maxArray[j] - 48 + biggerTen;
            if(temp>=10){
                biggerTen = 1;
            }else{
                biggerTen = 0;
            }
            maxArray[j] = (char)(temp%10 + 48);
            j--;
        }
        if(biggerTen == 1){
            return 1+new String(maxArray);
        }
        return new String(maxArray);
    }
}
