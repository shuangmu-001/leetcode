package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author wcl
 * @date 4:10 PM 2020/3/18
 * <a href="https://leetcode.com/problems/binary-tree-postorder-traversal/">
 *     Binary Tree Postorder Traversal</a>
 * @see PreorderTraversal
 * @see InorderTraversal
 * @see MorrisTraversal
 */
public class PostorderTraversal {
    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
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
     * Output: [3,2,1]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    // 正序思路
    public List<Integer> myPostorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() ||root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            // 改变了树的结构
            if(pop.right != null) {
                stack.push(pop);
                root = pop.right;
                pop.right = null;
            } else {
                result.add(pop.val);
            }
        }
        return result;
    }
    // 倒序输出
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // up
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(0,curr.val);
            if(curr.left!=null) {
                stack.push(curr.left);
            }
            if(curr.right!=null) {
                stack.push(curr.right);
            }
        }
        return list;
    }
}
