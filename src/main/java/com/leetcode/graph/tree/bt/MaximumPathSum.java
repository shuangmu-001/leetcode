package com.leetcode.graph.tree.bt;


/**
 * @author wcl
 * @date 4:34 PM 2020/3/18
 * TODO <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/">
 *     Binary Tree Maximum Path Sum</a>
 *
 * 用long型解决integer数据溢出的问题
 */
public class MaximumPathSum {
    /**
     * Given a non-empty binary tree, find the maximum path sum.
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
     * The path must contain at least one node and does not need to go through the root.
     *
     * Example 1:
     * Input: [1,2,3]
     *        1
     *       / \
     *      2   3
     * Output: 6
     *
     * Example 2:
     * Input: [-10,9,20,null,null,15,7]
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Output: 42
     */
    private int maximum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maximum;
    }
    // -3
    public int maxPathSumHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = maxPathSumHelper(root.left);
        int right = maxPathSumHelper(root.right);
        maximum = Math.max(maximum, left + right + root.val);

        return Math.max(Math.max(left, right) + root.val, 0);
    }


}
