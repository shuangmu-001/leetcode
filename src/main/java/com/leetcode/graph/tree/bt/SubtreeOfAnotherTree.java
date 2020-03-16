package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wcl
 * @date 2:05 PM 2020/3/16
 * <a href="https://leetcode.com/problems/subtree-of-another-tree/">
 * Subtree of Another Tree</a>
 * @see SameTree
 */
public class SubtreeOfAnotherTree {
    /**
     * Given two non-empty binary trees s and t,
     * check whether tree t has exactly the same structure and node values with a subtree of s.
     * A subtree of s is a tree consists of a node in s and all of this node's descendants.
     * The tree s could also be considered as a subtree of itself.
     * <p>
     * Example 1:
     * Given tree s:
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * Given tree t:
     * 4
     * / \
     * 1   2
     * Return true, because t has the same structure and node values with a subtree of s.
     * Example 2:
     * Given tree s:
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * /
     * 0
     * Given tree t:
     * 4
     * / \
     * 1   2
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.val == t.val) {
                if(isSubtreeHelper(poll, t)) {
                    return true;
                }
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        return false;
    }

    public boolean isSubtreeHelper(TreeNode s, TreeNode t) {

        if(s == null) {
            return t == null;
        } else {
            if(t == null) {
                return false;
            }
        }

        if(s.val != t.val) {
            return false;
        }

        return isSubtreeHelper(s.left, t.left) && isSubtreeHelper(s.right, t.right);
    }
}
