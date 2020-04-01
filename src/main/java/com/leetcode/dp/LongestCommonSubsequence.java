package com.leetcode.dp;

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
        if(text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int start = 0;
        while(start < text2.length() && text1.indexOf(text2.charAt(start)) < 0) {
            start++;
        }
        if(start >= text2.length()) {
            return 0;
        }
        int result = 0;
        for (int i = start; i < text2.length(); i++) {
            int len = 0;
            int begin = 0;
            for (int j = i; j < text2.length(); j++) {
                int index = text1.indexOf(text2.charAt(j), begin);
                if(index >= 0) {
                    begin = index + 1;
                    len++;
                }
            }
            result = Math.max(len, result);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace") == 3);
        System.out.println(longestCommonSubsequence("abcde", "aec") == 2);
        System.out.println(longestCommonSubsequence("abcde", "a") == 1);
        System.out.println(longestCommonSubsequence("abcde", "f") == 0);
        System.out.println(longestCommonSubsequence("bsbininm","jmjkbkjkv") == 1);
        System.out.println(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq") == 6);
    }
}
