package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:49 AM 2020/3/23
 * <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row/">
 *     Find Largest Value in Each Tree Row</a>
 */
public class FindLargestValueInEachTreeRow {
    /**
     * You need to find the largest value in each row of a binary tree.
     *
     * Example:
     * Input:
     *
     *           1
     *          / \
     *         3   2
     *        / \   \
     *       5   3   9
     *
     * Output: [1, 3, 9]
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        dfs(root, 0, results);
        return results;
    }

    public void dfs(TreeNode root, int level, List<Integer> results) {
        if(root == null) {
            return;
        }
        if(results.size() <= level) {
            results.add(root.val);
        }
        results.set(level, Math.max(results.get(level), root.val));
        dfs(root.left, level + 1, results);
        dfs(root.right, level + 1, results);
    }
}
