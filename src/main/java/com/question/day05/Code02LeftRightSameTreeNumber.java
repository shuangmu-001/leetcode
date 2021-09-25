package com.question.day05;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 5:20 下午 2021/9/23
 */
public class Code02LeftRightSameTreeNumber {

    // 如果一个节点X，它的左树结构和右树结构完全一样，那么我们树以X为头的树是相等树，
    // 给定一个二叉树的头节点head，返回head整棵树上有多少棵相等子树？
    public int sameNumber1(TreeNode head) {
        return process(head);
    }

    private int process(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int l = process(head.left);
        int r = process(head.right);
        if (isSameTree(head.left, head.right)) {
            return l + r + 1;
        }
        return l + r;
    }

    private boolean isSameTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return isSameTree(left.left, right.left) && isSameTree(left.right, right.right);
    }

    // 树的序列话
    // 字符串比较
//    public int sameNumber(TreeNode head) {
//    }
}
