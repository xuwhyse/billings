package com.awhyse.aaa.stack;

import java.util.Stack;

/**
 * 不依靠new任何对象，让栈反转
 * @author xumin
 * @date 2022-05-18 20:39
 */
public class BStackReverse {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        for(int i = stack.size()-1;i>0;i--){
            int high = i;
            int tar = stack.pop();
            swapTop(stack,high,tar);
        }

        System.err.println(stack);
    }

    /**
     * 每次只能将 tar这个目标数据，送到指定的high+1的深度; 保持原stack位置不变
     * @param stack
     * @param high 递归函数需要递归的深度
     * @param tar  头部数据，往下传的目标替换数据
     */
    private static void swapTop(Stack<Integer> stack, int high, int tar) {
        if(high>0){
            high -- ;
            int item = stack.pop();
            swapTop(stack,high,tar);

            stack.push(item);
            return;
        }
        stack.push(tar);
    }
}
