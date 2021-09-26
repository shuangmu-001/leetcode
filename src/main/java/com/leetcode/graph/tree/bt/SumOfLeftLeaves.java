package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 11:31 PM 2020/3/13
 * <a href="https://leetcode.com/problems/sum-of-left-leaves/">
 *     Sum of Left Leaves</a>
 */
public class SumOfLeftLeaves {
    /**
     * Find the sum of all left leaves in a given binary tree.
     *
     * Example:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * There are two left leaves in the binary tree,
     * with values 9 and 15 respectively. Return 24.
     */
    private int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum;
    }
}
