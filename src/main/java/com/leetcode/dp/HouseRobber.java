package com.leetcode.dp;

/**
 * @author wcl
 * @date 1:49 PM 2020/2/27
 * {@link "https://leetcode.com/problems/house-robber/"}
 * @see MinCostClimbingStairs
 */
public class HouseRobber {
    /**
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed,
     * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
     * and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house,
     * determine the maximum amount of money you can rob tonight without alerting the police.
     *
     * Example 1:
     *      Input: [1,2,3,1]
     *      Output: 4
     *      Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     *                   Total amount you can rob = 1 + 3 = 4.
     *
     * Example 2:
     *      Input: [2,7,9,3,1]
     *      Output: 12
     *      Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
     *                   Total amount you can rob = 2 + 9 + 1 = 12.
     *  DP java bottom-up solution
     * 思路 dp[n] = Max(dp[n - 1], dp[n - 2] + nums[n])
     *     ...
     *     dp[2] = Max(dp[1], dp[0] + nums[2]) 即 ： dp[2] = Max(Max(nums[0],nums[1]), nums[0] + nums[2])
     */
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if(length < 2) {
            return nums[0];
        }
        // 空间使用率可以优化, 用两个变量代替dp数组
        // 使用dp数组的原因是显示思路是dp
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1,2,3,1}) == 4);
        System.out.println(rob(new int[] {2,7,9,3,1}) == 12);
        System.out.println(rob(new int[] {1,2,1,1,1,1,4}) == 7);
    }
}
