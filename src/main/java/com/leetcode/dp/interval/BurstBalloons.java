package com.leetcode.dp.interval;

import com.leetcode.Utils;


/**
 * @author wcl
 * @date 4:07 PM 2020/4/1
 * TODO <a href="https://leetcode.com/problems/burst-balloons/">
 * Burst Balloons</a>
 */
public class BurstBalloons {
    /**
     * Given n balloons, indexed from 0 to n-1.
     * Each balloon is painted with a number on it represented by array nums.
     * You are asked to burst all the balloons.
     * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
     * Here left and right are adjacent indices of i.
     * After the burst, the left and right then becomes adjacent.
     * Find the maximum coins you can collect by bursting the balloons wisely.
     * <p>
     * Note:
     * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
     * 1 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * Example:
     * Input: [3,1,5,8]
     * Output: 167
     * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     */
    // dp[i][j] 表示 i 到 j 的最大值
    // k 表示 i 到 j 中最后被戳破的气球
    // left = i == 0 ? 1 : nums[i - 1]
    // right = j == n - 1 ? 1 : nums[j + 1]
    // dp[i][j] = Math.max(dp[i][k - 1] + dp[k + 1][j] + left * nums[k] * right) (i <= k <= j)
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    int left = i == 0 ? 1 : nums[i - 1];
                    int right = j == n - 1 ? 1 : nums[j + 1];
                    int leftMax = k == i ? 0 : dp[i][k - 1];
                    int rightMax = k == j ? 0 : dp[k + 1][j];
                    dp[i][j] = Math.max(leftMax + rightMax + nums[k] * left * right, dp[i][j]);
                }
//                Utils.printTwoArrays(dp);
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(167 == maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println(288 == maxCoins(new int[]{3, 4, 5, 8}));
        System.out.println(260 == maxCoins(new int[]{4, 3, 5, 8}));
        System.out.println(96 == maxCoins(new int[]{3, 4, 4, 3}));
    }
}
