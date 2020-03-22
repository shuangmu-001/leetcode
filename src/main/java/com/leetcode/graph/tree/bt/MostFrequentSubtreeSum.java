package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author wcl
 * @date 1:05 PM 2020/3/22
 * <a href="https://leetcode.com/problems/most-frequent-subtree-sum/">
 *     Most Frequent Subtree Sum</a>
 */
public class MostFrequentSubtreeSum {
    /**
     * Given the root of a tree, you are asked to find the most frequent subtree sum.
     * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
     * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
     *
     * Examples 1
     * Input:
     *
     *   5
     *  /  \
     * 2   -3
     * return [2, -3, 4], since all the values happen only once, return all of them in any order.
     * Examples 2
     * Input:
     *
     *   5
     *  /  \
     * 2   -5
     * return [2], since 2 happens twice, however -5 only occur once.
     * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
     */
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> results ;
    int max;
    public int[] findFrequentTreeSum(TreeNode root) {
        findFrequentTreeSumHelper(root);
        if(results == null) {
            return new int[0];
        }
        int[] nums = new int[results.size()];
        for(int i = 0; i < results.size(); i++) {
            nums[i] = results.get(i);
        }
        return nums;
    }

    public int findFrequentTreeSumHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = findFrequentTreeSumHelper(root.left);
        int right = findFrequentTreeSumHelper(root.right);
        int sum = root.val + left + right;
        map.merge(sum, 1, Integer::sum);
        Integer integer = map.get(sum);
        if(integer > max) {
            results = new ArrayList<>();
            results.add(sum);
            max = integer;
        } else if(integer == max) {
            results.add(sum);
        }

        return sum;
    }

}
