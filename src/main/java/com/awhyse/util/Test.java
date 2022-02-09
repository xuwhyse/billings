package com.awhyse.util;

/**
 * @author xumin
 * @date 2021-08-02 20:41
 */
public class Test {
    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value){
            this.value = value;
        }
    }
    static TreeNode root;

    static {
        root = new TreeNode(-1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(11);
        root.left.right = new TreeNode(12);

        root.right.left = new TreeNode(21);
        root.right.right = new TreeNode(22);
    }

    public static void main(String[] args) {

        System.out.println(root.value);
        printTree(root);

    }

    private static void printTree(TreeNode node) {
        if(node==null){
            return;
        }
        if(node.left!=null) {
            System.out.println(node.left.value);
        }
        if(node.right!=null) {
            System.out.println(node.right.value);
        }
        printTree(node.left);
        printTree(node.right);
    }
}
