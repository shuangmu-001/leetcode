package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 7:37 PM 2020/3/17
 * <a href="https://leetcode.com/problems/binary-tree-right-side-view/">
 *     Binary Tree Right Side View</a>
 */
public class RightSideView {
    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     * Example:
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     */
    private int maxDepth;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        preOrder(root,1, results);
        return results;
    }

    public void preOrder(TreeNode root, int depth, List<Integer> results) {
        if(root == null) {
            return;
        }
        if(depth > maxDepth) {
            results.add(root.val);
            maxDepth = depth;
        }
        preOrder(root.right, depth + 1, results);
        preOrder(root.left, depth + 1, results);
    }
}
