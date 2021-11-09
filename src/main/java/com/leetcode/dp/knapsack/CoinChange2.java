package com.leetcode.dp.knapsack;

/**
 * <a href="https://leetcode.com/problems/coin-change-2/">Coin Change 2</a>
 *
 * @author zms
 * @date 2:45 下午 2020/5/20
 * @see TargetSum
 */
public class CoinChange2 {
    /**
     * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * Example 2:
     * <p>
     * Input: amount = 3, coins = [2]
     * Output: 0
     * Explanation: the amount of 3 cannot be made up just with coins of 2.
     * Example 3:
     * <p>
     * Input: amount = 10, coins = [10]
     * Output: 1
     * <p>
     * <p>
     * Note:
     * <p>
     * You can assume that
     * <p>
     * 0 <= amount <= 5000
     * 1 <= coin <= 5000
     * the number of coins is less than 500
     * the answer is guaranteed to fit into signed 32-bit integer
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

    public static int change1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

    public int change02(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= amount; rest++) {
//                for(int i = index; i < coins.length; i++) {
//                    if(rest >= coins[i]) {
//                        dp[index][rest] += dp[i][rest - coins[i]];
//                    }
//                }
                // 斜率优化
                dp[index][rest] += dp[index + 1][rest];
                if (rest >= coins[index]) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
            }
        }
        return dp[0][amount];
    }

    // 空间压缩
    public int change03(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = coins[index]; rest <= amount; rest++) {
                dp[rest] += dp[rest - coins[index]];
            }
        }
        return dp[amount];
    }

    public int process01(int rest, int[] coins, int index) {
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        for (int i = index; i < coins.length; i++) {
            if (rest >= coins[i]) {
                ans += process01(rest - coins[i], coins, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] coins = new int[]{2, 3, 7};
        System.out.println(change(5, coins));
        System.out.println(change1(5, coins));
        System.out.println(change(100, coins));

        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 / 3); j++) {
                for (int k = 0; k <= (100 / 2); k++) {
                    if (i * 7 + j * 3 + k * 2 == 100) {
                        count += 1;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
