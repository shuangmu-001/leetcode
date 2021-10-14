package com.algs.recursion;

import com.Test;

/**
 * 范围模型
 * TODO 最长回文子序列的个数
 * <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">
 * Longest Palindromic Subsequence</a>
 *
 * @author zms
 * @date 5:00 下午 2021/10/14
 */
public class Code11LongestPalindromeSubsequence implements Test {

    public static int longestPalindromeSubseq01(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process01(str, 0, s.length() - 1);
    }

    // l...r l到r的之间的最长回文子序列
    public static int process01(char[] str, int l, int r) {
        if (r < l) {
            return 0;
        }
        if (l == r) {
            return 1;
        }
        int ans = 0;
        if (str[l] == str[r]) {
            ans = process01(str, l + 1, r - 1) + 2;
        }
        int left = process01(str, l + 1, r);
        int right = process01(str, l, r - 1);
        ans = Math.max(ans, Math.max(left, right));
        return ans;
    }

    public static int longestPalindromeSubseq02(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                if (str[l] == str[r]) {
                    dp[l][r] = Math.max(dp[l + 1][r - 1] + 2, dp[l][r]);
                }
            }
        }
        return dp[0][n - 1];
    }

    @Override
    public void test(int n) {
        String s = genTargetStr(n);
        int target = longestPalindromeSubseq01(s);
        int ans = longestPalindromeSubseq02(s);
        if (target != ans) {
            System.out.printf("错误的输入:%s\n", s);
            System.out.printf("错误的输出:%d,%d\n", target, ans);
            throw new RuntimeException();
        }
    }
}
