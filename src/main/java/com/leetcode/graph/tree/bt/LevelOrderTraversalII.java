package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 6:11 PM 2020/3/13
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/">
 *     Binary Tree Level Order Traversal II</a>
 */
public class LevelOrderTraversalII {
    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
     * (ie, from left to right, level by level from leaf to root).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *    // 自底向上
     * return its bottom-up level order traversal as:
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<>();
        traversal(root, 0, nodes);
        // 用LinkedList可以从头和尾两头添加
        Collections.reverse(nodes);
        return nodes;
    }

    public void traversal(TreeNode root, int depth, List<List<Integer>> nodes) {
        if (root != null) {
            if (nodes.size() <= depth) {
                nodes.add(new ArrayList<>());
            }
            int newDepth = depth + 1;
            traversal(root.left, newDepth, nodes);
            nodes.get(depth).add(root.val);
            traversal(root.right, newDepth, nodes);
        }
    }
}
