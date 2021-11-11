package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * <a href="https://leetcode.com/problems/recover-binary-search-tree/">Recover Binary Search Tree</a>
 *
 * @author zms
 * @date 12:57 PM 2020/3/21
 */
public class Recover {
    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     * <p>
     * Example 1:
     * Input: [1,3,null,null,2]
     * 1
     * /
     * 3
     * \
     * 2
     * Output: [3,1,null,null,2]
     * 3
     * /
     * 1
     * \
     * 2
     * <p>
     * Example 2:
     * Input: [3,1,4,null,null,2]
     * 3
     * / \
     * 1   4
     * /
     * 2
     * Output: [2,1,4,null,null,3]
     * 2
     * / \
     * 1   4
     * /
     * 3
     * Follow up:
     * A solution using O(n) space is pretty straight forward.
     * Could you devise a constant space solution?
     */
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        dfs(root);
        swap(first, second);
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if(first == null && pre.val > node.val) {
            first = pre;
        }
        if(first != null && pre.val > node.val) {
            second = node;
        }
        pre = node;
        dfs(node.right);
    }

    public void swap(TreeNode node01, TreeNode node02) {
        int temp = node01.val;
        node01.val = node02.val;
        node02.val = temp;
    }
}
