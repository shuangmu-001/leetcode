package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 9:51 PM 2020/3/9
 * {@link "https://leetcode.com/problems/trim-a-binary-search-tree/"}
 */
public class TrimABinarySearchTree {
    /**
     * Given a binary search tree and the lowest and highest boundaries as L and R,
     * trim the tree so that all its elements lies in [L, R] (R >= L).
     * You might need to change the root of the tree,
     * so the result should return the new root of the trimmed binary search tree.
     *
     * Example 1:
     * Input:
     *     1
     *    / \
     *   0   2
     *
     *   L = 1
     *   R = 2
     *
     * Output:
     *     1
     *       \
     *        2
     * Example 2:
     * Input:
     *     3
     *    / \
     *   0   4
     *    \
     *     2
     *    /
     *   1
     *
     *   L = 1
     *   R = 3
     *
     * Output:
     *       3
     *      /
     *    2
     *   /
     *  1
     */
    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if(root != null) {
            TreeNode left = trimBST(root.left, L, R);
            TreeNode right = trimBST(root.right, L, R);
            root.left = left;
            root.right = right;
            if(root.val < L) {
                root = root.right;
            } else if(root.val > R) {
                root = root.left;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root01 = new TreeNode(1);
        TreeNode left11 = new TreeNode(0);
        TreeNode right11 = new TreeNode(2);
        root01.left = left11;
        root01.right = right11;
        System.out.println(trimBST(root01, 1, 2));

        TreeNode root02 = new TreeNode(3);
        TreeNode left21 = new TreeNode(0);
        TreeNode right21 = new TreeNode(4);
        root02.left = left21;
        root02.right = right21;
        TreeNode left23 = new TreeNode(1);
        TreeNode right22 = new TreeNode(2);
        left21.right = right22;
        right22.left = left23;
        System.out.println(trimBST(root02, 1, 3));
    }

}
