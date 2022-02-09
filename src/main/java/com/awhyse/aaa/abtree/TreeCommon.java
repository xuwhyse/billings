package com.awhyse.aaa.abtree;

import java.util.ArrayList;

/**
 * @author xumin
 * @date 2021-08-10 20:51
 */
public class TreeCommon {

    /**
     * 求叶子节点到根之和为指定值的路径的集合
     * @param root TreeNode类
     * @param sum int整型
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        // write code here
        ArrayList<ArrayList<Integer>> tar = new ArrayList();
        if(root != null){
            ArrayList<Integer> temp = new ArrayList();
            isPath(root,sum,tar,temp);
        }

        return tar;
    }

    private boolean isPath(TreeNode root, int sum, ArrayList<ArrayList<Integer>> tar, ArrayList<Integer> temp) {
        if(root.left == null && root.right==null && root.val == sum){
            temp.add(root.val);
            tar.add(temp);
            return true;
        }
        if(root.left!=null){
            ArrayList<Integer> tempL = new ArrayList(temp);
            tempL.add(root.val);
            isPath(root.left,sum-root.val,tar,tempL);
        }
        if(root.right!=null){
            ArrayList<Integer> tempR = new ArrayList(temp);
            tempR.add(root.val);
            isPath(root.right,sum-root.val,tar,tempR);
        }
        return false;
    }

}
