package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zms
 * @date 11:19 PM 2020/3/14
 * <a href="https://leetcode.com/problems/balanced-binary-tree/">
 *     Balanced Binary Tree</a>
 * @see MaximumDepth
 */
public class Balanced {
    /**
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as:
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     *
     * Example 1:
     *      Given the following tree [3,9,20,null,null,15,7]:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *      Return true.
     *
     * Example 2:
     *      Given the following tree [1,2,2,3,3,null,null,4,4]:
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     *      Return false.
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(Math.abs(maxDepth(poll.left) - maxDepth(poll.right)) > 1) {
                return false;
            }

            if(poll.left != null) {
                queue.add(poll.left);
            }

            if(poll.right != null) {
                queue.add(poll.right);
            }

        }

        return true;
    }

    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    // other
    public int balanceHelper(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = balanceHelper(root.left);
        int rightHeight = balanceHelper(root.right);
        if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
