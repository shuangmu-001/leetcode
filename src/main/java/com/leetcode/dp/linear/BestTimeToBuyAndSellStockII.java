package com.leetcode.dp.linear;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">Best Time to Buy and Sell Stock II</a>
 *
 * @author zms
 * @date 11:07 上午 2021/10/27
 */
public class BestTimeToBuyAndSellStockII {
    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
     * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
     * However, you can buy it then immediately sell it on the same day.
     * <p>
     * Find and return the maximum profit you can achieve.
     * <p>
     * Example 1:
     * Input: prices = [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * Total profit is 4 + 3 = 7.
     * <p>
     * Example 2:
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Total profit is 4.
     * <p>
     * Example 3:
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
     * <p>
     * Constraints:
     * 1 <= prices.length <= 3 * 10^4
     * 0 <= prices[i] <= 10^4
     */
    // 5 - 1 = (5 - 4) + (4 - 3) + (3 - 2) + (2 - 1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            if (min < prices[i]) {
                ans += prices[i] - min;
            }
            min = prices[i];
        }
        return ans;
    }
}
