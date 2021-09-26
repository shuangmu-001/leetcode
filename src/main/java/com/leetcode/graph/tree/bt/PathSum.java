package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 7:09 PM 2020/3/15
 * <a href="https://leetcode.com/problems/path-sum/">
 *     Path Sum</a>
 * @see SumOfRootToLeafBinaryNumbers
 */
public class PathSum {
    /**
     * Given a binary tree and a sum,
     * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */
    public boolean result = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        hasPathSumHelper(root, 0, sum);
        return result;
    }

    public void hasPathSumHelper(TreeNode root, int before, int sum) {
        if(root == null || result) {
            return;
        }
        before += root.val;
        if(root.left != null) {
            hasPathSumHelper(root.left, before, sum);
        }

        if(root.right != null) {
            hasPathSumHelper(root.right, before, sum);
        } else if(root.left == null) {
            result = before == sum;
        }
    }
}
