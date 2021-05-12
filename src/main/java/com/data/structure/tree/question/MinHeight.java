package com.data.structure.tree.question;

import com.data.structure.tree.BinarySearchTree;
import com.data.structure.tree.BinaryTree;
import com.data.structure.tree.RedBlackTree;
import com.data.structure.tree.printer.BinaryTrees;

import java.util.Random;

/**
 * @author wcl
 * @date 5:28 下午 2021/5/11
 */
public class MinHeight {

    public static <E>int minHeight(BinaryTree.Node<E> root) {
        if (root == null) {
            return 0;
        }
        BinaryTree.Node<E> node = root;
        BinaryTree.Node<E> right;
        int level = 1;
        int res = Integer.MAX_VALUE;
        while (node != null) {
            right = node.left;
            if (right != null) {
                int val = 1;
                while (right.right != null && right.right != node) {
                    val++;
                    right = right.right;
                }
                if (right.right == null) {
                    if (right.left == null) {
                        res = Math.min(res, level + val);
                    }
                    right.right = node;
                    node = node.left;
                    level++;
                    continue;
                } else {
                    right.right = null;
                    level -= (val + 1);
                }
            } else if (node.right == null) {
                res = Math.min(res, level);
            }
            level++;
            node = node.right;
        }
        return res;
    }

    public static <E>int minHeight01(BinaryTree.Node<E> root) {
        if (root == null) {
            return 0;
        }
        return minHeightHelper(root);
    }

    private static <E>int minHeightHelper(BinaryTree.Node<E> root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if (root.left != null) {
            leftH = minHeightHelper(root.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (root.right != null) {
            rightH = minHeightHelper(root.right);
        }
        return 1 + Math.min(leftH, rightH);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10000; j++) {
                test(new Random().nextInt(10000));
            }
        }
    }

    public static void test(int n) {
        BinarySearchTree<Integer> redBlackTree = new RedBlackTree<>();
        for (int i = 0; i < n; i++) {
            redBlackTree.add(new Random().nextInt(50));
        }
        BinaryTree.Node<Integer> root = redBlackTree.getRoot();
        if(minHeight(root) != minHeight01(root)) {
            BinaryTrees.print(redBlackTree);
            minHeight(root);
            System.out.println();
        }
    }
}
