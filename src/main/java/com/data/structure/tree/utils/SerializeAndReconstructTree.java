package com.data.structure.tree.utils;

import com.data.structure.tree.BinarySearchTree;
import com.data.structure.tree.BinaryTree;
import com.data.structure.tree.RedBlackTree;
import com.data.structure.tree.printer.BinaryTrees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

/**
 * @author zms
 * @date 6:34 下午 2021/5/6
 */
public class SerializeAndReconstructTree {

    public static <E> Queue<E> preSerial(BinaryTree.TreeNode<E> node) {
        Queue<E> queue = new LinkedList<>();
        preSerialHelper(node, queue);
        return queue;
    }

    private static <E> void preSerialHelper(BinaryTree.TreeNode<E> node, Queue<E> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        queue.add(node.element);
        preSerialHelper(node.left, queue);
        preSerialHelper(node.right, queue);
    }

    public static <E> BinaryTree.TreeNode<E> buildByPreQueue(Queue<E> preQueue) {
        if (preQueue == null || preQueue.size() == 0) {
            return null;
        }
        return buildByPreQueueHelper(preQueue);
    }

    private static <E> BinaryTree.TreeNode<E> buildByPreQueueHelper(Queue<E> preQueue) {
        E poll = preQueue.poll();
        if (poll == null) {
            return null;
        }
        BinaryTree.TreeNode<E> node = new BinaryTree.TreeNode<>(poll, null);
        node.left = buildByPreQueueHelper(preQueue);
        node.right = buildByPreQueueHelper(preQueue);
        return node;
    }

    public static <E> Queue<E> inSerial(BinaryTree.TreeNode<E> node) {
        Queue<E> queue = new LinkedList<>();
        inSerialHelper(node, queue);
        return queue;
    }

    private static <E> void inSerialHelper(BinaryTree.TreeNode<E> node, Queue<E> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        inSerialHelper(node.left, queue);
        queue.add(node.element);
        inSerialHelper(node.right, queue);
    }

//    public static <E> BinaryTree.TreeNode<E> buildByInQueue(Queue<E> inQueue) {
//        if (inQueue == null || inQueue.size() == 0) {
//            return null;
//        }
//        return buildByInQueueHelper(inQueue);
////    }

    // TODO 通过中序队列反序列化成树
//    private static <E> BinaryTree.TreeNode<E> buildByInQueueHelper(Queue<E> inQueue) {
//        if (inQueue.isEmpty()) {
//            return null;
//        }
//        E poll = inQueue.poll();
//        BinaryTree.TreeNode<E> left = buildByInQueueHelper(inQueue);
//        if (poll == null) {
//            return null;
//        }
//        BinaryTree.TreeNode<E> node = new BinaryTree.TreeNode<>(poll, null);
//        node.left = left;
//        node.right = buildByInQueueHelper(inQueue);
//        return node;
//    }

    public static <E> Queue<E> postSerial(BinaryTree.TreeNode<E> node) {
        Queue<E> queue = new LinkedList<>();
        postSerialHelper(node, queue);
        return queue;
    }

    private static <E> void postSerialHelper(BinaryTree.TreeNode<E> node, Queue<E> queue) {
        if (node == null) {
            queue.add(null);
            return;
        }
        postSerialHelper(node.left, queue);
        postSerialHelper(node.right, queue);
        queue.add(node.element);
    }

    public static <E> BinaryTree.TreeNode<E> buildByPostQueue(Deque<E> postQueue) {
        if (postQueue == null || postQueue.size() == 0) {
            return null;
        }
        return buildByPostQueueHelper(postQueue);
    }

    // 通过后序队列反序列化成树
    private static <E> BinaryTree.TreeNode<E> buildByPostQueueHelper(Deque<E> postQueue) {
        E poll = postQueue.pollLast();
        if (poll == null) {
            return null;
        }
        BinaryTree.TreeNode<E> node = new BinaryTree.TreeNode<>(poll, null);
        node.right = buildByPostQueueHelper(postQueue);
        node.left = buildByPostQueueHelper(postQueue);
        return node;
    }

    public static <E> Queue<E> levelSerial(BinaryTree.TreeNode<E> node) {
        Queue<E> queue = new LinkedList<>();
        if (node == null) {
            queue.add(null);
            return queue;
        }
        Queue<BinaryTree.TreeNode<E>> nodes = new LinkedList<>();
        nodes.add(node);
        queue.add(node.element);
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            if (node.left != null) {
                nodes.add(node.left);
                queue.add(node.left.element);
            } else {
                queue.add(null);
            }
            if (node.right != null) {
                nodes.add(node.right);
                queue.add(node.right.element);
            } else {
                queue.add(null);
            }
        }
        return queue;
    }

    public static <E> BinaryTree.TreeNode<E> buildByLevelQueue(Queue<E> levelQueue) {
        if (levelQueue == null || levelQueue.size() == 0) {
            return null;
        }
        Queue<BinaryTree.TreeNode<E>> nodes = new LinkedList<>();
        BinaryTree.TreeNode<E> root = generateNode(levelQueue.poll());
        if (root != null) {
            nodes.add(root);
        }
        BinaryTree.TreeNode<E> node = null;
        while (!nodes.isEmpty()) {
            node = nodes.poll();
            node.left = generateNode(levelQueue.poll());
            node.right = generateNode(levelQueue.poll());
            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        return root;
    }

    public static <E> BinaryTree.TreeNode<E> generateNode(E element) {
        if (element == null) {
            return null;
        }
        return new BinaryTree.TreeNode<>(element, null);
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{
                7, 5, 4, 3, 10, 22, 13
        };
        BinarySearchTree<Integer> redBlackTree = new RedBlackTree<>();
        for (Integer integer : integers) {
            redBlackTree.add(integer);
        }
        BinaryTrees.print(redBlackTree);
        System.out.println();
        // 前序遍历的方式序列化和反序列化
        Queue<Integer> queue = preSerial(redBlackTree.getRoot());
        print(queue, SerializeAndReconstructTree::buildByPreQueue);

        // 中序遍历的方式序列化和反序列化
//        queue = inSerial(redBlackTree.getRoot());
//        print(queue, SerializeAndReconstructTree::buildByInQueue);
        // 后序遍历的方式序列化和反序列化
        queue = postSerial(redBlackTree.getRoot());
        print(queue, q -> {
            Deque<Integer> deque = (Deque<Integer>) q;
            return buildByPostQueue(deque);
        });
        // 层次遍历的方式序列化和反序列化
        queue = levelSerial(redBlackTree.getRoot());
        print(queue, SerializeAndReconstructTree::buildByLevelQueue);

    }

    public static void print(Queue<Integer> queue,
                             Function<Queue<Integer>, BinaryTree.TreeNode<Integer>> function) {
        System.out.println(queue);
        BinaryTree.TreeNode<Integer> root = function.apply(queue);
        BinaryTree<Integer> newTree = new BinaryTree<>(root);
        BinaryTrees.print(newTree);
        System.out.println();
    }

}
