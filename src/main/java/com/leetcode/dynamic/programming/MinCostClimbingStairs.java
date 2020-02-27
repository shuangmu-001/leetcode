package com.leetcode.dynamic.programming;

/**
 * @author wcl
 * @date 12:02 PM 2020/2/26
 * {@link "https://leetcode.com/problems/min-cost-climbing-stairs/"}
 * @see ClimbingStairs
 */
public class MinCostClimbingStairs {
    /**
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     * Once you pay the cost, you can either climb one or two steps.
     * You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
     *
     * Example 1:
     *      Input: cost = [10, 15, 20]
     *      Output: 15
     *      Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     *
     * Example 2:
     *      Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     *      Output: 6
     *      Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     *
     * Note:
     *      cost will have a length in the range [2, 1000].
     *      Every cost[i] will be an integer in the range [0, 999].
     * DP java bottom-up solution
     * 思路 dp[n] = min(dp[n - 1] + cost[n - 1], dp[n - 2] + cost[n - 2])
     *     ...
     *     dp[2] = min(cost[1], cost[0])
     */
    public static int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[length];
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}) == 15);
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}) == 6);
    }
}
