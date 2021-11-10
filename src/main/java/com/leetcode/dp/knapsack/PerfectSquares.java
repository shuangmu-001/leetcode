package com.leetcode.dp.knapsack;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/perfect-squares/">Perfect Squares</a>
 *
 * @author zms
 * @date 2:11 下午 2020/9/11
 */
public class PerfectSquares {
    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
     * <p>
     * Example 1:
     * <p>
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     * Example 2:
     * <p>
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     */
    public static int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
            int index = 1;
            int target = index * index;
            while (target <= n) {
                if (i >= target) {
                    dp[i] = Math.min(dp[i], dp[i - target] + 1);
                }
                index++;
                target = index * index;
            }
        }
        Utils.printArrays(dp);
        return dp[n] > n ? 0 : dp[n];
    }

    public static int numSquares01(int n) {
        Integer[] dp = new Integer[n + 1];
        return dfs(n, dp);
    }

    public static int dfs(int rest, Integer[] dp) {
        if (dp[rest] != null) {
            return dp[rest];
        }
        if (rest == 0) {
            dp[rest] = 0;
            return 0;
        }
        int ans = rest;
        for (int i = 1; i * i <= rest; i++) {
            int res = dfs(rest - i * i, dp) + 1;
            ans = Math.min(res, ans);
        }
        dp[rest] = ans;
        return ans;
    }

    public static int numSquares02(int n) {
        int[] dp = new int[n + 1];
        for (int rest = 1; rest <= n; rest++) {
            dp[rest] = rest;
            if (isSquare(rest)) {
                dp[rest] = 1;
                continue;
            }
            int index = 1;
            int target = index * index;
            while (target <= rest) {
                dp[rest] = Math.min(dp[rest - target] + 1, dp[rest]);
                index++;
                target = index * index;
            }
        }
        return dp[n];
    }

    protected static boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    public static void main(String[] args) {
        System.out.println(numSquares01(12) == 3);
        System.out.println(numSquares01(13) == 2);
        System.out.println(numSquares01(21) == 3);
        System.out.println(numSquares01(16) == 1);
    }
}
