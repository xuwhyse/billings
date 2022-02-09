package com.awhyse.alearn;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 *
 *
 * 输入：
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 * 复制
 * 返回值：
 * [1,-1]
 * 复制
 * 说明：
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 * Created by whyse
 * on 2018/5/18 下午9:27
 */
public class LRUT {


    public static void main(String[] args) {
        int[][] opts = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        LRUT lru = new LRUT();
        int[] tar = lru.LRU1(opts,3);
        System.out.println(tar);
    }

    /**
     * lru design
     *  概要 自定义node；linkedList + map 结构; put: map中没有key就头插入,有的话拿出来再头插入； get:没有就是-1，有的话拿出来头插入
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU1 (int[][] operators, int k) {
        // write code here
        //map 的keySet 是有序的链表，集成自hashmap,且node维护了双相链表，也有虚头和尾保证顺序
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap();

        LinkedList<Integer> tarList = new LinkedList<>();


        for(int i=0;i<operators.length;i++){
            int[] temp = operators[i];
            int key = temp[1];
            if(temp[0] == 1){
                //插入
                if(map.size()<k){
                    map.put(key, temp[2]);
                }else{
                    int keyFirst = map.keySet().iterator().next();
                    map.remove(keyFirst);
                    map.put(key, temp[2]);
                }
            }
            if(temp[0] == 2){
                //get
                Integer item = map.get(key);
                if(item==null){
                    tarList.add(-1);
                }else{
                    map.remove(key);
                    map.put(key,item);
                    tarList.add(item);
                }
            }

        }


        int[] tar = new int[tarList.size()];
        for(int i=0;i<tarList.size();i++){
            tar[i] = tarList.get(i);
        }
        return tar;
    }

}
