package com.leetcode.dp.string;

import com.leetcode.Utils;

/**
 * {@link "https://leetcode.com/problems/palindromic-substrings/"}
 *
 * @author wcl
 * @date 5:09 下午 2021/5/7
 */
public class PalindromicSubstrings {

    /**
     * Given a string, your task is to count how many palindromic substrings in this string.
     * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
     * <p>
     * Example 1:
     * Input: "abc"
     * Output: 3
     * Explanation: Three palindromic strings: "a", "b", "c".
     * <p>
     * Example 2:
     * Input: "aaa"
     * Output: 6
     * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     * <p>
     * Note:
     * The input string length won't exceed 1000.
     */
    public static int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                boolean b = s.charAt(i) == s.charAt(j);
                if (len == 1 || (b && len == 2)) {
                    dp[i][j] = true;
                    res++;
                    continue;
                }
                if (b) {
                    res += (dp[i + 1][j - 1] ? 1 : 0);
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }
        Utils.printTwoArrays(dp);
        return res;
    }
    // TODO 从中心向外扩散，如果不是就不用扩散了，奇数和偶数，每个点都可以是中心

    public static void main(String[] args) {
        System.out.println(3 == countSubstrings("abc"));
        System.out.println(6 == countSubstrings("aaa"));
        System.out.println(10 == countSubstrings("aaaa"));
        System.out.println(16 == countSubstrings("abcdefedcba"));
    }
}
