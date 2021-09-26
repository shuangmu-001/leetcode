package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 2:37 PM 2020/3/18
 * <a href="https://leetcode.com/problems/path-sum-ii/">
 *     Path Sum II</a>
 */
public class PathSumII {
    /**
     * Given a binary tree and a sum,
     * find all root-to-leaf paths where each path's sum equals the given sum.
     * Note: A leaf is a node with no children.
     *
     * Example:
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \    / \
     * 7    2  5   1
     * Return:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */
    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        traversal(root, 0, sum, 0, new ArrayList<>());
        return results;
    }

    public void traversal(TreeNode root, int sum, int target, int depth, List<Integer> result) {
        if(root == null) {
            return;
        }
        if(result.size() > depth) {
            result.set(depth, root.val);
        } else {
            result.add(root.val);
        }

        traversal(root.left, sum + root.val, target, depth + 1, result);
        traversal(root.right, sum + root.val, target, depth + 1, result);
        if(root.left == null && root.right == null) {
            if(sum + root.val == target) {
                results.add(new ArrayList<>(result.subList(0, depth + 1)));
            }
        }

    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.remove(0);
        System.out.println(result.size());
    }

}
