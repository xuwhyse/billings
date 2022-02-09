package com.awhyse.aaa.aalinked;

import java.util.Stack;

/**
 * 单链表，逆序输出
 * @author xumin
 * @date 2022-01-14 16:54
 */
public class LinkListReversPrint {

    public static void main(String[] args) {
        //空链表
        reverseListPrint(new LinkListT());

        System.err.println("---------");

        LinkListT list = new LinkListT();
        list.insert(1);
        //单节点
        reverseListPrint(list);

        System.err.println("---------");

        list.insert(2);
        list.insert(3);
        list.insert(4);
        //多节点
        reverseListPrint(list);



    }

    /**
     * 考察对循环，递归，栈的三个关联概念
     * @param list
     */
    private static void reverseListPrint(LinkListT list) {
        if(list==null || list.head==null){
            return;
        }
        //正向遍历反向输出，就是栈stack
        Stack<Integer> stack = new Stack<>();
        ListNode pNode = list.head;
        while(pNode!=null){
            stack.push(pNode.value);
            pNode = pNode.next;
        }
        while (!stack.empty()){
            System.err.println(stack.pop());
        }
    }
}
