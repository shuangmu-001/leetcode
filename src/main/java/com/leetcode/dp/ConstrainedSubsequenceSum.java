package com.leetcode.dp;

import com.leetcode.Utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author wcl
 * @date 6:00 PM 2020/4/30
 * TODO <a href="https://leetcode.com/problems/constrained-subsequence-sum/">
 *     Constrained Subsequence Sum</a>
 */
public class ConstrainedSubsequenceSum {
    /**
     * Given an integer array nums and an integer k,
     * return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence,
     * nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
     * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
     * leaving the remaining elements in their original order.
     *
     * Example 1:
     * Input: nums = [10,2,-10,5,20], k = 2
     * Output: 37
     * Explanation: The subsequence is [10, 2, 5, 20].
     *
     * Example 2:
     * Input: nums = [-1,-2,-3], k = 1
     * Output: -1
     * Explanation: The subsequence must be non-empty, so we choose the largest number.
     *
     * Example 3:
     * Input: nums = [10,-2,-10,-5,20], k = 2
     * Output: 23
     * Explanation: The subsequence is [10, -2, -5, 20].
     *
     * Constraints:
     * 1 <= k <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     */
    // Time Limit Exceeded
    public static int constrainedSubsetSum1(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            int l = Math.min(k, i);
            dp[i] = nums[i];
            for (int j = 0; j < l; j++) {
                dp[i] = Math.max(dp[i], dp[i - j - 1] + nums[i]);
            }
            max = Math.max(dp[i], max);
        }
        Utils.printArrays(dp);
        return max;
    }
    // 滑动窗口 优化dp
    public static int constrainedSubsetSum2(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.merge(dp[0], 1, Integer::sum);
        for (int i = 1; i < len; i++) {
            Integer integer = treeMap.lastKey();
            dp[i] = Math.max(nums[i], integer + nums[i]);
            max = Math.max(max, dp[i]);
            treeMap.merge(dp[i], 1, Integer::sum);
            if(i >= k) {
                Integer count = treeMap.get(dp[i - k]);
                if(count > 1) {
                    treeMap.merge(dp[i - k], -1, Integer::sum);
                } else {
                    treeMap.remove(dp[i - k]);
                }
            }

        }
        Utils.printArrays(dp);
        return max;
    }

    public static int constrainedSubsetSum(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        Deque<Integer> deque = new LinkedList<>();
        deque.add(dp[0]);
        for (int i = 1; i < len; i++) {
            Integer last = deque.getFirst();
            dp[i] = Math.max(nums[i], last + nums[i]);
            max = Math.max(max, dp[i]);
            deque.add(dp[i]);
            if(i >= k) {
                deque.removeFirstOccurrence(dp[i - k]);
            }

        }
        Utils.printArrays(dp);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(23 == constrainedSubsetSum(new int[]{10,-2,-10,-5,20}, 2));
        System.out.println(126 == constrainedSubsetSum(new int[]{-51,-35,74,10,-2,-10,-5,20,-62,14,-53,-23,15,-42,-51,-35,74}, 2));
        System.out.println(170 == constrainedSubsetSum(new int[]{-51,-35,74,10,-2,-10,-5,20,-62,14,-53,-23,15,-42,-51,-35,74}, 3));
        System.out.println(207 == constrainedSubsetSum(new int[]{-51,-35,74,10,-2,-10,-5,20,-62,14,-53,-23,15,-42,-51,-35,74}, 4));
        System.out.println(87 == constrainedSubsetSum(new int[]{-51,-35,74,10,-2,-10,-5,20,-62,14,-53,-23,15,-42,-51,-35,74}, 1));
    }
}
