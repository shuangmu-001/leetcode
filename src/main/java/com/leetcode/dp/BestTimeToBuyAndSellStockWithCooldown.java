package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
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
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     *
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */
    public static int maxProfit1(int[] prices) {
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
    // TODO
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }

        int cash = 0, hold = -prices[0], frost = 0;
        // cash 是利润， hold是持有 frost 冻结
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - frost);
            // cash
            hold = Math.max(hold, cash - prices[i]);
            if(i >= 2) {
               frost = Math.max(frost, hold - prices[i]);
            }
        }
        return cash;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{6,1,6,4,3,0,2}) == 7);
        System.out.println(maxProfit(new int[]{1,6,0,2}) == 5);
    }
}
