package com.awhyse.alearn.shujujiegou.linked;


/**
 * Created by whyse
 * on 2018/5/18 下午9:27
 */
public class LinkedTest {

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    static int arr[] = new int[10];

    public static void main(String[] args) {
        System.out.println("hello");
    }

    /**
     * 描述
     * 输入一个链表，反转链表后，输出新链表的表头。
     * 示例1
     * 输入：
     * {1,2,3}
     * 复制
     * 返回值：
     * {3,2,1}
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        //解题需要先画图，操作指针；
        //遍历的话，先找出口不管是for还是while
        //需要0（1）的变量，然后找到推进方程
        if(head ==null){
            return null;
        }
        ListNode tail = head;
        ListNode temp = null;
        while(tail.next!=null){
            temp = tail.next;
            tail.next = temp.next;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
