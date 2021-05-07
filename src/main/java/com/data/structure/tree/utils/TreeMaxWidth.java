package com.data.structure.tree.utils;

import com.data.structure.tree.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wcl
 * @date 5:31 下午 2021/5/6
 */
public class TreeMaxWidth {

    public static <E> int maxWidth(BinaryTree.Node<E> node) {
        if (node == null) {
            return 0;
        }
        Queue<BinaryTree.Node<E>> queue = new LinkedList<>();
        queue.add(node);
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = Math.max(max, size);
            for (int i = 0; i < size; i++) {
                BinaryTree.Node<E> poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return max;
    }

    public static <E> int maxWidth1(BinaryTree.Node<E> node) {
        if (node == null) {
            return 0;
        }
        Queue<BinaryTree.Node<E>> queue = new LinkedList<>();
        queue.add(node);
        BinaryTree.Node<E> curEnd = node;
        BinaryTree.Node<E> nextEnd = node;
        int max = 0;
        int curSize = 0;
        while (!queue.isEmpty()) {
            BinaryTree.Node<E> cur = queue.poll();
            if(cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curSize++;
            if(cur == curEnd) {
                max = Math.max(curSize, max);
                curEnd = nextEnd;
                curSize = 0;
            }
        }
        return max;
    }
}
