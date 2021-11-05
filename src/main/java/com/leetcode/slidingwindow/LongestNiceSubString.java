package com.leetcode.slidingwindow;

/**
 * <a href="https://leetcode.com/problems/longest-nice-substring/">Longest Nice Substring</a>
 *
 * @author zms
 * @date 6:55 下午 2021/11/4
 */
public class LongestNiceSubString {
    /**
     * A string s is nice if, for every letter of the alphabet that s contains,
     * it appears both in uppercase and lowercase.
     * For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear.
     * However, "abA" is not because 'b' appears, but 'B' does not.
     * Given a string s, return the longest substring of s that is nice.
     * If there are multiple, return the substring of the earliest occurrence.
     * If there are none, return an empty string.
     * <p>
     * Example 1:
     * Input: s = "YazaAay"
     * Output: "aAa"
     * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
     * "aAa" is the longest nice substring.
     *
     * Example 2:
     * Input: s = "Bb"
     * Output: "Bb"
     * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
     *
     * Example 3:
     * <p>
     * Input: s = "c"
     * Output: ""
     * Explanation: There are no nice substrings.
     * Example 4:
     * <p>
     * Input: s = "dDzeE"
     * Output: "dD"
     * Explanation: Both "dD" and "eE" are the longest nice substrings.
     * As there are multiple longest nice substrings, return "dD" since it occurs earlier.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 100
     * s consists of uppercase and lowercase English letters.
     */
    public static String longestNiceSubstring(String s) {
        int start = 0;
        int len = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            int up = 0;
            int lower = 0;
            for (int j = i; j < n; j++) {
                char c = str[j];
                if(c >= 'A' && c <= 'Z') {
                    up |= (1 << (c - 'A'));
                }
                if(c >= 'a' && c <= 'z') {
                    lower |= (1 << (c - 'a'));
                }
                if(up == lower && j - i + 1 > len) {
                    len = j -i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + len);
    }

    public static void main(String[] args) {
        System.out.println(longestNiceSubstring("YazaAay"));
        System.out.println(longestNiceSubstring("Bb"));
        System.out.println(longestNiceSubstring("c"));
        System.out.println(longestNiceSubstring("dDzeE"));
    }
}
