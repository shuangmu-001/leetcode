package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;
import edu.princeton.cs.algs4.In;

/**
 * @author wcl
 * @date 12:08 PM 2020/3/21
 * <a href="https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/">
 *     Maximum Sum BST in Binary Tree</a>
 */
public class MaximumSumBSTInBinaryTree {
    /**
     * Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     *
     * Example 1:
     *
     *      Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     *      Output: 20
     *      Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
     *
     * Example 2:
     *      Input: root = [4,3,null,1,2]
     *      Output: 2
     *      Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
     *
     * Example 3:
     *      Input: root = [-4,-2,-5]
     *      Output: 0
     *      Explanation: All values are negatives. Return an empty BST.
     *
     * Example 4:
     *      Input: root = [2,1,3]
     *      Output: 6
     *
     * Example 5:
     *      Input: root = [5,4,8,3,null,6,3]
     *      Output: 7
     *
     * Constraints:
     *      Each tree has at most 40000 nodes..
     *      Each node's value is between [-4 * 10^4 , 4 * 10^4].
     */
    private int max = Integer.MIN_VALUE;
    public int maxSumBST(TreeNode root) {
        maxSumBSTHelper(root);
        return max;
    }

    public BSTInfo maxSumBSTHelper(TreeNode root) {
        if(root == null) {
            return new BSTInfo(null, null, 0);
        }
        BSTInfo left = maxSumBSTHelper(root.left);
        BSTInfo right = maxSumBSTHelper(root.right);
        if(right == null || left == null) {
            return null;
        }
        if(left.max == null && right.min == null) {
            max = Math.max(max, root.val);
            return new BSTInfo(root.val, root.val, root.val);
        }

       if(left.max == null && root.val < right.min) {
            max = Math.max(max, root.val + right.sum);
            return new BSTInfo(root.val, right.max, right.sum + root.val);
       }
       if(right.min == null && root.val > left.max) {
            max = Math.max(max, root.val + left.sum);
            return new BSTInfo(left.min, root.val, left.sum + root.val);
        }
        if(left.max != null && right.min != null && root.val > left.max && root.val < right.min) {
            max = Math.max(max, root.val + left.sum + right.sum);
            return new BSTInfo(left.min, right.max, root.val + left.sum + right.sum);
        }

        return null;
    }
    static class BSTInfo {

        Integer min;
        Integer max;
        int sum;

        public BSTInfo(Integer min, Integer max, int sum) {
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }
}
