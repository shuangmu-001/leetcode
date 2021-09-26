package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author zms
 * @date 8:29 PM 2020/3/11
 * {@link "https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/"}
 */
public class ConvertSortedArrayToBST {
    /**
     * Given an array where elements are sorted in ascending order,convert it to a height balanced BST.
     * For this problem, a height-balanced binary tree is defined as a binary tree in
     * which the depth of the two subtrees of every node never differ by more than 1.
     * Example:
     *      Given the sorted array: [-10,-3,0,5,9],
     *      One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return traversal(nums, null, 0, nums.length - 1);
    }

    public static TreeNode traversal(int[] nums, TreeNode root, int left, int right) {
        int mid = (left + right) >> 1;
        if(left == mid) {
            TreeNode treeNode = new TreeNode(nums[mid]);
            if(left != right) {
                treeNode.right = new TreeNode(nums[right]);
            }
            return treeNode;
        }
        if(root == null) {
            root = new TreeNode(nums[mid]);
        }

        root.left =  traversal(nums, root.left, left, Math.max(mid - 1, left));
        root.right = traversal(nums, root.right, Math.min(mid + 1, right), right);
        return root;
    }

    /**
     * 二分
     * @see com.leetcode.graph.tree.bt.CountCompleteTreeNodes
     */
    private TreeNode sortedArrayToBST(int[] nums, int start, int end){
        if(start >= end) return null;
        // if(start == end) return new TreeNode(nums[start]);
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(nums));
        int[] nums1 = new int[]{-10,-3,0,5};
        System.out.println(sortedArrayToBST(nums1));
        int[] nums2 = new int[]{-10,-3,0,5};
        System.out.println(sortedArrayToBST(nums2));
        int[] nums3 = new int[]{-10,-3,0,5};
        System.out.println(sortedArrayToBST(nums3));
        int[] nums4 = new int[]{-10};
        System.out.println(sortedArrayToBST(nums4));
    }
}
