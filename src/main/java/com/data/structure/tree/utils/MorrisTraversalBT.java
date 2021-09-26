package com.data.structure.tree.utils;

import com.data.structure.tree.BinarySearchTree;
import com.data.structure.tree.BinaryTree;
import com.data.structure.tree.RedBlackTree;
import com.data.structure.tree.printer.BinaryTrees;

import java.util.Random;

/**
 * @author zms
 * @date 3:26 下午 2021/5/11
 */
public class MorrisTraversalBT {
    // 中序遍历
    public static <E> void morrisTraversalIn(BinaryTree.TreeNode<E> head) {
        if (head == null) {
            return;
        }
        BinaryTree.TreeNode<E> node = head;
        BinaryTree.TreeNode<E> rightNode;
        while (node != null) {
            rightNode = node.left;
            if (rightNode != null) {

                while (rightNode.right != null && rightNode.right != node) {
                    rightNode = rightNode.right;
                }
                if (rightNode.right == null) {
                    rightNode.right = node;
                    node = node.left;
                    continue;
                } else {
                    // 恢复树的结构
                    rightNode.right = null;
                }
            }
            // 中序遍历
            System.out.print(node.element + ",");
            node = node.right;
        }
    }

    // 前序遍历
    public static <E> void morrisTraversalPre(BinaryTree.TreeNode<E> head) {
        if (head == null) {
            return;
        }
        BinaryTree.TreeNode<E> node = head;
        BinaryTree.TreeNode<E> rightNode;
        while (node != null) {
            rightNode = node.left;
            if (rightNode != null) {

                while (rightNode.right != null && rightNode.right != node) {
                    rightNode = rightNode.right;
                }
                if (rightNode.right == null) {
                    rightNode.right = node;
                    System.out.print(node.element + ",");
                    node = node.left;
                    continue;
                } else {
                    rightNode.right = null;
                }
            } else {
                System.out.print(node.element + ",");
            }
            node = node.right;
        }
    }

    // 后序遍历
    public static <E> void morrisTraversalPost(BinaryTree.TreeNode<E> head) {
        if (head == null) {
            return;
        }
        BinaryTree.TreeNode<E> node = head;
        BinaryTree.TreeNode<E> rightNode;
        while (node != null) {
            rightNode = node.left;
            if (rightNode != null) {
                while (rightNode.right != null && rightNode.right != node) {
                    rightNode = rightNode.right;
                }
                if (rightNode.right == null) {
                    rightNode.right = node;
                    node = node.left;
                    continue;
                } else {
                    rightNode.right = null;
                    printEdge(node.left);
                }
            }
            node = node.right;
        }
        printEdge(head);
        System.out.println();
    }

    private static <E> void printEdge(BinaryTree.TreeNode<E> head) {
        BinaryTree.TreeNode<E> right = head;
        BinaryTree.TreeNode<E> pre = null;
        while (right != null) {
            BinaryTree.TreeNode<E> temp = right.right;
            right.right = pre;
            pre = right;
            right = temp;
        }

        while (pre != null) {
            System.out.print(pre.element + ",");
            BinaryTree.TreeNode<E> temp = pre.right;
            pre.right = right;
            right = pre;
            pre = temp;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> redBlackTree = new RedBlackTree<>();
        for (int i = 0; i < 10; i++) {
            redBlackTree.add(new Random().nextInt(50));
        }
        BinaryTrees.print(redBlackTree);
        System.out.println();
        morrisTraversalIn(redBlackTree.getRoot());
        System.out.println();
        morrisTraversalPre(redBlackTree.getRoot());
        System.out.println();
        morrisTraversalPost(redBlackTree.getRoot());
        System.out.println();
    }
}
