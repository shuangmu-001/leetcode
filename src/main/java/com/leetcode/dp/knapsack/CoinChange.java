package com.leetcode.dp.knapsack;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 2:40 下午 2020/5/20
 * <a href="https://leetcode.com/problems/coin-change/">
 * Coin Change</a>
 * TODO 完全背包
 */
public class CoinChange {
    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     * Note:
     * You may assume that you have an infinite number of each kind of coin.
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount ; i++) {
            dp[i] = amount + 1;
            for (int coin : coins) {
                if(i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        Utils.printArrays(dp);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }
}
