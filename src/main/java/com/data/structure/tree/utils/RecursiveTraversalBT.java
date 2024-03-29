package com.data.structure.tree.utils;

import com.data.structure.tree.BinaryTree;

/**
 * @author zms
 * @date 3:53 下午 2021/5/6
 */
public class RecursiveTraversalBT {

    public static <E> void pre(BinaryTree.TreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        pre(node.left);
        pre(node.right);
    }

    public static <E> void in(BinaryTree.TreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        in(node.left);
        in(node.right);
    }

    public static <E> void post(BinaryTree.TreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        post(node.left);
        post(node.right);
    }
}
