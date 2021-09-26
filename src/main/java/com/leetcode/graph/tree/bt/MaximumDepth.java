package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 8:26 PM 2020/3/9
 * {@link "https://leetcode.com/problems/maximum-depth-of-binary-tree/"}
 */
public class MaximumDepth {
    /**
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     * Example:
     *      Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *      return its depth = 3.
     */
    private int maxDepth;
    public int maxDepth1(TreeNode root) {
        maxDepth = 0;
        postOrder(root, 1);
        return maxDepth;
    }

    public void postOrder(TreeNode root, int depth) {
        if(root != null) {
            if(root.left == null && root.right == null) {
                maxDepth = Math.max(maxDepth, depth);
            }
            int len = depth + 1;
            postOrder(root.left, len);
            postOrder(root.right, len);
        }
    }
    // other
    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }

        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
