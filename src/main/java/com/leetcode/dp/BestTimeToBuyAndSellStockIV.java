package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 4:47 PM 2020/3/24
 * TODO <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/">
 *     Best Time to Buy and Sell Stock IV</a>
 */
public class BestTimeToBuyAndSellStockIV {
    /**
     * Say you have an array for which the i-th element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     *
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [2,4,1], k = 2
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     * Example 2:
     *
     * Input: [3,2,6,5,0,3], k = 2
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
     *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     */
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][prices.length];
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                dp[i][j] = Math.max(prices[j] - prices[i], 0);
                max = Math.max(dp[i][j], max);
            }
        }
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                dp[i][j] = Math.max(prices[j] - prices[i], 0);
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }
}
