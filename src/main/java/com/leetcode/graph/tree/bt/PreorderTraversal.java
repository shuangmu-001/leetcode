package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wcl
 * @date 9:44 PM 2020/3/17
 * <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/">
 *     Binary Tree Preorder Traversal</a>
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
        List<Integer> visited = new ArrayList<>();
        Stack<TreeNode> toVisit = new Stack<>();

        if(root!=null) toVisit.add(root);
        while(!toVisit.isEmpty()) {
            TreeNode node = toVisit.pop();
            visited.add(node.val);
            if(node.right!=null) toVisit.add(node.right);
            if(node.left!=null) toVisit.add(node.left);

        }
        return visited;
    }
}
