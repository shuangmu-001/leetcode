package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 3:58 PM 2020/3/23
 * <a href="https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/">
 *     Flip Binary Tree To Match Preorder Traversal</a>
 */
public class FlipBinaryTreeToMatchPreorderTraversal {
    /**
     * Given a binary tree with N nodes, each node has a different value from {1, ..., N}.
     * A node in this binary tree can be flipped by swapping the left child and the right child of that node.
     * Consider the sequence of N values reported by a preorder traversal starting from the root.
     * Call such a sequence of N values the voyage of the tree.
     * (Recall that a preorder traversal of a node means we report the current node's value,
     * then preorder-traverse the left child, then preorder-traverse the right child.)
     * Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.
     * If we can do so, then return a list of the values of all nodes flipped.
     * You may return the answer in any order.
     * If we cannot do so, then return the list [-1].
     *
     * Example 1:
     *      Input: root = [1,2], voyage = [2,1]
     *      Output: [-1]
     *
     * Example 2:
     *      Input: root = [1,2,3], voyage = [1,3,2]
     *      Output: [1]
     *
     * Example 3:
     *      Input: root = [1,2,3], voyage = [1,2,3]
     *      Output: []
     *
     * Note:1 <= N <= 100
     */
    private boolean match = true;
    private int start = 0;
    public List<Integer> flipMatchVoyage1(TreeNode root, int[] voyage) {
        if(root == null) {
            return new ArrayList<>();
        }
        if(voyage == null || voyage.length == 0 ) {
            return Collections.singletonList(-1);
        }
        List<Integer> result = new ArrayList<>();
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;
        preorder(root, voyage, result, dummy, false);
        return match ? result : Collections.singletonList(-1);
    }
    public void preorder(TreeNode root, int[] voyage, List<Integer> result, TreeNode parent, boolean flag) {
        if(root == null || !match || start >= voyage.length) {
            return;
        }
        if(voyage[start] != root.val) {
            if(flag) {
                if(parent.right == null || parent.right.val != voyage[start]) {
                    match = false;
                } else {
                    parent.left = parent.right;
                    parent.right = root;
                    root = parent.left;
                    result.add(parent.val);
                }
            } else {
                match = false;
            }
        }
        start++;
        preorder(root.left, voyage, result, root, true);
        preorder(root.right, voyage, result, root, false);

    }

    List<Integer> res = new ArrayList<>();
    int index = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return dfs(root, voyage) ? res : Collections.singletonList(-1);
    }

    private boolean dfs(TreeNode node, int[] voyage) {
        if (node == null) {
            return true;
        }
        if (node.val != voyage[index++]) {
            return false;
        }
        if (node.left != null && node.left.val != voyage[index]) {
            res.add(node.val);
            return dfs(node.right, voyage) && dfs(node.left, voyage);
        } else {
            return dfs(node.left, voyage) && dfs(node.right, voyage);
        }
    }
}
