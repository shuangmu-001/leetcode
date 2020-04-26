package com.leetcode.dp;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 4:30 PM 2020/4/26
 * TODO <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">
 *     Longest Palindromic Subsequence</a>
 * @see LongestCommonSubsequence
 * @see LongestPalindromicSubstring
 */
public class LongestPalindromicSubsequence {
    /**
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
     *
     * Example 1:
     * Input:
     *
     * "bbbab"
     * Output:
     * 4
     * One possible longest palindromic subsequence is "bbbb".
     * Example 2:
     * Input:
     *
     * "cbbd"
     * Output:
     * 2
     * One possible longest palindromic subsequence is "bb".
     *
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
                if(c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[length][length];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("aacdefcaa") == 7);
    }
}
