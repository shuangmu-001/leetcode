package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 6:24 PM 2020/3/8
 * {@link "https://leetcode.com/problems/increasing-order-search-tree/"}
 */
public class IncreasingOrderSearchTree {
    /**
     * Given a binary search tree,
     * rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
     * and every node has no left child and only 1 right child.
     *
     * Example 1:
     * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
     *
     *        5
     *       / \
     *     3    6
     *    / \    \
     *   2   4    8
     *  /        / \
     * 1        7   9
     *
     * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     *  1
     *   \
     *    2
     *     \
     *      3
     *       \
     *        4
     *         \
     *          5
     *           \
     *            6
     *             \
     *              7
     *               \
     *                8
     *                 \
     *                  9
     * Note:
     *      The number of nodes in the given tree will be between 1 and 100.
     *      Each node will have a unique integer value from 0 to 1000.
     */
    private TreeNode result = new TreeNode(0);
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = result;
        inOrder(root);
        return dummy.right;
    }

    public void inOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            inOrder(root.left);
        }

        result.right = new TreeNode(root.val);
        result = result.right;

        if(root.right != null) {
            inOrder(root.right);
        }
    }

}
