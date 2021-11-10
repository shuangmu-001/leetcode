package com.leetcode.dp.knapsack;

import com.Test;

/**
 * <a href="https://leetcode.com/problems/integer-break/">Integer Break</a>
 *
 * @author zms
 * @date 12:01 下午 2021/11/10
 * @see CombinationSumIV
 */
public class IntegerBreak implements Test {
    /**
     * Given an integer n, break it into the sum of k positive integers,
     * where k >= 2, and maximize the product of those integers.
     * Return the maximum product you can get.
     * <p>
     * Example 1:
     * Input: n = 2
     * Output: 1
     * Explanation: 2 = 1 + 1, 1 × 1 = 1.
     * <p>
     * Example 2:
     * Input: n = 10
     * Output: 36
     * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
     * <p>
     * Constraints:
     * 2 <= n <= 58
     */
    public static int integerBreak01(int n) {
        Integer[] dp = new Integer[n + 1];
        return dfs01(n, dp);
    }

    public static int dfs01(int rest, Integer[] dp) {
        if (dp[rest] != null) {
            return dp[rest];
        }
        if (rest == 1) {
            dp[rest] = 1;
            return 1;
        }
        int ans = 0;
        for (int i = 1; i < rest; i++) {
            int product = dfs01(rest - i, dp) * i;
            product = Math.max(product, i * (rest - i));
            ans = Math.max(ans, product);
        }
        dp[rest] = ans;
        return ans;
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int rest = 2; rest <= n; rest++) {
            for (int i = 1; i < rest; i++) {
                int product = dp[rest - i] * i;
                product = Math.max(product, i * (rest - i));
                dp[rest] = Math.max(dp[rest], product);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(2) == 1);
        System.out.println(integerBreak(10) == 36);
        IntegerBreak b = new IntegerBreak();
        for (int i = 0; i < 100; i++) {
            int n = b.genRandomNum(56) + 2;
            int ans01 = integerBreak01(n);
            int ans02 = integerBreak(n);
            if (ans01 != ans02) {
                System.out.printf("错误输入:%d\n", n);
                System.out.printf("错误输出:%d,%d\n", ans01, ans02);
                break;
            }
        }
    }
}
