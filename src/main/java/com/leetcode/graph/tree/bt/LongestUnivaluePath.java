package com.leetcode.graph.tree.bt;

import com.Utils;

/**
 * @author zms
 * @date 10:27 PM 2020/3/15
 * TODO <a href="https://leetcode.com/problems/longest-univalue-path/">
 *      Longest Univalue Path</a>
 */
public class LongestUnivaluePath {
    /**
     * Given a binary tree,
     * find the length of the longest path where each node in the path has the same value.
     * This path may or may not pass through the root.
     * The length of path between two nodes is represented by the number of edges between them.
     *
     * Example 1:
     * Input:
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     * Output: 2
     *
     * Example 2:
     * Input:
     *               1
     *              / \
     *             4   5
     *            / \   \
     *           4   4   5
     * Output: 2
     *
     * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
     */
    static int max = 0;
    public static int longestUnivaluePath(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    public static int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(left, max);
        max = Math.max(right, max);

        if(root.right != null && root.left != null
                && root.val == root.left.val && root.val == root.right.val) {
            max = Math.max(max, left + right + 1);
            return Math.max(left, right)+ 1;
        }

        if(root.right != null && root.val == root.right.val) {
            max = Math.max(max, right + 1);
            return right + 1;
        }
        if(root.left != null && root.val == root.left.val) {
            max = Math.max(max, left + 1);
            return left + 1;
        }

        return 0;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        max = Math.max(max, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    public static void main(String[] args) {
        TreeNode treeNode = Utils.arrayToTreeNode(new Integer[]{5,5,5});
        System.out.println(longestUnivaluePath(treeNode));

//        treeNode = Utils.arrayToTreeNode(new Integer[]{5,5,5,5,5,3,5,5});
//        System.out.println(longestUnivaluePath(treeNode));
    }
}
