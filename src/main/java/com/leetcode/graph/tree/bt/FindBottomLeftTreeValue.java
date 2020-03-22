package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 10:10 PM 2020/3/22
 * <a href="https://leetcode.com/problems/find-bottom-left-tree-value/">
 *     Find Bottom Left Tree Value</a>
 */
public class FindBottomLeftTreeValue {
    /**
     * Given a binary tree, find the leftmost value in the last row of the tree.
     * Example 1:
     * Input:
     *     2
     *    / \
     *   1   3
     * Output: 1
     *
     * Example 2:
     * Input:
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   5   6
     *        /
     *       7
     * Output:7
     * Note: You may assume the tree (i.e., the given root node) is not NULL.
     */
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueHelper(root, 1);
        return result;
    }
    private int max;
    private int result;
    public void findBottomLeftValueHelper(TreeNode root, int level) {
        if(root == null) {
            return;
        }
        if(level > max) {
            result = root.val;
            max = level;
        }
        findBottomLeftValueHelper(root.left, level + 1);
        findBottomLeftValueHelper(root.right, level + 1);
    }
}
