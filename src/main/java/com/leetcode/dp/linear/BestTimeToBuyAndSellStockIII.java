package com.leetcode.dp.linear;

import com.Utils;

/**
 * @author zms
 * @date 6:38 PM 2020/3/24
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/">
 *     Best Time to Buy and Sell Stock III</a>
 */
public class BestTimeToBuyAndSellStockIII {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note: You may not engage in multiple transactions at the same time
     * (i.e., you must sell the stock before you buy again).
     *
     * Example 1:
     *
     * Input: [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * Example 2:
     *
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     *              engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     * 开始:
     *      状态的定义:dp[i] 第i天结束的最大收益
     *      状态方程: 即dp[i] 与 dp[i - 1] 的关系
     *              当dp[i - 1] 手上持有股票则 卖掉
     *              当dp[i - 1] 手上持有股票不变
     * 问题: 没有记录手上是否持有股票
     *      状态的定义:dp[i][j] i 表示第几天，j 表示手上是否持有股票 (0表示没有，1表示有)
     *      状态方程: 即dp[i] 与 dp[i - 1] 的关系
     *              dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
     *              dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
     * 问题: 交易次数限制提现不出来
     *      状态的定义:dp[i][k][j] i 表示第几天，j 表示手上是否持有股票 (0表示没有，1表示有) k 表示交易的次数
     *      状态方程:
     *          dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k -1][1] + prices[i])
     *          dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k][0] - prices[i])
     *      最大收益为dp[i][1]数组的最大值
     */
    public int maxProfit1(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[][][] dp = new int[prices.length][3][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }
        dp[0][0][1] = -prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
            int count = Math.min(((i+1) >> 1) + 1, 3);
            for (int j = 1; j < count; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                max = Math.max(dp[i][j][0], max);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }

        }
        // Utils.printThreeArrays(dp);
        return max;
    }
    public int maxProfit2(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[][][] dp = new int[prices.length][3][2];

        dp[0][0][1] = -prices[0];
        dp[0][1][1] = Integer.MIN_VALUE;
        dp[0][2][1] = Integer.MIN_VALUE;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][1] = Math.max(dp[i - 1][0][1], -prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
            max = Math.max(dp[i][1][0], max);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);
            max = Math.max(dp[i][2][0], max);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][2][0] - prices[i]);

        }

        return max;
    }
    public static int maxProfit3(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[][][] dp = new int[prices.length][2][3];
        dp[0][1][0] = -prices[0];
        dp[0][1][1] = Integer.MIN_VALUE;
        dp[0][1][2] = Integer.MIN_VALUE;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], -prices[i]);
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][0] + prices[i]);
            max = Math.max(dp[i][0][1], max);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][1] - prices[i]);
            dp[i][0][2] = Math.max(dp[i - 1][0][2], dp[i - 1][1][1] + prices[i]);
            max = Math.max(dp[i][0][2], max);
            dp[i][1][2] = Math.max(dp[i - 1][1][2], dp[i - 1][0][2] - prices[i]);

        }
        Utils.printThreeArrays(dp);
        return max;
    }

    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int[][] before = new int[2][3];
        before[1][0] = -prices[0];
        before[1][1] = Integer.MIN_VALUE;
        before[1][2] = Integer.MIN_VALUE;

        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int first = Math.max(before[1][0], -prices[i]);
            int second1 = Math.max(before[0][1], before[1][0] + prices[i]);
            max = Math.max(second1, max);
            int second2 = Math.max(before[1][1], before[0][1] - prices[i]);
            before[1][0] = first;
            before[0][1] = second1;
            int tree1 = Math.max(before[0][2], before[1][1] + prices[i]);
            max = Math.max(tree1, max);
            int tree2 = Math.max(before[1][2], before[0][2] - prices[i]);
            before[0][2] = tree1;
            before[1][1] = second2;
            before[1][2] = tree2;

        }
        Utils.printTwoArrays(before);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4}) == 6);
        System.out.println(maxProfit(new int[]{1,2,3,4,5}) == 4);
        System.out.println(maxProfit(new int[]{7,6,4,3,1}) == 0);
    }
}
