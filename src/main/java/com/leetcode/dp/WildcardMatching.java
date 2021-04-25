package com.leetcode.dp;

/**
 * @author zms
 * @date 3:41 下午 2021/4/25
 * <a href="https://leetcode.com/problems/wildcard-matching/">
 * Wildcard Matching</a>
 */
public class WildcardMatching {
    /**
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
     * <p>
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     * <p>
     * Example 1:
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * <p>
     * Example 2:
     * Input: s = "aa", p = "*"
     * Output: true
     * Explanation: '*' matches any sequence.
     * <p>
     * Example 3:
     * Input: s = "cb", p = "?a"
     * Output: false
     * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
     * <p>
     * Example 4:
     * Input: s = "adceb", p = "*a*b"
     * Output: true
     * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
     * <p>
     * Example 5:
     * Input: s = "acdcb", p = "a*c?b"
     * Output: false
     * <p>
     * Constraints:
     * 0 <= s.length, p.length <= 2000
     * s contains only lowercase English letters.
     * p contains only lowercase English letters, '?' or '*'.
     */
    // 状态：dp[i][j] 表示 s[i] 和 p[j]是否匹配
    // 状态转移方程：
    // 情况一：p[j] == ? 或 p[j] == s[i]  dp[i][j] = dp[i - 1][j - 1]
    // TODO 情况二：dp[i][j - 1] 或 dp[i - 1][j]
    // * 表示的是任意字符串 aajefoiiejvienivlen   a*n
    public static boolean isMatch(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] s2 = p.toCharArray();
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }
                dp[i][j] = false;
                if (s2[j - 1] != '*') {
                    // p[j] == ? 或 p[j] == s[i]  dp[i][j] = dp[i - 1][j - 1]
                    if (i > 0 && (s2[j - 1] == '?' || s2[j - 1] == s1[i - 1])) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // dp[i][j - 1] 或 dp[i - 1][j]
                    dp[i][j] |= dp[i][j - 1];
                    if (i > 0) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(!isMatch("aa", "a"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(!isMatch("cb", "?a"));
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(!isMatch("acdcb", "a*c?b"));
        System.out.println(isMatch("aajefoiiejvienivlen", "a*n"));
    }
}
