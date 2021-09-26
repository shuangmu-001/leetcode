package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 6:48 PM 2020/3/16
 * <a href="https://leetcode.com/problems/insert-into-a-binary-search-tree/">
 *     Insert into a Binary Search Tree</a>
 */
public class Insert {
    /**
     * Given the root node of a binary search tree (BST) and a value to be inserted into the tree,
     * insert the value into the BST.
     * Return the root node of the BST after the insertion.
     * It is guaranteed that the new value does not exist in the original BST.
     * Note that there may exist multiple valid ways for the insertion,
     * as long as the tree remains a BST after insertion. You can return any of them.
     *
     * For example,
     *
     * Given the tree:
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     * And the value to insert: 5
     * You can return this binary search tree:
     *
     *          4
     *        /   \
     *       2     7
     *      / \   /
     *     1   3 5
     * This tree is also valid:
     *
     *          5
     *        /   \
     *       2     7
     *      / \
     *     1   3
     *          \
     *           4
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode dummy = root;
        TreeNode before = root;
        while(root != null) {
            before = root;
            if(root.val > val) {
                root = root.left;
            } else if(root.val < val) {
                root = root.right;
            }
        }
        if(before != null ) {
            if(before.val < val) {
                before.right = new TreeNode(val);
            } else {
                before.left = new TreeNode(val);
            }
        }
        return dummy;
    }
}
