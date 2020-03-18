package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 3:53 PM 2020/3/18
 * <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">
 *     Flatten Binary Tree to Linked List</a>
 * @see MorrisTraversal
 */
public class FlattenBTtoLinkedList {
    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * For example, given the following tree:
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * The flattened tree should look like:
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    public void flatten(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if(curr.left == null) {
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr.right;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
                temp.right = curr;
            }
        }
    }
}
