package com.leetcode.slidingwindow;

/**
 * <a href="https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/">
 * Substrings of Size Three with Distinct Characters</a>
 *
 * @author zms
 * @date 11:52 上午 2021/11/8
 */
public class SubStringsOfSizeThreeWithDistinctCharacters {
    /**
     * A string is good if there are no repeated characters.
     * Given a string s, return the number of good substrings of length three in s.
     * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
     * A substring is a contiguous sequence of characters in a string.
     * <p>
     * Example 1:
     * Input: s = "xyzzaz"
     * Output: 1
     * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
     * The only good substring of length 3 is "xyz".
     * <p>
     * Example 2:
     * Input: s = "aababcabc"
     * Output: 4
     * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
     * The good substrings are "abc", "bca", "cab", and "abc".
     * <p>
     * Constraints:
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     */
    public static int countGoodSubstrings(String s) {
        int n = s.length();
        if (n <= 2) {
            return 0;
        }
        int ans = 0;
        char[] str = s.toCharArray();
        char first = str[0];
        char second = str[1];
        for (int i = 2; i < n; i++) {
            char cur = str[i];
            if (first != second && cur != first
                    && cur != second) {
                ans++;
            }
            first = second;
            second = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("xyzzzaz") == 1);
        System.out.println(countGoodSubstrings("xy") == 0);
    }
}
