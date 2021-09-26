package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 1:32 PM 2020/3/9
 * {@link "https://leetcode.com/problems/univalued-binary-tree/"}
 */
public class UnivaluedBinaryTree {
    /**
     * A binary tree is univalued if every node in the tree has the same value.
     * Return true if and only if the given tree is univalued.
     *
     * Example 1:
     *      Input: [1,1,1,1,1,null,1]
     *      Output: true
     *
     * Example 2:
     *      Input: [2,2,2,5,2]
     *      Output: false
     *
     * Note:
     *      The number of nodes in the given tree will be in the range [1, 100].
     *      Each node's value will be an integer in the range [0, 99].
     */
    private boolean result = true;
    public boolean isUnivalTree(TreeNode root) {
        result = true;
        inOrder(root, root.val);
        return result;
    }
    public void inOrder(TreeNode root, int target) {
        if(!result || root == null) {
            return;
        }
        if(root.left != null) {
            inOrder(root.left, target);
        }
        if(root.val != target) {
            result = false;
            return;
        }
        if(root.right != null) {
            inOrder(root.right, target);
        }
    }

    private boolean isUnivalTreeHelper(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return isUnivalTreeHelper(root.left, val) && isUnivalTreeHelper(root.right, val);
    }
}
