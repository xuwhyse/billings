package com.awhyse.aaa.aalinked;

/**
 * 链表插入，遍历输出，单链反转
 * @author xumin
 * @date 2022-01-10 15:16
 */
public class LinkListT {

    public ListNode head;

    private ListNode tail;

    public void insert(int value){
        if(tail == null){
            head = new ListNode(value);
            tail = head;
            return;
        }

        tail.next = new ListNode(value);
        tail = tail.next;
    }

    public boolean delete(int value){
        ListNode temp = head;
        if(head.value==value){
            head = head.next;
            temp.next = null;
            return true;
        }

        while(temp.next!=null){
            if(temp.next.value==value){
                temp.next = temp.next.next;
                temp.next.next = null;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void printAll(){
        ListNode temp = head;
        while(temp!=null){
            System.err.println(temp.value);
            temp = temp.next;
        }
    }

    /**
     * 反转单链表
     */
    public void reverseList(){
        if(head==null){
            return;
        }
        //思路，尾即是投，尾后面为null时退出； 否则逐个头插入队列
        tail = head;
        while(tail.next!=null){
            ListNode temp = tail.next;
            tail.next = temp.next;
            temp.next = head;
            head = temp;
        }
    }

    public static void main(String[] args) {
        test0();
        System.err.println("---------");
        test1();
        System.err.println("---------");
        test2();
        System.err.println("---------");
        test3Delete();
    }

    private static void test3Delete() {
        LinkListT list = new LinkListT();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        list.delete(1);

//        list.reverseList();

        list.printAll();
    }

    private static void test0() {
        LinkListT list = new LinkListT();
        list.printAll();

        list.reverseList();
        list.printAll();
    }

    private static void test2() {
        LinkListT list = new LinkListT();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.reverseList();

        list.printAll();
    }

    private static void test1() {
        LinkListT list = new LinkListT();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.printAll();
    }

}
