package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author wcl
 * @date 9:11 PM 2020/3/17
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">
 *     Binary Tree Level Order Traversal</a>
 * TODO 只使用一个队列去实现它
 * TODO 用BFS算法来做
 */
public class BTLevelOrderTraversal {
    /**
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its level order traversal as:
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        traversal(root, results, 0);
        return results;
    }

    public void traversal(TreeNode root, List<List<Integer>> results, int level) {
        if(root != null) {
            if(results.size() <= level) {
                results.add(new ArrayList<>());
            }
            List<Integer> integers = results.get(level);
            integers.add(root.val);
            traversal(root.left, results, level+ 1);
            traversal(root.right, results, level+ 1);
        }
    }

    public List<List<Integer>> queue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        res.add(Collections.singletonList(root.val));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> nums = new ArrayList<>();
            while(size > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                nums.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }

            res.add(nums);
        }
        return res;
    }
}
