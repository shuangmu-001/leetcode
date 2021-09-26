package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 3:25 PM 2020/3/17
 * <a href="https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/">
 *     Construct Binary Search Tree from Preorder Traversal</a>
 */
public class ConstructBSTFromPreorderTraversal {
    /**
     * Return the root node of a binary search tree that matches the given preorder traversal.
     * (Recall that a binary search tree is a binary tree where for every node,
     * any descendant of node.left has a value < node.val,
     * and any descendant of node.right has a value > node.val.
     * Also recall that a preorder traversal displays the value of the node first,
     * then traverses node.left, then traverses node.right.)
     *
     * Example 1:
     * Input: [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     *
     * Note:
     *      1 <= preorder.length <= 100
     *      The values of preorder are distinct.
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length);
    }

    private static TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if(start >= end) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[start]);
        int rightIndex = getRightIndex(preorder, start);
        treeNode.left = bstFromPreorder(preorder, start + 1, rightIndex);
        treeNode.right = bstFromPreorder(preorder, rightIndex, end);
        return treeNode;
    }

    private static int getRightIndex(int[] preorder, int index) {
        int rightIndex = preorder.length;
        for (int i = index + 1; i < rightIndex; i++) {
            if(preorder[index] < preorder[i]) {
                rightIndex = i;
            }
        }
        return rightIndex;
    }

    public static void main(String[] args) {
        int[] nums = {8, 5, 1, 7, 10, 12};
        System.out.println(bstFromPreorder(nums, 0, nums.length));
    }
}
