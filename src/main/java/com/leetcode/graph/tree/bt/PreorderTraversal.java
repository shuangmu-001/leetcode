package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zms
 * @date 9:44 PM 2020/3/17
 * <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/">
 *     Binary Tree Preorder Traversal</a>
 * @see MorrisTraversal
 */
public class PreorderTraversal {
    /**
     * Given a binary tree,
     * return the preorder traversal of its nodes' values.
     *
     * Example:
     * Input: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * Output: [1,2,3]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() ||root != null) {
            while(root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            if(pop.right != null) {
                root = pop.right;
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            list.add(current.val);
            if(current.right!=null) {
                stack.push(current.right);
            }
            if(current.left!=null) {
                stack.push(current.left);
            }
        }
        return list;
    }
}
