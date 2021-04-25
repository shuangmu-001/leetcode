package com.leetcode.dp;

import java.util.Arrays;

/**
 * @author zms
 * @date 10:53 上午 2021/4/25
 * <a href="https://leetcode.com/problems/count-sorted-vowel-strings/">
 * Count Sorted Vowel Strings</a>
 */
public class CountSortedVowelStrings {
    /**
     * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
     * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
     * <p>
     * Example 1:
     * Input: n = 1
     * Output: 5
     * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
     * <p>
     * Example 2:
     * Input: n = 2
     * Output: 15
     * Explanation: The 15 sorted strings that consist of vowels only are
     * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
     * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
     * <p>
     * Example 3:
     * Input: n = 33
     * Output: 66045
     * <p>
     * Constraints:
     * 1 <= n <= 50
     */
    public static int countVowelStrings(int n) {
        int[] counts = new int[5];
        Arrays.fill(counts, 1);
        for (int len = 0; len < n; len++) {
            for (int i = 1; i < 5; i++) {
                counts[i] += counts[i - 1];
            }
        }
        return counts[4];
    }

    public static int countVowelStrings01(int n) {
        // i 表示字符串的长度 j 表示元素的个数
        // dp[i][j] 表示长度为i，元素个数为j的字符串数量
        int[][] dp = new int[n + 1][5];
        Arrays.fill(dp[0], 1);
        for (int len = 1; len <= n; len++) {
            dp[len][0] = 1;
            for (int i = 1; i < 5; i++) {
                dp[len][i] = dp[len][i - 1] + dp[len - 1][i];
            }
        }
        return dp[n][4];
    }


    public static void main(String[] args) {
        System.out.println(countVowelStrings01(1) == 5);
        System.out.println(countVowelStrings01(2) == 15);
        System.out.println(countVowelStrings01(33) == 66045);
    }
}
