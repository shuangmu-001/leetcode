package com.leetcode.dp.knapsack;

import com.Utils;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/partition-to-k-equal-sum-subsets/">Partition To K Equal Sum Subsets</a>
 *
 * @author zms
 * @date 5:14 PM 2020/5/18
 * @see PartitionEqualSubsetSum
 */
public class PartitionToKEqualSumSubsets {
    /**
     * Given an array of integers nums and a positive integer k,
     * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
     * <p>
     * Example 1:
     * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * Output: True
     * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     * <p>
     * Note:
     * 1 <= k <= len(nums) <= 16.
     * 0 < nums[i] < 10000.
     */
    public static boolean canPartitionKSubsets01(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }
        if (sum < k || sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (target < max) {
            return false;
        }
        Map<Integer, Map<Integer, Map<Integer, Boolean>>> dp = new HashMap<>();
        return process01(nums, k, target, target, 0, dp);
    }

    public static boolean process01(int[] nums, int k, int rest, int target, int flag,
                                    Map<Integer, Map<Integer, Map<Integer, Boolean>>> dp) {
        if (dp.containsKey(k) && dp.get(k).containsKey(rest) && dp.get(k).get(rest).containsKey(flag)) {
            return dp.get(k).get(rest).get(flag);
        }
        boolean ans = false;
        if (k == 1) {
            ans = true;
        } else if (rest == 0) {
            ans = process01(nums, k - 1, target, target, flag, dp);
        } else {
            for (int i = 0; i < nums.length; i++) {
                int exist = (flag & (1 << i));
                if (rest >= nums[i] && exist == 0) {
                    exist = (flag | (1 << i));
                    ans = process01(nums, k, rest - nums[i], target, exist, dp);
                }
                if (ans) {
                    break;
                }
            }
        }
        if (!dp.containsKey(k)) {
            dp.put(k, new HashMap<>());
        }
        Map<Integer, Map<Integer, Boolean>> map = dp.get(k);
        if (!map.containsKey(rest)) {
            map.put(rest, new HashMap<>());
        }
        map.get(rest).put(flag, ans);
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(canPartitionKSubsets01(new int[]{1, 1, 1, 1, 2, 2, 2, 2}, 4));
        System.out.println(canPartitionKSubsets01(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(!canPartitionKSubsets01(new int[]{7, 2, 2, 2, 2, 2, 2, 2, 3}, 3));
        System.out.println(canPartitionKSubsets01(new int[]{883, 850, 2995, 2324, 4474, 907, 991, 294, 912, 19, 826, 425, 3100, 438, 210}, 4));
        System.out.println(canPartitionKSubsets01(new int[]{1, 2, 3, 8, 10}, 2));
        System.out.println(!canPartitionKSubsets01(new int[]{88, 34, 61, 8, 5, 25, 11, 47, 87, 71, 71, 75, 1, 24, 65, 13}, 7));
        List<int[]> read = Optional.ofNullable(Utils.read("./src/main/resources/non-negative.txt", 16, true)).orElse(new ArrayList<>());
        for (int[] nums : read) {
            int[] newNums = new int[16];
            int index = 0;
            for (int n : nums) {
                newNums[index++] = n % 100;
            }
            Utils.printArrays(newNums);
            for (int i = 2; i < 16; i++) {
                System.out.println(canPartitionKSubsets01(newNums, i)  + "--------" + i);
            }

        }
    }
}
