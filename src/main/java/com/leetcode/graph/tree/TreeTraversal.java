package com.leetcode.graph.tree;

import java.util.List;

/**
 * @author wcl
 * @date 6:13 PM 2020/3/8
 * 1、遍历
 *   DFS (depth first search) 深度优先搜索
 *      pre-order
 *      in-order
 *      post-order
 *   BFS (breadth first search) 广度/宽度优先搜索
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
}
