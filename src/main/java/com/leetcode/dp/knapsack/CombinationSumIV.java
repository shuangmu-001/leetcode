package com.leetcode.dp.knapsack;

import com.leetcode.Utils;

import java.util.Arrays;

/**
 * @author wcl
 * @date 5:32 PM 2020/5/12
 * <a href="https://leetcode.com/problems/combination-sum-iv/">
 * Combination Sum IV</a>
 * @see com.leetcode.backtracking.CombinationSum
 * @see com.leetcode.backtracking.CombinationSumII
 * @see com.leetcode.backtracking.CombinationSumIII
 */
public class CombinationSumIV {
    /**
     * Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     *
     * Example:
     *
     * nums = [1, 2, 3]
     * target = 4
     *
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     *
     * Note that different sequences are counted as different combinations.
     *
     * Therefore the output is 7.
     *
     *
     * Follow up:
     * What if negative numbers are allowed in the given array?
     * How does it change the problem?
     * What limitation we need to add to the question.txt to allow negative numbers?
     */
    // TLE
    static int count = 0;
    public static int combinationSum41(int[] nums, int target) {
        count = 0;
        dfs(nums, target);
        return count;
    }

    public static void dfs(int[] nums, int target) {
        if(target == 0) {
            count++;
            return;
        }
        for (int n : nums) {
            if(target >= n) {
                target -= n;
                dfs(nums, target);
                target += n;
            }
        }
    }

    public static int combinationSum42(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int m = nums.length;
        // dp[i][j] i 表示第几个 j 表示和
        int[][] dp = new int[m][target + 1];
        for (int i = 0; i <= target; i++) {
            if(i == nums[0]) {
                dp[0][i] = 1;
            } else if(i > nums[0]) {
                dp[0][i] = dp[0][i - nums[0]];
            }
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= target; j++) {
                if(j >= nums[i]) {
                    for (int k = 0; k <= i; k++) {
                        if(j >= nums[k]) {
                            dp[i][j] += dp[i][j - nums[k]];
                        }
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }
//        Utils.printTwoArrays(dp);
        return dp[m - 1][target];
    }


    public static int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // dp[j] 表示目标为j的个数 dp[j + 1] = sum(dp[j + 1 - nums[0]], dp[j + 1 - nums[1]]...dp[j + 1 - nums[m - 1]])
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
//        Utils.printTwoArrays(dp);
        return dp[target];
    }



    public static void main(String[] args) {

        int[] nums = new int[]{3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        System.out.println(combinationSum4(nums, 10));
        for (int i = 1; i < 16; i++) {
            System.out.println(combinationSum41(nums, i) + "  :  " + combinationSum4(nums, i));
        }

        nums = new int[]{3,1,2,4};
        System.out.println(combinationSum4(nums, 4));
        System.out.println(combinationSum41(nums, 4));


    }
}
