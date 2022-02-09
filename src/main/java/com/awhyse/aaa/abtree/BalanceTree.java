package com.awhyse.aaa.abtree;

/**
 * @author xumin
 * @date 2021-08-10 20:51
 */
public class BalanceTree {

    /**
     * 平衡二叉树要求：左右子树的深度差不超过1，并且左右子树也是平衡二叉树(空树也可以理解成平衡二叉树)
     */
    public class Solution {
        public boolean IsBalanced_Solution(TreeNode root) {
            if(root==null) {
                return true;
            }
            return (Math.abs(depth(root.left)-depth(root.right))<=1)
                    && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
        }

        /**
         * 求树的层高
         * 也可以使用队列非空，自定义数据结构，求得树的最高值
         * @param root
         * @return
         */
        public int depth(TreeNode root){
            if(root==null) {
                return 0;
            }
            return Math.max(depth(root.left),depth(root.right))+1;
        }
    }

}
