package com.awhyse.aaa.aalinked;

/**
 * 输入一个链表，输出链表倒数第k个值
 *
 * 举一反三： 链表双指针或者多指针遍历，还有更多玩法； 比如指针是否成环； 获取链表的中间node(也是一快，一慢指针走)
 *
 * @author xumin
 * @date 2022-02-14 15:34
 */
public class FindKthToTail {

    public static void main(String[] args){

        System.err.println(FindKthToTailDo(ListNodeHelper.genRandomList(7,1),1));
        System.err.println(FindKthToTailDo(ListNodeHelper.genRandomList(7,1),2));
        System.err.println(FindKthToTailDo(ListNodeHelper.genRandomList(7,1),7));
        System.err.println(FindKthToTailDo(ListNodeHelper.genRandomList(7,1),8));
        System.err.println(FindKthToTailDo(ListNodeHelper.genRandomList(12,1),2));
        
    }

    /**
     * 输入一个链表，输出链表倒数第k个值
     * @param pHead
     * @param k
     * @return
     * 考察点: 输入的鲁棒性
     */
    private static int FindKthToTailDo(ListNode pHead, int k) {
        if(pHead == null || k<=0){
            return -1;
        }
        //思路：双指针，p1往前走 k-1步；此时pk指向head; 双指针往后走，直到p1到尾，此时pk所指就是目标
        ListNode p1 = pHead;
        ListNode pk = pHead;
        //当前步数
        int count = 0;

        while(p1.next!=null){
            if(count < k-1){
                count++;
                p1 = p1.next;
            }else{
                p1 = p1.next;
                pk = pk.next;
            }
        }
        if(count!= k-1){
            System.err.println("k值超过 list size");
            return -1;
        }

        return pk.value;
    }
}
