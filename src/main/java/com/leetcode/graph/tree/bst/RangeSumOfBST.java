package com.leetcode.graph.tree.bst;

import com.leetcode.graph.tree.TreeNode;

/**
 * @author wcl
 * @date 9:02 PM 2020/2/29
 * {@link "https://leetcode.com/problems/range-sum-of-bst/"}
 * 1、遍历所有节点
 * 2、求所有节点的和
 */
public class RangeSumOfBST {
    /**
     * Given the root node of a binary search tree,
     * return the sum of values of all nodes with value between L and R (inclusive).
     * The binary search tree is guaranteed to have unique values.
     *
     * Example 1:
     *      Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
     *      Output: 32
     *
     * Example 2:
     *      Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
     *      Output: 23
     *
     * Note:
     *     The number of nodes in the tree is at most 10000.
     *     The final answer is guaranteed to be less than 2^31.
     */
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if(L > R) {
            return 0;
        }

        while(root.val > R) {
            root = root.left;
        }

        while(root.val < L) {
            root = root.right;
        }

        TreeNode left = root.val == L ? root : root.left;
        TreeNode right = root.val == R ? root : root.right;
        int result = 0;
        while(root.val != L) {
            if(left.val < L) {
                left = left.right;
            } else if (left.val > L){
                result += left.val;
                left = left.left;
            } else {
                result += left.val;
                break;
            }
        }
        while(root.val != R) {
            if(right.val < R) {
                result += right.val;
                right = right.right;
            } else if (right.val > R){
                right = right.left;
            } else {
                result += right.val;
                break;
            }
        }
        result += root.val;
        return result;
    }

    public static int allNodeSum(TreeNode root) {

    }

    public static void main(String[] args) {

    }
}
