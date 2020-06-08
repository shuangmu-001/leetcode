package com.leetcode.graph.tree.bt;

/**
 * @author wcl
 * @date 9:24 AM 2020/3/19
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">
 *     Lowest Common Ancestor of a Binary Tree</a>
 * @see com.leetcode.graph.tree.bt.bst.LowestCommonAncestor
 * TODO 代码优化
 */
public class LowestCommonAncestor {
    /**
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * According to the definition of LCA on Wikipedia:
     * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     * Example 1:
     *      Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     *      Output: 3
     *      Explanation: The LCA of nodes 5 and 1 is 3.
     *
     * Example 2:
     *      Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     *      Output: 5
     *      Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
     *
     *
     * Note:
     *
     * All of the nodes' values will be unique.
     * p and q are different and both values will exist in the binary tree.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor(root, p, q, root);
    }
    boolean pFlag = false;
    boolean qFlag = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, TreeNode ancestor) {
        if(root == null) {
            return null;
        }
        if(root.val == p.val) {
            pFlag = true;
        }
        if(root.val == q.val) {
            qFlag = true;
        }
        TreeNode left;
        TreeNode right;
        if(pFlag && qFlag) {
            return ancestor;
        }
        if(pFlag || qFlag) {
            left = lowestCommonAncestor(root.left, p, q, ancestor);
        } else {
            left = lowestCommonAncestor(root.left, p, q, root.left);
        }

        if(pFlag || qFlag) {
            right = lowestCommonAncestor(root.right, p, q, ancestor);
        } else {
            right = lowestCommonAncestor(root.right, p, q, root.right);
        }
        return left != null ? left : right;
    }
}
