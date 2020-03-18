package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 10:15 AM 2020/3/18
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/">
 *     Validate Binary Search Tree</a>
 */
public class Validate {
    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * Example 1:
     *     2
     *    / \
     *   1   3
     * Input: [2,1,3]
     * Output: true
     *
     * Example 2:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * Input: [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     */
    private boolean valid = true;
    private Integer before;
    public boolean isValidBST(TreeNode root) {
        inorder(root);
        return valid;
    }

    public void inorder(TreeNode root) {
        if(root == null || !valid) {
            return;
        }
        inorder(root.left);
        if(before != null && before >= root.val) {
            valid = false;
        }
        before = root.val;
        inorder(root.right);
    }

}
