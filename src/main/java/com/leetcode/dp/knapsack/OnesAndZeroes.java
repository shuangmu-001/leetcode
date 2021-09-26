package com.leetcode.dp.knapsack;

import com.Utils;

/**
 * @author zms
 * @date 2:00 下午 2020/5/19
 * <a href="https://leetcode.com/problems/ones-and-zeroes/">
 * Ones And Zeroes</a>
 */
public class OnesAndZeroes {
    /**
     * Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.
     * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s.
     * Each 0 and 1 can be used at most once.
     *
     * Example 1:
     * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
     * Output: 4
     * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
     *
     * Example 2:
     * Input: strs = ["10","0","1"], m = 1, n = 1
     * Output: 2
     * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
     *
     * Constraints:
     *
     * 1 <= strs.length <= 600
     * 1 <= strs[i].length <= 100
     * strs[i] consists only of digits '0' and '1'.
     * 1 <= m, n <= 100
     */
    // 0 / 1 背包
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (String str : strs) {
            int zero = getZeroCount(str);
            int one = str.length() - zero;
            for (int i = m,j = n; i >= zero && j >= one;) {

                dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                if(i == zero) {
                    i = m;
                    j--;
                } else {
                    i--;
                }
            }
            Utils.printTwoArrays(dp);
        }

        return dp[m][n];
    }

    public static int getZeroCount(String str) {
        int res = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if(str.charAt(i) == '0') {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(findMaxForm(new String[]{"10","0","1"}, 1, 1));
        System.out.println(findMaxForm(new String[]{"10","0001","111001","1","0"}, 7, 5));
    }
}
