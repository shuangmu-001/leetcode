package com.data.structure.tree.question;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * 判断一个树是否是二叉搜索树
 *
 * @author zms
 * @date 5:02 下午 2021/5/11
 */
public class IsBST {

    public static boolean isBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        TreeNode rightNode;
        TreeNode pre = null;
        while (node != null) {
            rightNode = node.left;
            if (rightNode != null) {
                while (rightNode.right != null && rightNode != node) {
                    rightNode = rightNode.right;
                }
                if(rightNode.right == null) {
                    rightNode.right = node;
                    node = node.left;
                    continue;
                } else {
                    // 恢复树的结构
                    rightNode.right = null;
                }
            }
            // BST 中序遍历 是从大小的顺序
            if(pre != null && pre.val > node.val) {
                return false;
            }
            pre = node;
            node = node.right;
        }
        return true;
    }

    public static boolean isBST01(TreeNode node) {
        if (node == null) {
            return true;
        }
        return isBSTHelper(node).isBST;
    }

    public static Info isBSTHelper(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info left = isBSTHelper(node.left);
        Info right = isBSTHelper(node.right);
        boolean isBST = true;
        int min = node.val;
        int max = node.val;
        if (left != null) {
            isBST = left.isBST && left.max <= node.val;
            min = left.min;
        }
        if (right != null) {
            isBST = isBST && right.isBST && node.val <= right.min;
            max = right.max;
        }
        return new Info(isBST, max, min);
    }

    static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
