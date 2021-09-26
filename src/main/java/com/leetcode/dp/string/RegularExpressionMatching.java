package com.leetcode.dp.string;

/**
 * TODO <a href="https://leetcode-cn.com/problems/regular-expression-matching/">Regular Expression Matching</>
 *
 * @author zms
 * @date Create in 5:12 下午 2019/11/26
 */
public class RegularExpressionMatching {
    /**
     * Given an input string (s) and a pattern (p),
     * implement regular expression matching with support for '.' and '*'.
     * <p>
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * Example 1:
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * <p>
     * Example 2:
     * Input: s = "aa", p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * <p>
     * Example 3:
     * Input: s = "ab", p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     * <p>
     * Example 4:
     * Input: s = "aab", p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     * <p>
     * Example 5:
     * Input: s = "mississippi", p = "mis*is*p*."
     * Output: false
     * <p>
     * Constraints:
     * 0 <= s.length <= 20
     * 0 <= p.length <= 30
     * s contains only lowercase English letters.
     * p contains only lowercase English letters, '.', and '*'.
     * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
     *
     * @param s could be empty and contains only lowercase letters a-z.
     * @param p could be empty and contains only lowercase letters a-z,
     *          and characters like . or *.
     * @return boolean
     */
    // dp[i][j] i表示s的下标，j表示p的下标
    // 状态：dp[i][j] 表示s(0, i)与p(0, j)是否匹配
    // 状态转移方程：
    // 情况一：当p[j] == . || p[j] == s[i](s[i] 不可能是 * ) 则 dp[i][j] = true;
    // 情况二：当p[j] == * 则 dp[i][j] = (s[i] == p[j - 1] && dp[i - 1][j - 2]) || (dp[i][j - 2])
    // 初始化：
    // 主要是针对空串
    private static boolean isMatch02(String s, String p) {
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
                    //  当p[j] == . || p[j] == s[i](s[i] 不可能是 * ) 则 dp[i][j] = true;
                    if (i > 0 && (s2[j - 1] == '.' || s2[j - 1] == s1[i - 1])) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // 当 s 是空的时候，p可能是 A*B*等
                    if (j >= 2) {
                        dp[i][j] |= dp[i][j - 2];
                    }
                    // 当p[j] == * 则 dp[i][j] = (s[i] == p[j - 1] && dp[i - 1][j - 2]) || (dp[i][j - 2])
                    if (i >= 1 && j >= 2) {
                        dp[i][j] |= dp[i - 1][j] && (s1[i - 1] == s2[j - 2] || s2[j - 2] == '.');
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch02("", ""));
        System.out.println(!isMatch02("a", ""));
        System.out.println(!isMatch02("aa", "a"));
        System.out.println(isMatch02("aa", "a*"));
        System.out.println(isMatch02("ab", ".*"));
        System.out.println(isMatch02("aab", "c*a*c*b"));
        System.out.println(!isMatch02("mississippi", "mis*is*p*."));
    }

}
