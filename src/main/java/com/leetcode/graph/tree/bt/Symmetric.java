package com.leetcode.graph.tree.bt;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/symmetric-tree/">Symmetric Tree</a>
 *
 * @author zms
 * @date 9:43 PM 2020/3/15
 */
public class Symmetric {
    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * But the following [1,2,2,null,3,null,3] is not:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * <p>
     * <p>
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (right == null || left == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric01(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root.left);
        nodes.add(root.right);
        while (!nodes.isEmpty()) {
            TreeNode left = nodes.pollFirst();
            TreeNode right = nodes.pollFirst();
            if (!isSame(left, right)) {
                return false;
            }
            if (left != null && right != null) {
                nodes.add(left.left);
                nodes.add(right.right);
                nodes.add(left.right);
                nodes.add(right.left);
            }
        }
        return true;
    }

    public boolean isSame(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        return left.val == right.val;
    }

}
