package com.leetcode.dynamic.programming;

/**
 * @author wcl
 * @date 9:46 PM 2020/2/26
 * TODO {@link "https://leetcode.com/problems/is-subsequence/"}
 * TODO 如何用动态规划来解释当前问题
 */
public class IsSubsequence {
    /**
     * Given a string s and a string t, check if s is subsequence of t.
     * You may assume that there is only lower case English letters in both s and t.
     * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
     *
     * Example 1:
     *      s = "abc", t = "ahbgdc"
     *      Return true.
     *
     * Example 2:
     *      s = "axc", t = "ahbgdc"
     *      Return false.
     *
     * Follow up:
     *      If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B,
     *      and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
     * Runtime: 9 ms, faster than 60.86% of Java online submissions for Is Subsequence.
     * Memory Usage: 43.9 MB, less than 100.00% of Java online submissions for Is Subsequence.
     */
    public static boolean isSubsequence(String s, String t) {
        if(s == null || s.length() > t.length()){
            return false;
        }
        if(s.length() == 0) {
            return true;
        }
        for (int i = 0, j = 0; i < t.length(); i++) {
            char sC = s.charAt(j);
            char tC = t.charAt(i);
            if(sC == tC) {
                j++;
            }
            if(j == s.length()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(!isSubsequence("axc", "ahbgdc"));
    }
}
