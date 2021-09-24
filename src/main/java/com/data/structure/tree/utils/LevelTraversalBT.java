package com.data.structure.tree.utils;

import com.data.structure.tree.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wcl
 * @date 5:19 下午 2021/5/6
 */
public class LevelTraversalBT {

    public static <E> List<List<E>> level(BinaryTree.TreeNode<E> node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<List<E>> res = new ArrayList<>();
        Queue<BinaryTree.TreeNode<E>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<E> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BinaryTree.TreeNode<E> poll = queue.remove();
                list.add(poll.element);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
