package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wcl
 * @date 10:43 AM 2020/3/18
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/">
 *     Binary Tree Inorder Traversal</a>
 * @see PreorderTraversal
 * @see MorrisTraversal
 */
public class InorderTraversal {
    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     *
     * Example:
     *
     * Input: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * Output: [1,3,2]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if(root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            results.add(pop.val);
            if(pop.right != null) {
                root = pop.right;
            }
        }
        return results;
    }
}
