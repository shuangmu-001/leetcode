package com.leetcode.dynamic.programming;

/**
 * @author wcl
 * @date 7:44 PM 2020/2/25
 * {@link "https://leetcode.com/problems/climbing-stairs/"}
 */
public class ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     *
     * Example 1:
     *      Input: 2
     *      Output: 2
     *      Explanation: There are two ways to climb to the top.
     *          1. 1 step + 1 step
     *          2. 2 steps
     *
     * Example 2:
     *      Input: 3
     *      Output: 3
     *      Explanation: There are three ways to climb to the top.
     *          1. 1 step + 1 step + 1 step
     *          2. 1 step + 2 steps
     *          3. 2 steps + 1 step
     *
     * 假设只剩最有一步到第10级台阶 有两种可能从9->10或从8->10，则问题成0->9和0->8，
     *                             即：f(n) = f(n-1) + f(n-2)
     *                                f(n-1) = f(n-2) + f(n-3)
     *                                f(n-2) = f(n-3) + f(n-4)
     *                                ...
     *                                f(3) = f(2) + f(1)
     */
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));

    }

}
