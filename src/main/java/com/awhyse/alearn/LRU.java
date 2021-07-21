package com.awhyse.alearn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by whyse
 * on 2018/5/18 下午9:27
 */
public class LRU {


    static class  A {
        int key;
        int value;
        A front;
        A after;
        long lastUpdateTime;
    }

    public static void main(String[] args) {
        int[][] opts = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};

        System.out.println(LRU1(opts,3));
//        System.out.println(LRU2(opts,3));
    }

//    private static int[] LRU2(int[][] opts, int k) {
//        Map<Integer,A> map = new HashMap(k);
//        LinkedList<Integer> tarList = new LinkedList();
//
//        for(int i=0;i<opts.length;i++){
//            int[] temp = opts[i];
//            //获取map中的值
//            A tempA = map.get(temp[1]);
//
//            if(temp[0] == 1){
//                //插入
//                if(tempA==null) {
//                    tempA = new A();
//                    tempA.key = temp[1];
//                    tempA.value = temp[2];
//                    tempA.lastUpdateTime = i;
//                    map.put(tempA.key,tempA);
//                }else{
//                    tempA.lastUpdateTime = i;
//                }
//            }
//
//            if(temp[0] == 2){
//                //输出
//                if(tempA == null){
//                    tarList.add(-1);
//                }else{
//                    if(k>(i-tempA.lastUpdateTime)){
//
//                    }
//                    tarList.add(tempA.value);
//                    //更新操作
//                    if(!tempA.equals(head)){
//                        if(tempA.equals(tail)){
//                            tail = tail.front;
//                            tail.after = null;
//                        }else{
//                            tempA.after.front = tempA.front;
//                            tempA.front.after = tempA.after;
//                        }
//                        head.front = tempA;
//                        tempA.after = head;
//                        head = tempA;
//                    }
//                }
//
//            }
//
//        }
//
//        int[] tar = new int[tarList.size()];
//        for(int i=0;i<tarList.size();i++){
//            tar[i] = tarList.get(i);
//        }
//        return tar;
//    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public static int[] LRU1 (int[][] operators, int k) {
        // write code here
        Map<Integer,A> map = new HashMap(k);
        LinkedList<Integer> tarList = new LinkedList();

        A head = null;
        A tail  = null;
        for(int i=0;i<operators.length;i++){
            int[] temp = operators[i];
            if(temp[0] == 1){
                //插入
                A tempA = map.get(temp[1]);
                if(tempA == null){
                    //纯插入
                    tempA = new A();
                    tempA.key = temp[1];
                    tempA.value = temp[2];

                    if(map.isEmpty()){
                        head = tempA;
                        tail = tempA;
                    }else{
                        head.front = tempA;
                        tempA.after = head;
                        head = tempA;
                    }
                    map.put(tempA.key,tempA);
                    if(map.size()>k){
                        map.remove(tail.key);
                        tail = tail.front;
                    }
                }else{
                    //更新操作
                    if(!tempA.equals(head)){
                        if(tempA.equals(tail)){
                            tail = tail.front;
                            tail.after = null;
                        }else{
                            tempA.after.front = tempA.front;
                            tempA.front.after = tempA.after;

                        }
                        head.front = tempA;
                        tempA.after = head;
                        head = tempA;
                    }

                }
            }
            if(temp[0] == 2){
                //输出
                A tempA = map.get(temp[1]);
                if(tempA == null){
                    tarList.add(-1);
                }else{
                    tarList.add(tempA.value);
                    //更新操作
                    if(!tempA.equals(head)){
                        if(tempA.equals(tail)){
                            tail = tail.front;
                            tail.after = null;
                        }else{
                            tempA.after.front = tempA.front;
                            tempA.front.after = tempA.after;
                        }
                        head.front = tempA;
                        tempA.after = head;
                        head = tempA;
                    }
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
