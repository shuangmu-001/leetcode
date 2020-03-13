package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 11:46 AM 2020/3/10
 * {@link "https://leetcode.com/problems/invert-binary-tree/"}
 */
public class InvertBinaryTree {
    /**
     * Invert a binary tree.
     * Example:
     * Input:
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * Output:
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * Trivia:
     * This problem was inspired by this original tweet by Max Howell:
     *     Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
     */
    public TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

}
