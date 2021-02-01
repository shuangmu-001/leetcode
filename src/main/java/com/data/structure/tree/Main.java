package com.data.structure.tree;

import com.data.structure.tree.printer.BinaryTrees;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zms
 * @date 3:15 下午 2020/12/23
 */
public class Main {
    private static final Integer[] integers;

    static {
//        integers = new Integer[]{
//                7, 5, 4, 3, 10, 22, 13
//        };

//        integers = new Integer[]{
//                77, 67, 11, 90, 62, 85, 24, 73, 21, 53, 19, 24, 65, 38, 51, 87, 54, 59, 50, 68
//        };

//        integers = new Integer[]{
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
//        };
//        integers = new Integer[]{
//                80, 30, 70, 40, 20, 10, 50
//        };
        integers = new Integer[100];
        for (int i = 0; i < 100; i++) {
            integers[i] = ThreadLocalRandom.current().nextInt(100, 1000);
        }
    }

    public static void main(String[] args) {

//        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
//        add(binarySearchTree);
//        System.out.println();
//
//        BinarySearchTree<Integer> avlTree = new AVLTree<>();
//        add(avlTree);
//        System.out.println();
//        remove(avlTree);
        System.out.println(Arrays.toString(integers));
        BinarySearchTree<Integer> redBlackTree = new RedBlackTree<>();
        add(redBlackTree);
        System.out.println();
        remove(redBlackTree);
//        remove(redBlackTree, 1);
//        remove(redBlackTree, 10);
//        remove(redBlackTree, 9);
//        remove(redBlackTree, 11);
//        remove(redBlackTree, 5);
//        remove(redBlackTree, 3);
//        remove(redBlackTree, 7);
//        remove(redBlackTree, 13);
//        remove(redBlackTree, 16);
//        System.out.println("---------目标----------");
//        remove(redBlackTree, 15);
    }

    public static void add(BinarySearchTree<Integer> binarySearchTree) {

        for (Integer integer : integers) {
            binarySearchTree.add(integer);
        }
        BinaryTrees.print(binarySearchTree);
        System.out.println();
        System.out.println("------------------");
    }

    public static void remove(BinarySearchTree<Integer> binarySearchTree) {
        for (Integer integer : integers) {
            System.out.println("删除的节点是 :" + integer);
            remove(binarySearchTree, integer);
        }
    }

    public static void remove(BinarySearchTree<Integer> binarySearchTree, Integer element) {
        binarySearchTree.remove(element);
        System.out.println();
        BinaryTrees.print(binarySearchTree);
        System.out.println();
    }

}
