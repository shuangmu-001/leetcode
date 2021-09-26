package com.leetcode.graph.tree.bt;

import com.Utils;

/**
 * @author zms
 * @date 4:29 PM 2020/4/30
 * <a href="https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/532/week-5/3315/">
 *     Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree</a>
 */
public class CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABT {
    /**
     * Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree.
     *
     * We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
     * Output: true
     * Explanation:
     * The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
     * Other valid sequences are:
     * 0 -> 1 -> 1 -> 0
     * 0 -> 0 -> 0
     * Example 2:
     *
     *
     *
     * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
     * Output: false
     * Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
     * Example 3:
     *
     *
     *
     * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
     * Output: false
     * Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 5000
     * 0 <= arr[i] <= 9
     * Each node's value is between [0 - 9].
     */
     static boolean res = false;
    public static boolean isValidSequence(TreeNode root, int[] arr) {
        if(root == null || root.val != arr[0]){
            return false;
        }
        dfs(root, 0, arr, true);
        return res;
    }

    public static void dfs(TreeNode root, int index, int[] arr, boolean before) {
        if(root == null || res || index >= arr.length || !before) {
            return;
        }
        if(root.left != null) {
            dfs(root.left, index + 1, arr, root.val == arr[index]);
        }
        if(root.right != null) {
            dfs(root.right, index + 1, arr, root.val == arr[index]);
        }
        if(index == arr.length - 1
                && root.right == null
                && root.left == null
                && root.val == arr[index]) {
            res = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValidSequence(
                        Utils.arrayToTreeNode(
                                new Integer[]{0,1,0,0,1,0,null,null,null,0}),
                        new int[]{0,1,0,1}));
    }
}
