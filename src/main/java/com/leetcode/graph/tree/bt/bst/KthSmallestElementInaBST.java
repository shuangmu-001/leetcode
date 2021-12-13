package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.LinkedList;

/**
 * @author zms
 * @date 6:41 PM 2020/3/18
 * <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/">
 * Kth Smallest Element in a BST</a>
 */
public class KthSmallestElementInaBST {
    /**
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     * Note:You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     * <p>
     * Example 1:
     * Input: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     * 2
     * Output: 1
     * <p>
     * Example 2:
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * Output: 3
     * <p>
     * TODO Follow up:
     *          What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
     *          How would you optimize the kthSmallest routine?
     * <p>
     * That's a design question.txt, basically we're asked to implement a structure which contains a BST inside and optimises the following operations :
     * Insert
     * Delete
     * Find kth smallest
     * Seems like a database description, isn't it?
     * Let's use here the same logic as for LRU cache design, and combine an indexing structure (we could keep BST here) with a double linked list.
     */
    public int kthSmallest01(TreeNode root, int k) {
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                k--;
                if (k == 0) {
                    return curr.val;
                }
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
            }
        }
        return 0;
    }

    public int kthSmallest02(TreeNode root, int k) {
        inorder(root, k);
        return ans == null ? 0 : ans;
    }

    Integer ans = null;
    int th = 0;

    public void inorder(TreeNode node, int k) {
        if (ans != null || node == null) {
            return;
        }
        inorder(node.left, k);
        th++;
        if (th == k) {
            ans = node.val;
            return;
        }
        inorder(node.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            if(--k == 0) {
                return pop.val;
            }
            root = pop.right;
        }
    }
}
