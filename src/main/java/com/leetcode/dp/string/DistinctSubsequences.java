package com.leetcode.dp.string;

import com.Utils;

/**
 * @author zms
 * @date 10:37 上午 2021/4/27
 * <a href="https://leetcode.com/problems/distinct-subsequences/">
 * Distinct Subsequences</a>
 * @see com.leetcode.dp.linear.NumberOfLongestIncreasingSubsequence
 */
public class DistinctSubsequences {
    /**
     * Given two strings s and t, return the number of distinct subsequences of s which equals t.
     * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     * It is guaranteed the answer fits on a 32-bit signed integer.
     * <p>
     * Example 1:
     * Input: s = "rabbbit", t = "rabbit"
     * Output: 3
     * Explanation:
     * As shown below, there are 3 ways you can generate "rabbit" from S.
     * rabbbit
     * rabbbit
     * rabbbit
     * <p>
     * Example 2:
     * Input: s = "babgbag", t = "bag"
     * Output: 5
     * Explanation:
     * As shown below, there are 5 ways you can generate "bag" from S.
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * <p>
     * <p>
     * Constraints:
     * 1 <= s.length, t.length <= 1000
     * s and t consist of English letters.
     */
    // 前提只能删除s中的元素，不能删除t中的元素
    // 状态方程： dp[i][j] s(i) 中有多少个 t(j)
    // 状态转移方程：dp[i][j]
    // 情况一：s[i] != t[j]，dp[i - 1][j]
    // 情况二：s[i] == t[j], dp[i - 1][j - 1] + dp[i - 1][j]
    // 初始化：当s为空，0；当t为空，1；
    public static int numDistinct01(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return 0;
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    // 压缩空间
    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return 0;
        }
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int pre = dp[0];
            for (int j = 1; j <= i && j <= m; j++) {
                int temp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += pre;
                }
                pre = temp;
            }
            Utils.printArrays(dp);
        }
        return dp[m];
    }

    public static void main(String[] args) {
        System.out.println(5 == numDistinct("babgbag", "bag"));
        System.out.println(3 == numDistinct("rabbbit", "rabbit"));
    }
}
