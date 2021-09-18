package com.leetcode.dp.knapsack;

import com.Utils;

/**
 * @author wcl
 * @date 2:11 下午 2020/9/11
 * <a href="https://leetcode.com/problems/perfect-squares/">Perfect Squares</a>
 */
public class PerfectSquares {
    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
     *
     * Example 1:
     *
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     * Example 2:
     *
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     */
    public static int numSquares(int n) {
        if(n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
            int index = 1;
            int target = index * index;
            while ( target <= n) {
                if(i >= target) {
                    dp[i] = Math.min(dp[i], dp[i - target] + 1);
                }
                index++;
                target = index * index;
            }
        }
        Utils.printArrays(dp);
        return dp[n] > n ? 0 : dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12) == 3);
        System.out.println(numSquares(13) == 2);
        System.out.println(numSquares(21) == 3);
        System.out.println(numSquares(16) == 1);
    }
}
