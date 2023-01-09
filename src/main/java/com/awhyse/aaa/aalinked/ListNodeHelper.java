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
        ListNode listNode1 = genRandomList(3,1);
        ListNode listNode2 = genRandomList(6,1);

        listNode = getMergeList(listNode1,listNode2);
        System.err.println(listNode);
    }

    private static ListNode getMergeList(ListNode linkedNode1, ListNode linkedNode2) {

        ListNode head = new ListNode(-1);
        ListNode pCur = head;
        ListNode pCur1 = linkedNode1;
        ListNode pCur2 = linkedNode2;

        while(pCur1!=null && pCur2!=null){
            if(pCur1.value<=pCur2.value){
                pCur.next = pCur1;
                pCur1 = pCur1.next;
            }else{
                pCur.next = pCur2;
                pCur2 = pCur2.next;
            }
            pCur = pCur.next;
        }
        if(pCur1 == null && pCur2!=null){
            pCur.next = pCur2;
            return head;
        }

        if(pCur2 == null && pCur1!=null){
            pCur.next = pCur1;
            return head;
        }
        return head;
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
