package com.awhyse.aaa.array;

/**
 * @author xumin
 * @date 2022-01-13 17:49
 */
public class BErWeiArrayFindKey {

    public static void main(String[] args) {
        int[][] arra1 = {{}};

        int[][] arra2 = {{0}};

        int[][] arra3 = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

        int num = 6;
        int rowL = 4;
        int columnL = 4;
        boolean flag = findX (arra3,rowL,columnL,num);
    }

    /**
     * 二维数组中，从左到右数字递增，从上到下数字递增；  求num是否在二维数组里
     * @param array
     * @param num
     * @return
     */
    private static boolean findX(int[][] array,int rowL,int columnL, int num) {

        /*
    分析
     1  2  8   9
     2  4  9   12
     4  7  10  13
     6  8  11  15

     多维数组遍历，xy都可以从0或者最大开始。
     1、从右上角往左数，如果值>指定数，则该列放弃(x--)；如果值小于<指定数，则该行放弃，往下走（y++）;直到
     超出界限。  或者找到值为止


     */

        if(array == null || array.length==0){
            return false;
        }
        int x = columnL - 1;
        int y = 0;
        while(x>=0 && y<rowL){
            int temp = array[x][y];
            if(temp == num){
                System.err.println("x="+x+"   y="+y);
                return true;
            }else if(temp > num){
                x--;
            }else{
                y++;
            }
        }
        return false;
    }

}
