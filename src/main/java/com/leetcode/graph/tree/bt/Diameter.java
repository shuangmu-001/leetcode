package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 5:45 PM 2020/3/15
 * <a href="https://leetcode.com/problems/diameter-of-binary-tree/">
 *     Diameter of Binary Tree</a>
 * @see Balanced
 * @see MaximumDepth
 */
public class Diameter {
    /**
     * Given a binary tree, you need to compute the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     *
     * Example:
     * Given a binary tree
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *
     * Note: The length of path between two nodes is represented by the number of edges between them.
     */
    private int maxLength = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxLength;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        maxLength = Math.max(leftDepth + rightDepth, maxLength);
        return Math.max(leftDepth,rightDepth) + 1;
    }
}
