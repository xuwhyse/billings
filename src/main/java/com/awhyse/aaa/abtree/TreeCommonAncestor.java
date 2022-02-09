package com.awhyse.aaa.abtree;

import java.util.Stack;

/**
 * @author xumin
 * @date 2021-08-10 18:00
 */
public class TreeCommonAncestor {


    public class Solution {
        /**
         *
         * @param root TreeNode类
         * @param o1 int整型
         * @param o2 int整型
         * @return int整型
         */
        public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
            // write code here
            Stack<TreeNode> o1Path=new Stack();
            Stack<TreeNode>o2Path=new Stack();
            getPath(root,o1,o1Path);
            getPath(root,o2,o2Path);
            int father=root.val;//第一个元素必定是root，两个栈第一个元素必相等
            while(!(o1Path.isEmpty()||o2Path.isEmpty())){
                int f1=o1Path.pop().val;
                int f2=o2Path.pop().val;
                if(f1!=f2) {
                    break;
                }
                else {
                    father = f1;
                }
            }
            return father;
        }

        /**
         * 这个递归树找父亲很重要 ！！
         * @param node
         * @param target
         * @param pathStack
         * @return
         */
        boolean getPath(TreeNode node,int target,Stack<TreeNode>pathStack){
            if(node == null) {
                return false;
            }
            if(node.val==target//重点在于这个三合一的判定条件
                    ||getPath(node.left,target,pathStack)
                    ||getPath(node.right,target,pathStack)){
                pathStack.push(node);
                return true;
            }
            return false;
        }

    }

}
