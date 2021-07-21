package com.awhyse.alearn.paixu;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;


@Slf4j
public class ExceptionTest {

    public static void main(String[] args) {
        int[] arr = {5,2,3,1,4};
        MySort(arr);
        System.err.println(arr);
    }

    public static int[] MySort (int[] arr) {
        // write code here
        sortT(arr,0,arr.length-1);
        return arr;
    }

    public static void sortT (int[] arr,int begin, int end) {

        if(begin >= end){
            return;
        }

        ArrayList<Integer> leftList = new ArrayList(end - begin);
        ArrayList<Integer> rightList = new ArrayList(end - begin);
        int base = arr[begin];
        for(int i=begin+1;i<=end;i++){
            if(base > arr[i]){
                leftList.add(arr[i]);
            }else{
                rightList.add(arr[i]);
            }
        }

        for(int i=0;i<leftList.size();i++){
            arr[begin+i] = leftList.get(i);
        }
        arr[begin+leftList.size()] = base;
        for(int i=0;i<rightList.size();i++){
            arr[begin+leftList.size()+i+1] = rightList.get(i);
        }

        sortT(arr,begin,begin+leftList.size()-1);
        sortT(arr,begin+leftList.size()+1,end);
    }


}
