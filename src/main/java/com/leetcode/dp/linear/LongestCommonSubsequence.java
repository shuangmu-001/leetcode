package com.leetcode.dp.linear;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 11:33 AM 2020/4/1
 * TODO <a href="https://leetcode.com/problems/longest-common-subsequence/">
 *     Longest Common Subsequence</a>
 */
public class LongestCommonSubsequence {
    /**
     * Given two strings text1 and text2,
     * return the length of their longest common subsequence.
     * A subsequence of a string is a new string generated from the original string with
     * some characters(can be none) deleted without changing the relative order of the remaining characters.
     * (eg, "ace" is a subsequence of "abcde" while "aec" is not).
     * A common subsequence of two strings is a subsequence that is common to both strings.
     * If there is no common subsequence, return 0.
     *
     * Example 1:
     *      Input: text1 = "abcde", text2 = "ace"
     *      Output: 3
     *      Explanation: The longest common subsequence is "ace" and its length is 3.
     *
     * Example 2:
     *      Input: text1 = "abc", text2 = "abc"
     *      Output: 3
     *      Explanation: The longest common subsequence is "abc" and its length is 3.
     *
     * Example 3:
     *      Input: text1 = "abc", text2 = "def"
     *      Output: 0
     *      Explanation: There is no such common subsequence, so the result is 0.
     *
     * Constraints:
     *      1 <= text1.length <= 1000
     *      1 <= text2.length <= 1000
     *      The input strings consist of lowercase English characters only.
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        if(text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            char c1 = chars1[i - 1];
            for (int j = 1; j <= len2; j++) {
                char c2 = chars2[j - 1];
                if(c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[len1][len2];
    }



    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace") == 3);
        System.out.println(longestCommonSubsequence("abcde", "aec") == 2);
        System.out.println(longestCommonSubsequence("abcde", "a") == 1);
        System.out.println(longestCommonSubsequence("abcde", "f") == 0);
        System.out.println(longestCommonSubsequence("bsbininm","jmjkbkjkv") == 1);
        System.out.println(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq") == 6);
        System.out.println(longestCommonSubsequence("ezupkr","ubmrapg") == 2);
    }
}
