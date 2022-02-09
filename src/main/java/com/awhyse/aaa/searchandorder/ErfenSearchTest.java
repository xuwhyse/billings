package com.awhyse.aaa.searchandorder;

/**
 * 二分查找实现
 * @author xumin
 * @date 2022-01-20 10:44
 */
public class ErfenSearchTest {
    /*
    查找：1.顺序查找  2.二分查找(为啥不多分？非洲重要，循环和递归)  3.哈希查找   4.二叉排序树查找
     */

    public static void main(String[] args) {
        int[] array1 = {2,6,1,7,3,4,5};
//        int middle = partitionGetPosition(array1,0,6);
//        System.err.println(middle);
        array1 = erfenSearchDG(array1,0,6);
        System.err.println(array1);
    }

    private static int[] erfenSearchDG(int[] array, int start, int end) {
        if(array == null || start == end){
            return null;
        }
        int middle = partitionGetPosition(array,start,end);
        if(middle>start){
            erfenSearchDG(array,start,middle-1);
        }
        if(middle<end){
            erfenSearchDG(array,middle+1,end);
        }

        return array;
    }

    /**
     * 二分法，核心基础方法！！，分割函数。
     * 核心点: 设置middle指针，指向左边队列的最末位（顺序遍历的时候需要不停的调整middle指针，来保证两边集合符合）
     * 以array[end] 为标的值，小的放左边，大的放右边。 最后array[end]对应值的真正下标
     * @param array
     * @param start
     * @param end
     * @return array[end]值的，在数组中的最终位置
     */
    private static int partitionGetPosition(int[] array, int start, int end) {
        if(array == null || start == end){
            return end;
        }
        // 假设标的值array[end]的最终下标是middle,比array[end]小的在middle左边，大的在右边
        //思路：middle最终是 标的array[end]的下标； 我们要动态找到middle,并且保证midlle左边的
        //都被array[end]小。   最终
        // 从左边开始遍历，middle初始值是start-1;始终指向左侧队列的尾部
        int middle = start-1;
        for(int i=start;i<end;i++){
            if(array[i]<array[end]){
                // 关键点: i<=middle指针的时候，middle++右移就行。  如果i>middle的时候,把当前小值置换到左边，middle++
                //也正好指向第一个右边值
                middle ++;
                if(i>middle){
                    //此时middle指针指向的值是右边队列的数，发现i>middle的时候，交换array[i]和array[middle]的值
                    int temp = array[middle];
                    //又指向左边最左队列
                    array[middle] = array[i];
                    array[i] = temp;
                }
            }
        }
        middle ++;
        int temp = array[middle];
        array[middle] = array[end];
        array[end] = temp;

        return middle;
    }


}
