package com.test;

import com.awhyse.util.HTMLParse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by whyse
 * on 2018/5/28 下午5:09
 */
public class TestMsgBoard {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(10);
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");

        int pages = 2;
        int times = set.size()/pages;
        List<String> listTar= new ArrayList<>(set);
        int i=0;
        for(;i<times;i++){
            List<String> item = listTar.subList(i*pages,(i+1)*pages);
            System.err.print(item);
            HTMLParse.insertIntoTBFor1(item);
        }
        if(set.size()%pages!=0){
            List<String> item = listTar.subList(i*pages,set.size());
            System.err.print(item);
            HTMLParse.insertIntoTBFor1(item);
        }
    }
}
