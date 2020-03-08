package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;


/**
 * @author wcl
 * @date 9:02 PM 2020/2/29
 * {@link "https://leetcode.com/problems/range-sum-of-bst/"}
 * @see com.leetcode.graph.tree.TreeTraversal
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
     *
     */
    private static int result;
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

        result = 0;
        inOrder(root, L, R, result);

        return result;
}

    public static void inOrder(TreeNode root, int L, int R, int result) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            inOrder(root.left, L, R, result);
        }

        if(root.val >= L && root.val <= R) {
            result += root.val;
        }

        if(root.right != null) {
            inOrder(root.right, L, R, result);
        }
    }


    public static void preOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.println(root.val);
        if(root.left != null) {
            preOrder(root.left);
        }

        if(root.right != null) {
            preOrder(root.right);
        }
    }

    public static void postOrder(TreeNode root) {
        if(root == null) {
            return;
        }

        if(root.left != null) {
            postOrder(root.left);
        }

        if(root.right != null) {
            postOrder(root.right);
        }
        System.out.println(root.val);
    }
}
