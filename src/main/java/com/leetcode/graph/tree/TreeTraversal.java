package com.leetcode.graph.tree;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.List;

/**
 * @author wcl
 * @date 6:13 PM 2020/3/8
 * 1、遍历
 *   DFS (depth first search) 深度优先搜索
 *   Morris Traversal
 *      pre-order
 *      in-order
 *      post-order
 *   BFS (breadth first search) 广度/宽度优先搜索
 *
 * 2、求所有节点的和
 */
public class TreeTraversal {

    public void preOrder(Node root) {
        System.out.println(root.val);
        if(root.children != null) {
            List<Node> childrens = root.children;
            for (Node children : childrens) {
                preOrder(children);
            }
        }
    }

    public void preOrder(TreeNode root) {
        if(root != null) {
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(Node root) {

        if(root.children != null) {
            List<Node> childrens = root.children;
            for (Node children : childrens) {
                inOrder(children);
                System.out.println(root.val);
            }
        }
    }

    public void postOrder(Node root) {

        if(root.children != null) {
            List<Node> childrens = root.children;
            for (Node children : childrens) {
                inOrder(children);
            }
            System.out.println(root.val);
        }
    }

    public static int sum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        // 左边节点和
        int left = sum(root.left);
        // 右边节点和
        int right = sum(root.right);

        return left + right + root.val;
    }

}
