package com.awhyse.concurrent.bingfa.Ali;

/**
 * Created by whyse
 * on 2018/8/12 上午10:00
 */
public class Test4 {
    public static void main(String[] args) {
        int m=5,n=2;
        int[][] juz = {{0,1,0,0,1},{0,1,0,0,1}};
        Long num = 0l;//群体个数
        Long maxCount = 0l;//最大群体的人数
        paint(m,n,juz);
        System.err.print(juz);
    }

    /**
     * 原先二维数组是0，1的值
     * 现在遍历二维数组，定义一个变量a初始值是2含义是：如果遇到是1，就用该值代替。接着把该位置的所有邻座都用a代替。
     * 继续遍历，遇到非1的就继续把该值的所有邻座值是1的代替成a。 直到遇到0，a=a+1，变成3.下一次遇到1的话就把1变成a，紧接着
     * 把相邻的都变成3，重复上一次操作。直到二维数组遍历完毕。   此时返回的二维数组中,值2的数是一个群体，3是一个群体。。。如果最后一个
     * 值不是0，那么a-1 就是群体的数量。 如果最后一个值是0那么a-2是群体的数量 num
     *
     * 再次遍历二维数组，统计2~a-1 各值出现的次数。  出现最多的就是最大群体人数
     * @param x 一行个数
     * @param y 一共几行
     * @param juz 一行x个，一共y行
     */
    private static void paint(int x, int y, int[][] juz) {
        int a=2;
        //遇到0后，a是否+1的标注位
        boolean canAdd = false;//遇到是1后激活成true,再次遇到0后a=a+1 同时变成false
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                int temp = juz[i][j];
                if(temp == 0 && canAdd){
                    a=a+1;
                    canAdd = false;
                } else if(temp==1){
                    //将自己和还未遍历的邻座是1的都涂成a

                }else{
                    //该值既不是0也不是1，就是属于之前被涂的，把自己的邻座是1的涂成自己的值，传染
                }
            }
        }
    }
}
