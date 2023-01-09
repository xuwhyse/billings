package com.awhyse.aaa.aalinked;

/**
 * @author xumin
 * @date 2022-03-04 16:36
 */
public class ListCommon {

    public static void main(String[] args) {
        //链表反转例子
        reverseCase();




    }

    private static void reverseCase() {

        ListNode header = ListNodeHelper.genRandomList(7,2);
        header = getEasyReverse(header);
        System.err.println(header);

    }

    /**
     * 链表反转，使用头插法最简单
     * @param header
     * @return
     */
    private static ListNode getEasyReverse(ListNode header) {
        ListNode nHeader = new ListNode(-1);
        while(header!=null){
            ListNode temp = header;
            header = header.next;
            if(nHeader.next == null){
                nHeader.next = temp;
                //尾节点要置空处理，注意
                temp.next = null;
            }else{
                temp.next = nHeader.next;
                nHeader.next = temp;
            }
        }
        return nHeader.next;
    }
}
