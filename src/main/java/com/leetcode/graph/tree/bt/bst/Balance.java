package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 10:48 PM 2020/3/20
 * <a href="https://leetcode.com/problems/balance-a-binary-search-tree/">
 *     Balance a Binary Search Tree</a>
 */
public class Balance {
    /**
     * Given a binary search tree, return a balanced binary search tree with the same node values.
     * A binary search tree is balanced if and only if the depth of
     * the two subtrees of every node never differ by more than 1.
     * If there is more than one answer, return any of them.
     *
     * Example 1:
     *      Input: root = [1,null,2,null,3,null,4,null,null]
     *      Output: [2,1,3,null,null,null,4]
     *      Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
     */

    public TreeNode balanceBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        depth(root, dummy, true);
        return dummy.left;
    }

    public int depth(TreeNode root, TreeNode parent, boolean flag) {
        if(root == null) {
            return 0;
        }
        int left = depth(root.left, root, true);
        int right = depth(root.right, root, false);
        if(left > right && left - right > 1) {
            TreeNode leftNode = root.left;

            root.left = null;
            if(flag) {
                parent.left = leftNode;
            } else {
                parent.right = leftNode;
            }
            while(leftNode.right != null) {
                leftNode = leftNode.right;
            }
            leftNode.right = root;
            left = left - 1;
        }
        if(right > left && right - left > 1) {
            TreeNode rightNode = root.right;
            root.right = null;
            if(flag) {
                parent.left = rightNode;
            } else {
                parent.right = rightNode;
            }
            while(rightNode.left != null) {
                rightNode = rightNode.left;
            }
            rightNode.left = root;
            right = right - 1;
        }
        return Math.max(left, right) + 1;
    }

}
