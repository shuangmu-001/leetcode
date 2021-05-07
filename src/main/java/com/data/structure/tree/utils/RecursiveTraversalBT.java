package com.data.structure.tree.utils;

import com.data.structure.tree.BinaryTree;

/**
 * @author wcl
 * @date 3:53 下午 2021/5/6
 */
public class RecursiveTraversalBT {

    public static <E> void pre(BinaryTree.Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        pre(node.left);
        pre(node.right);
    }

    public static <E> void in(BinaryTree.Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        in(node.left);
        in(node.right);
    }

    public static <E> void post(BinaryTree.Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        post(node.left);
        post(node.right);
    }
}
