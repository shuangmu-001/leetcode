package com.leetcode.dp.knapsack;

import com.Test;
import com.Utils;

/**
 * <a href="https://leetcode.com/problems/coin-change/">Coin Change</a>
 *
 * @author zms
 * @date 2:40 下午 2020/5/20
 */
public class CoinChange implements Test {
    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * <p>
     * Example 1:
     * <p>
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     * <p>
     * Input: coins = [2], amount = 3
     * Output: -1
     * Note:
     * You may assume that you have an infinite number of each kind of coin.
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        Utils.printArrays(dp);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int coinChange01(int[] coins, int amount) {
        int n = coins.length;
        int ans = process01(coins, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process01(int[] coins, int rest, int index) {
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] < rest; zhang++) {
            int res = process01(coins, rest - zhang * coins[index], index + 1);
            if (res != Integer.MAX_VALUE) {
                ans = Math.min(res + zhang, ans);
            }
        }
        return ans;
    }

    public static int coinChange02(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 0;
        for (int rest = 1; rest <= amount; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    if (dp[index + 1][rest - zhang * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index + 1][rest - zhang * coins[index]] + zhang, dp[index][rest]);
                    }
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static int coinChange03(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int rest = 1; rest <= amount; rest++) {
            dp[n][rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest - coins[index]] + 1, dp[index][rest]);
                }
            }
        }

        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static int coinChange04(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int rest = 1; rest <= amount; rest++) {
            dp[rest] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
                if (rest - coins[index] >= 0 && dp[rest - coins[index]] != Integer.MAX_VALUE) {
                    dp[rest] = Math.min(dp[rest - coins[index]] + 1, dp[rest]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange02(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange02(new int[]{2}, 3));
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int amount = genRandomNum(20);
        int ans01 = coinChange02(arr, amount);
        int ans02 = coinChange03(arr, amount);
        if (ans01 != ans02) {
            Utils.printArrays(arr);
            System.out.printf("错误输入:%d\n", amount);
            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
