package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 1:49 PM 2020/3/13
 * {@link ""}
 */
public class MinimumAbsoluteDifference {
    /**
     * Given a binary search tree with non-negative values,
     * find the minimum absolute difference between values of any two nodes.
     *
     * Example:
     * Input:
     *    1
     *     \
     *      3
     *     /
     *    2
     * Output:1
     * Explanation:
     *      The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
     *
     * Note:There are at least two nodes in this BST.
     */
    private int min = Integer.MAX_VALUE;
    private int before = 0;
    private boolean first = true;
    public int getMinimumDifference(TreeNode root) {
        if(root != null) {
            getMinimumDifference(root.left);
            if(first) {
                before = root.val;
                first = false;
            } else {
                min = Math.min(root.val - before, min);
                before = root.val;
            }
            getMinimumDifference(root.right);
        }
        return min;
    }
}
