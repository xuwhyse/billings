package com.awhyse.aaa.searchandorder;

/**
 * 基于二分查找的有序子数组找最小值
 * @author xumin
 * @date 2022-01-20 10:44
 */
public class ErfenUser1Test {

    /*
    考点：1.新概念，数组不是有序的，而是有序数组的一个旋转；（可以分为两个有序数组）
    2.对二分查找的过程有深度理解，能写出变种代码
    3.
     */
    public static void main(String[] args) {
        int[] array1 = {1,2,3,4,5,6};
        int[] array2 = {4,5,6,1,2,3};
        int[] array4 = {3,5,1,2,2,3};
        int[] array3 = {1,1,1,1,0,1,1,1};

        //主要思路分析  头指针值>尾指针值（多数正常情况，头与middle对比）
        System.err.println(getMinIndex(array1,0,5));
        System.err.println(getMinIndex(array2,0,5));
        System.err.println(getMinIndex(array4,0,5));
        System.err.println(getMinIndex(array3,0,7));
    }


    private static int getMinIndex(int[] array, int startIndex, int endIndex) {
        if(array==null || array.length == 0){
            return -1;
        }
        if((endIndex - startIndex)==1){
            if(array[startIndex] < array[endIndex]){
                return startIndex;
            }
            return endIndex;
        }

        if(array[startIndex]<array[endIndex]){
            return startIndex;
        }
        if(array[startIndex] == array[endIndex]){
            System.err.println("start到end遍历一遍取最小");
            return findMIn(array,startIndex,endIndex);
        }
        //取中间下标
        int middleIndex = (startIndex+endIndex)/2;
        if(array[startIndex]<array[middleIndex]){
            return getMinIndex(array,middleIndex,endIndex);
        }else if(array[startIndex]>array[middleIndex]){
            return getMinIndex(array,startIndex,middleIndex);
        }
        return findMIn(array,startIndex,endIndex);
    }

    private static int findMIn(int[] array, int startIndex, int endIndex) {
        int minV = startIndex;
        for(int i=startIndex+1;i<=endIndex;i++){
            if(array[i]<array[minV]){
                minV = i;
            }
        }
        return minV;
    }


}
