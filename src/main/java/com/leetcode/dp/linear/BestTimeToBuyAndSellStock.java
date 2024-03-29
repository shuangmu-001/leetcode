package com.leetcode.dp.linear;

/**
 * {@link "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/"}
 *
 * @author zms
 * @date 10:14 AM 2020/2/26
 */
public class BestTimeToBuyAndSellStock {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     * Note that you cannot sell a stock before01 you buy one.
     * <p>
     * Example 1:
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     * Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */
    public static int maxProfit01(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        int buying = prices[0];
        int price = 0;
        for (int i = 1; i < length; i++) {
            if (prices[i] < buying) {
                buying = prices[i];
            } else {
                int temp = prices[i] - buying;
                price = Math.max(price, temp);
            }
        }
        return price;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, prices[i] - min);
            min = Math.min(prices[i], min);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5);
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}) == 0);
    }
}
