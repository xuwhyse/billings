package com.awhyse.aaa.stringm;

/**
 * @author xumin
 * @date 2022-01-14 11:22
 */
public class StringTURL {

    public static void main(String[] args) {
        String str1 = "we are happy";
        char[] char1 = str1.toCharArray();

        String str2 = getURLStr(char1);
        System.err.println(str2);

        str2 = getURLStr(null);
        System.err.println(str2);

        char[] char2  = {' ',' '};
        str2 = getURLStr(char2);
        System.err.println(str2);
    }

    /**
     * 将char1中的' '空格，使用%20 代替
     * 问题来源： 网络编程中，如果url中含有特殊字符，会使服务器解析异常，所以要对特殊字符做替换;其中空格就是 %20
     *
     * 如果使用java的string 按空格切分来解，就有啥意义；底层的算法是要省时间，也要省空间
     * @param charP
     * @return
     *
     * 考察点: 1.考察分析效率的思路，时间复杂度和空间复杂度； 有此推到出一版想法不太行，需要考虑其他。
     * 2. 数组合并或者成新的有序数组，通常考虑从后往前，当然长度要知道
     *
     */
    private static String getURLStr(char[] charP) {
        /*思路1，实例化场景，如果从左往右遍历，遇到空格就做替换；  那需要移动后面o(n)个字符； 继续往后遍历，还有
         * 未知个空格待替换。   时间复杂度o(n*n),空间也是一变再变，所以不合适

         * 思路2，实例化场景，是否能先知道最终字符数组的长度？（可以，先遍历一遍）；
         * 是否能减少重复字符的移动次数？（可以，知道长度后从右往左填充）
         */
        //落地思路：  数组移动借助指针思维，先计算最终数组长度，并申请。然后两数组末尾对齐，往左遍历
        if(charP==null || charP.length==0){
            return null;
        }
        int countK = 0;
        for(int i=0;i<charP.length;i++){
            if(charP[i] == ' '){
                countK++;
            }
        }
        //' ' --> "%20",多了2个字节
        int newLength = countK*2+charP.length;
        char[] newStr = new char[newLength];
        int j = newLength - 1;
        for(int i=charP.length-1;i>=0;i--){
            char temp = charP[i];
            if(temp == ' '){
                newStr[j--] = '0';
                newStr[j--] = '2';
                newStr[j--] = '%';
            }else{
                newStr[j--] = charP[i];
            }
        }
        return new String(newStr);
    }
}
