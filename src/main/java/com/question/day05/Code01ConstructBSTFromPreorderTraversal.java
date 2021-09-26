package com.question.day05;

import com.Test;
import com.leetcode.graph.tree.bt.TreeNode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/">
 * Construct Binary Search Tree from Preorder Traversal</a>
 *
 * @author zms
 * @date 4:38 下午 2021/9/23
 */
public class Code01ConstructBSTFromPreorderTraversal implements Test {
    /**
     * Return the root node of a binary search tree that matches the given preorder traversal.
     * (Recall that a binary search tree is a binary tree where for every node,
     * any descendant of node.left has a value < node.val,
     * and any descendant of node.right has a value > node.val.
     * Also recall that a preorder traversal displays the value of the node first,
     * then traverses node.left, then traverses node.right.)
     * <p>
     * Example 1:
     * Input: [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     * <p>
     * Note:
     * 1 <= preorder.length <= 100
     * The values of preorder are distinct.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return bstFromPreorder(preorder, 0, preorder.length);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (start >= end) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[start]);
        // 获得首个大于当前节点的索引
        int index = getRightIndex(preorder, start + 1, end, preorder[start]);
        treeNode.left = bstFromPreorder(preorder, start + 1, index);
        treeNode.right = bstFromPreorder(preorder, index, end);
        return treeNode;
    }

    // 可以用单调栈解决这个问题
    private int getRightIndex(int[] preorder, int index, int end, int cur) {
        int length = preorder.length;
        for (int i = index; i < end; i++) {
            if (cur < preorder[i]) {
                return i;
            }
        }
        return length;
    }

    // 单调栈
    public TreeNode bstFromPreorder01(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int length = preorder.length;
        int[] nearBig = new int[length];
        for (int i = 0; i < length; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                nearBig[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        return bstFromPreorder(preorder, 0, preorder.length, nearBig);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end, int[] nearBig) {
        if (start >= end) {
            return null;
        }
        // 获得首个大于当前节点的索引
        int index = (nearBig[start] == -1 || nearBig[start] > end) ? end : nearBig[start];
        TreeNode treeNode = new TreeNode(preorder[start]);
        treeNode.left = bstFromPreorder(preorder, start + 1, index, nearBig);
        treeNode.right = bstFromPreorder(preorder, index, end, nearBig);
        return treeNode;
    }

    // 用数组实现单调栈
    public TreeNode bstFromPreorder02(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int length = preorder.length;
        int[] nearBig = new int[length];
        int[] stack = new int[length];
        int size = 0;
        for (int i = 0; i < length; i++) {
            nearBig[i] = -1;
        }
        for (int i = 0; i < length; i++) {
            while (size > 0 && preorder[stack[size - 1]] < preorder[i]) {
                nearBig[stack[size-- - 1]] = i;
            }
            stack[size++] = i;
        }
        return bstFromPreorder(preorder, 0, preorder.length, nearBig);
    }
}
