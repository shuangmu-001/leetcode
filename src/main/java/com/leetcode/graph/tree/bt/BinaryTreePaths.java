package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:45 PM 2020/3/13
 * <a href="https://leetcode.com/problems/binary-tree-paths/">
 *     Binary Tree Paths</a>
 */
public class BinaryTreePaths {
    /**
     * Given a binary tree, return all root-to-leaf paths.
     * Note: A leaf is a node with no children.
     *
     * Example:
     * Input:
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     * Output: ["1->2->5", "1->3"]
     * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        traversal(root, sb, results);
        return results;
    }

    public void traversal(TreeNode root, StringBuilder sb, List<String> strs) {
        if(root == null) {
            return;
        }
        int length = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null) {
            strs.add(sb.toString());
        } else {
            sb.append("->");
            traversal(root.left, sb, strs);
            traversal(root.right, sb, strs);
        }
        sb.setLength(length);

    }
}
