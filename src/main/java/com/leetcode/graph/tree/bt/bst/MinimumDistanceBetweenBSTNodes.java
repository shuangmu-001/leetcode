package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 6:07 PM 2020/3/13
 * {@link "https://leetcode.com/problems/minimum-distance-between-bst-nodes/"}
 * @see MinimumAbsoluteDifference
 */
public class MinimumDistanceBetweenBSTNodes {
    /**
     * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
     *
     * Example :
     *
     * Input: root = [4,2,6,1,3,null,null]
     * Output: 1
     * Explanation:
     * Note that root is a TreeNode object, not an array.
     *
     * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
     *
     *           4
     *         /   \
     *       2      6
     *      / \
     *     1   3
     *
     * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
     * Note:
     *
     * The size of the BST will be between 2 and 100.
     * The BST is always valid, each node's value is an integer, and each node's value is different.
     */
    private int min = Integer.MAX_VALUE;
    private int before = 0;
    private boolean first = true;
    public int minDiffInBST(TreeNode root) {
        if(root != null) {
            minDiffInBST(root.left);
            if(first) {
                before = root.val;
                first = false;
            } else {
                min = Math.min(root.val - before, min);
                before = root.val;
            }
            minDiffInBST(root.right);
        }
        return min;
    }
}
