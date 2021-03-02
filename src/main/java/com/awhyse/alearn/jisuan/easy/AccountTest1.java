package com.awhyse.alearn.jisuan.easy;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 账户合并

 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。

 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。

 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/accounts-merge
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * @author xumin
 * @date 2020-11-30 14:26
 */
public class AccountTest1 {

    public static void main(String[] args) {

        String json = "[[\"John\", \"johnsmith@mail.com\", \"john00@mail.com\"], [\"John\", \"johnnybravo@mail.com\"], [\"John\", \"johnsmith@mail.com\", \"john_newyork@mail.com\"], [\"Mary\", \"mary@mail.com\"]]";
//        json = "";


        List<List<String>> accounts = (List<List<String>>) JSON.parse(json);

        System.err.println(accountsMerge(accounts));

    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {

        if(accounts.isEmpty() || accounts.size()<2){
            return accounts;
        }

        // 1. 名字不同肯定不同， 名字相同有可能需要合并
        Map<String, List<Set<String>>>  linkedHashSetMap = new HashMap<>(accounts.size());
        for(List<String> account : accounts){
            String name = account.get(0);
            //新的账户list
            Set<String> accs = new TreeSet<>(account.subList(1,account.size()));

            if(linkedHashSetMap.containsKey(name)){
                //获取同名的账户list
                List<Set<String>> linkedHashSet = linkedHashSetMap.get(name);
                //记录有相同账户的linkedHashSet 下标
                List<Integer> indexs = new ArrayList<>(linkedHashSet.size());
                for(int i=0;i<linkedHashSet.size();i++){
                    Set<String> item = linkedHashSet.get(i);
                    if(hasSame(item,accs)){
                        //合并
                        item.addAll(accs);
                        indexs.add(i);
                    }
                }
                if(indexs.size() == 0){
                    linkedHashSet.add(accs);
                }
                //多个相同得合并
                if(indexs.size()>=2){
                    //获取第一个
                    Set<String> item = linkedHashSet.get(indexs.get(0));
                    //删除剩余其他下标的,注意偏移量改变
                    for(int i=1;i<indexs.size();i++){
                        int index = indexs.get(i)+1-i;
                        Set<String> temp = linkedHashSet.get(index);
                        item.addAll(temp);
                        linkedHashSet.remove(index);
                    }
                }

            }else{
                List<Set<String>> linkedHashSet = new LinkedList<>();
                linkedHashSet.add(accs);

                linkedHashSetMap.put(name,linkedHashSet);
            }
        }

        List<List<String>> lists = new ArrayList<>(accounts.size());
        for(String name : linkedHashSetMap.keySet() ){
            List<Set<String>> item = linkedHashSetMap.get(name);
            for(Set<String> set : item){
                List<String> temp = new LinkedList<>();
                temp.add(name);
                temp.addAll(set);

                lists.add(temp);
            }
        }

        return lists;
    }

    private static boolean hasSame(Set<String> item, Set<String> accs) {
        Set<String> set1 = new HashSet<>(item);
        set1.addAll(accs);
        if(set1.size() == (item.size()+accs.size())){
            return false;
        }
        return true;
    }
}
