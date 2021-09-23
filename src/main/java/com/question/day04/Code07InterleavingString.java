package com.question.day04;


/**
 * TODO 样本对应模型？
 * <a href="https://leetcode.com/problems/interleaving-string/">
 * interleaving string</a>
 *
 * @author wcl
 * @date 9:09 下午 2021/9/18
 */
public class Code07InterleavingString {
    /**
     * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
     * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
     * <p>
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
     * Note: a + b is the concatenation of strings a and b.
     * <p>
     * Example 1:
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * <p>
     * Example 2:
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * Output: false
     * <p>
     * Example 3:
     * Input: s1 = "", s2 = "", s3 = ""
     * Output: true
     * <p>
     * Constraints:
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1, s2, and s3 consist of lowercase English letters.
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        // dp[i][j] = s3(i + j) == s1(i) && dp[i-1][j] || s3(i + j) == s2(j) && dp[i][j - 1]
        boolean[] dp = new boolean[m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1));
                } else if (j == 0) {
                    dp[j] &= s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[j] &= s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    dp[j] |= (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
