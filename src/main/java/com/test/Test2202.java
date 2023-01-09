package com.test;

/**
 * @author xumin
 * on 2022/2/25 下午5:00
 */
public class Test2202 {

    public static void main(String[] args) {
        LinkedNode linkedNode1 = null;
        LinkedNode linkedNode2 = null;
        
        linkedNode1  = getMergeList(linkedNode1,linkedNode2);
    }

    /**
     *
     * @param linkedNode1
     * @param linkedNode2
     * @return
     */
    private static LinkedNode getMergeList(LinkedNode linkedNode1, LinkedNode linkedNode2) {

        LinkedNode head = new LinkedNode();
        LinkedNode pCur = head;
        LinkedNode pCur1 = linkedNode1;
        LinkedNode pCur2 = linkedNode2;

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

}
