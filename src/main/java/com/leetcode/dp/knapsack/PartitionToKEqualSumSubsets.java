package com.leetcode.dp.knapsack;

/**
 * @author wcl
 * @date 5:14 PM 2020/5/18
 * <a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets/">
 * Partition To K Equal Sum Subsets</a>
 * @see PartitionEqualSubsetSum
 * TODO
 */
public class PartitionToKEqualSumSubsets {
    /**
     * Given an array of integers nums and a positive integer k,
     * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
     *
     * Example 1:
     * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * Output: True
     * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     *
     *
     * Note:
     *
     * 1 <= k <= len(nums) <= 16.
     * 0 < nums[i] < 10000.
     */
    int count = 0;
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int length = nums.length;
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum < k || sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] counts = new int[target + 1];
        for (int n : nums) {
            if(n > target) {
                return false;
            }
            counts[n]++;
        }

        return true;
    }

    public static void dfs(int[] nums, int[] counts, int index, int target) {
        for (int i = index; i < nums.length; i++) {
            dfs(nums, counts, i + 1, target-nums[i]);
        }
    }
}
