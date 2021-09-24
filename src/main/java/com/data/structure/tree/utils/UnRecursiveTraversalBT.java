package com.data.structure.tree.utils;

import com.data.structure.tree.BinaryTree;

import java.util.Stack;

/**
 * @author wcl
 * @date 3:56 下午 2021/5/6
 */
public class UnRecursiveTraversalBT {

    public static <E> void pre(BinaryTree.TreeNode<E> node) {
        System.out.print("pre-order : ");
        if (node == null) {
            return;
        }
        Stack<BinaryTree.TreeNode<E>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTree.TreeNode<E> pop = stack.pop();
            System.out.print(pop.element + " ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        System.out.println();
    }

    public static <E> void in(BinaryTree.TreeNode<E> node) {
        System.out.print("in-order : ");
        if (node == null) {
            return;
        }
        Stack<BinaryTree.TreeNode<E>> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.element + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    public static <E> void post(BinaryTree.TreeNode<E> node) {
        System.out.print("post-order : ");
        if (node == null) {
            return;
        }
        Stack<BinaryTree.TreeNode<E>> s1 = new Stack<>();
        Stack<BinaryTree.TreeNode<E>> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            BinaryTree.TreeNode<E> pop = s1.pop();
            s2.push(pop);
            if (pop.left != null) {
                s1.push(pop.left);
            }
            if (pop.right != null) {
                s1.push(pop.right);
            }
        }
        while (!s2.isEmpty()) {
            BinaryTree.TreeNode<E> pop = s2.pop();
            System.out.print(pop.element + " ");
        }
        System.out.println();
    }

    public static <E> void post2(BinaryTree.TreeNode<E> node) {
        System.out.print("post-order : ");
        if (node == null) {
            return;
        }
        Stack<BinaryTree.TreeNode<E>> s1 = new Stack<>();
        s1.push(node);
        BinaryTree.TreeNode<E> c;
        BinaryTree.TreeNode<E> h = node;
        while (!s1.isEmpty()) {
            c = s1.peek();
            // h 表示前一次打印的节点
            // 如果 h 是 c 的子节点，则说明c的子节点肯定已经开始打印了
            if (c.left != null && h != c.left && h != c.right) {
                s1.push(c.left);
                // 如果 h 是 c 的右子节点，则说明c的子节点已经打印完成了
            } else if (c.right != null && h != c.right) {
                s1.push(c.right);
                // 打印头节点
            } else {
                System.out.print(s1.pop().element + " ");
                h = c;
            }
        }
        System.out.println();
    }
}
