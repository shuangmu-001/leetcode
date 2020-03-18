package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcl
 * @date 2:11 PM 2020/3/18
 * <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/">
 *     Binary Tree Zigzag Level Order Traversal</a>
 *
 * @see LevelOrderTraversalII
 * @see BTLevelOrderTraversal
 */
public class ZigzagLevelOrderTraversal {
    /**
     * Given a binary tree,
     * return the zigzag level order traversal of its nodes' values.
     * (ie, from left to right, then right to left for the next level and alternate between).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<>();
        traversal(root, 0, nodes);
        return nodes;
    }
    public void traversal(TreeNode root, int depth, List<List<Integer>> nodes) {
        if (root != null) {
            if (nodes.size() <= depth) {
                nodes.add(new LinkedList<>());
            }
            int newDepth = depth + 1;
            traversal(root.left, newDepth, nodes);
            if((depth & 1) != 0) {
                nodes.get(depth).add(0,root.val);
            } else {
                nodes.get(depth).add(root.val);
            }
            traversal(root.right, newDepth, nodes);
        }
    }
}
