package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 9:43 AM 2020/3/18
 * <a href="https://leetcode.com/problems/binary-search-tree-iterator/">
 *     Binary Search Tree Iterator</a>
 */
public class BSTIterator {
    /**
     * Implement an iterator over a binary search tree (BST).
     * Your iterator will be initialized with the root node of a BST.
     * Calling next() will return the next smallest number in the BST.
     *
     * Example:
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // return 3
     * iterator.next();    // return 7
     * iterator.hasNext(); // return true
     * iterator.next();    // return 9
     * iterator.hasNext(); // return true
     * iterator.next();    // return 15
     * iterator.hasNext(); // return true
     * iterator.next();    // return 20
     * iterator.hasNext(); // return false
     *
     * Note:
     *      next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
     *      You may assume that next() call will always be valid, that is,
     *      there will be at least a next smallest number in the BST when next() is called.
     */
    private List<Integer> nums = new ArrayList<>();
    private int index;
    public BSTIterator(TreeNode root) {
        inorder(root);
    }
    private void inorder(TreeNode root) {
        if(root != null) {
            inorder(root.left);
            nums.add(root.val);
            inorder(root.right);
        }
    }
    /** @return the next smallest number */
    public int next() {

        return nums.get(index++);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return nums.size() > index;
    }
}
