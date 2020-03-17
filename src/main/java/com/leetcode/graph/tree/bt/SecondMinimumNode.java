package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wcl
 * @date 10:01 PM 2020/3/15
 * <a href="https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/">
 *     Second Minimum Node In a Binary Tree</a>
 */
public class SecondMinimumNode {
    /**
     * Given a non-empty special binary tree consisting of nodes with the non-negative value,
     * where each node in this tree has exactly two or zero sub-node.
     * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
     * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
     * Given such a binary tree,
     * you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
     * If no such second minimum value exists, output -1 instead.
     *
     * Example 1:
     * Input:
     *     2
     *    / \
     *   2   5
     *      / \
     *     5   7
     * Output: 5
     * Explanation: The smallest value is 2, the second smallest value is 5.
     *
     * Example 2:
     * Input:
     *     2
     *    / \
     *   2   2
     * Output: -1
     * Explanation: The smallest value is 2, but there isn't any second smallest value.
     */
    public int findSecondMinimumValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int min = root.val;
        int second = -1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            if(poll.val != min) {
                second = second != -1 ? Math.min(poll.val, second) : poll.val;
            }


            if(poll.left != null) {
                queue.add(poll.left);
            }

            if(poll.right != null) {
                queue.add(poll.right);
            }

        }
        return second;
    }
}