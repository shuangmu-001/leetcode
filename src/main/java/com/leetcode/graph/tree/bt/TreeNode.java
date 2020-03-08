package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 8:52 PM 2020/2/29
 * Definition for a binary tree node.
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
