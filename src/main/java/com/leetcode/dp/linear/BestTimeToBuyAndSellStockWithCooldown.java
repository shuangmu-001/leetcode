package com.leetcode.dp.linear;

import com.Utils;

/**
 * @author zms
 * @date 5:03 PM 2020/3/24
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">
 *     Best Time to Buy and Sell Stock with Cooldown</a>
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit.
     * You may complete as many transactions as you like
     * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before01 you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     *
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[] dp = new int[prices.length];
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int price = Math.max(prices[j] - prices[i], 0);
                if(i > 2) {
                    price += dp[i - 2];
                }
                dp[j] = Math.max(price, dp[j]);
                max = Math.max(max, dp[j]);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
            Utils.printArrays(dp);
        }

        return max;
    }
    public static int maxProfit01(int[] prices) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][3];
        return process01(prices, 0, 2, dp);
    }

    // price 表示前一个状态 0 有 1 表示卖 2 表示冷静期
    public static int process01(int[] prices, int index, int price, Integer[][] dp) {
        if (index >= prices.length) {
            return 0;
        }
        if(dp[index][price] != null) {
            return dp[index][price];
        }
        // index 不买不卖
        int ans = process01(prices, index + 1, price == 1 ? 2 : price, dp);

        if (price == 0) {
            // index 只能卖
            ans = Math.max(ans, process01(prices, index + 1, 1, dp) + prices[index]);
        } else if (price == 2) {
            // index 只能买
            ans = Math.max(ans, process01(prices, index + 1, 0, dp) - prices[index]);
        }
        dp[index][price] = ans;
        return ans;
    }

    public static int maxProfit02(int[] prices) {
        int n = prices.length;
        int buy = 0;
        int sell = 0;
        int cool = 0;
        for(int index = n - 1; index >= 0; index--) {
            int temp = buy;
            buy = Math.max(buy, sell + prices[index]);
            sell = cool;
            cool = Math.max(cool, temp - prices[index]);
        }
        return cool;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit02(new int[]{6,1,6,4,3,0,2}) == 7);
        System.out.println(maxProfit02(new int[]{1,6,0,2}) == 5);
    }
}
