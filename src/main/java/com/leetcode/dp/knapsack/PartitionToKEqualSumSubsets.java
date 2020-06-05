package com.leetcode.dp.knapsack;

import com.leetcode.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(k == 1) {
            return true;
        }
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum < k || sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums) {
            if(n > target) {
                return false;
            }
            for (int i = target; i >= n ; i--) {
                if(dp[i - n] != 0) {
                    dp[i]++;
                }
            }
            Utils.printArrays(dp);
        }
        return dp[target] >= k;
    }


    public static void main(String[] args) {
//        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(canPartitionKSubsets(new int[]{7,2,2,2,2,2,2,2,3}, 3));
//        System.out.println(canPartitionKSubsets(new int[]{1, 2, 3, 5}, 2));
//        System.out.println(canPartitionKSubsets(new int[]{88,34,61,8,5,25,11,47,87,71,71,75,1,24,65,13}, 7));
//        List<int[]> read = Optional.ofNullable(Utils.read("./src/main/resources/non-negative.txt", 16, true)).orElse(new ArrayList<>());
//        for (int[] nums : read) {
//            int[] newNums = new int[16];
//            int index = 0;
//            for (int n : nums) {
//                newNums[index++] = n % 100;
//            }
//            Utils.printArrays(newNums);
//            for (int i = 2; i < 16; i++) {
//                System.out.println(canPartitionKSubsets(newNums, i)  + "--------" + i);
//            }
//
//        }
    }
}
