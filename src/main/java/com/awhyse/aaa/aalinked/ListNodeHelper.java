package com.awhyse.aaa.aalinked;

import java.util.Random;

/**
 * @author xumin
 * @date 2022-02-14 15:45
 */
public class ListNodeHelper {
    public static void main(String[] args) {
        int size = 6;
        ListNode listNode = genRandomList(size,0);
        ListNode listNode1 = genRandomList(size,1);
        ListNode listNode2 = genRandomList(size,2);
        System.err.println(listNode);
    }

    /**
     * 测试用例用，创建链表
     * @param size 随机链表的长度
     * @param type 0:随机链表  1：有序链表  2：倒叙链表
     * @return
     */
    public static ListNode genRandomList(int size, int type) {
        if(size<=0){
            return null;
        }
        ListNode pHead = null;
        ListNode pTail = null;
        boolean first = true;
        int count = 1;
        while(--size>=0){
            ListNode tempNode = null;
            if(type==0){
                tempNode = new ListNode(new Random().nextInt());
            } else if(type == 1){
                tempNode = new ListNode(count++);
            } else{
                tempNode = new ListNode(size+1);
            }
            if(first){
                pHead = pTail = tempNode;
                first = false;
            }
            pTail.next = tempNode;
            pTail = tempNode;
        }

        return pHead;
    }
}
