package com.leetcode.dp.interval;

import com.Utils;
import com.leetcode.dp.linear.LongestCommonSubsequence;

/**
 * @author zms
 * @date 4:30 PM 2020/4/26
 * TODO <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">
 * Longest Palindromic Subsequence</a>
 * @see LongestCommonSubsequence
 * @see LongestPalindromicSubstring
 */
public class LongestPalindromicSubsequence {
    /**
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
     * <p>
     * Example 1:
     * Input:
     * <p>
     * "bbbab"
     * Output:
     * 4
     * One possible longest palindromic subsequence is "bbbb".
     * Example 2:
     * Input:
     * <p>
     * "cbbd"
     * Output:
     * 2
     * One possible longest palindromic subsequence is "bb".
     * <p>
     * Runtime: 58 ms, faster than 24.38% of Java online submissions for Longest Palindromic Subsequence.
     * Memory Usage: 72.9 MB, less than 5.55% of Java online submissions for Longest Palindromic Subsequence.
     */
    public static int longestPalindromeSubseq(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[length + 1][length + 1];
        for (int i = 1; i <= length; i++) {
            char c1 = chars[i - 1];
            for (int j = 1; j <= length; j++) {
                char c2 = chars[length - j];
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[length][length];
    }

    /**
     * dp[i][j] 表示 i 到 j的最大回文子序列
     */
    public static int longestPalindromeSubseq1(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][i] = 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    if (chars[i] == chars[j]) {
                        dp[i][j] = Math.max(dp[i + 1][j - 1] + 2, dp[i][j]);
                    }
                }
            }
            Utils.printTwoArrays(dp);
        }

        return dp[0][n - 1];
    }

    // TODO 记忆化搜索的做法




    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq1("aacdefcaa") == 7);
    }
}
