package com.leetcode.graph.tree.bt;

/**
 * @author zms
 * @date 9:26 PM 2020/3/16
 * <a href="https://leetcode.com/problems/maximum-binary-tree/">
 *     Maximum Binary Tree</a>
 */
public class MaximumBinaryTree {
    /**
     * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
     * The root is the maximum number in the array.
     * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
     * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
     * Construct the maximum tree by the given array and output the root node of this tree.
     *
     * Example 1:
     *      Input: [3,2,1,6,0,5]
     *      Output: return the tree root node representing the following tree:
     *
     *       6
     *     /   \
     *    3     5
     *     \    /
     *      2  0
     *        \
     *         1
     * Note:
     *      The size of the given array will be in the range [1,1000].
     */
    public static TreeNode constructMaximumBinaryTree1(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            TreeNode temp = new TreeNode(nums[i]);
            if(temp.val > root.val) {
                temp.left = root;
                root = temp;
            } else if (root.right == null){
                root.right = temp;
            } else {
                TreeNode dummy = root;
                TreeNode before = root;
                while(dummy != null) {
                    if(dummy.val > temp.val) {
                        before = dummy;
                        dummy = dummy.right;
                    } else {
                        break;
                    }
                }
                before.right = temp;
                temp.left = dummy;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(constructMaximumBinaryTree1(new int[]{3,2,1,6,0,5}));
    }
    // other
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        final int max = findMaxIndex(nums, start, end);
        final TreeNode t = new TreeNode(nums[max]);
        t.left = constructMaximumBinaryTree(nums, start, max-1);
        t.right = constructMaximumBinaryTree(nums, max + 1, end);
        return t;
    }

    private int findMaxIndex(int[] nums, int start, int end) {
        int max = start;
        for (int i = start; i <= end; i++) {
            if (nums[max] < nums[i]) {
                max = i;
            }
        }
        return max;
    }
}
