package com.leetcode.string;

/**
 * <a href="https://leetcode.com/problems/longest-palindrome/">Longest Palindrome</a>
 *
 * @author zms
 * @date 2:33 下午 2021/12/1
 */
public class LongestPalindrome {
    /**
     * Given a string s which consists of lowercase or uppercase letters,
     * return the length of the longest palindrome that can be built with those letters.
     * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
     * <p>
     * Example 1:
     * Input: s = "abccccdd"
     * Output: 7
     * Explanation:
     * One longest palindrome that can be built is "dccaccd", whose length is 7.
     * <p>
     * Example 2:
     * Input: s = "a"
     * Output: 1
     * <p>
     * Example 3:
     * Input: s = "bb"
     * Output: 2
     * <p>
     * Constraints:
     * 1 <= s.length <= 2000
     * s consists of lowercase and/or uppercase English letters only.
     */
    public static int longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return 1;
        }
        int[] lower = new int[26];
        int[] upper = new int[26];
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lower[c - 'a']++;
            } else {
                upper[c - 'A']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if ((lower[i] & 1) == 0 || (ans & 1) == 0) {
                ans += lower[i];
            } else if ((lower[i] & 1) != 0 && (ans & 1) != 0) {
                ans += lower[i] - 1;
            }
            if ((upper[i] & 1) == 0 || (ans & 1) == 0) {
                ans += upper[i];
            } else if ((upper[i] & 1) != 0 && (ans & 1) != 0) {
                ans += upper[i] - 1;
            }
        }
        return ans;
    }
}
