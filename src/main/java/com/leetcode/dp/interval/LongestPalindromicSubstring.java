package com.leetcode.dp.interval;

import com.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO {link "https://leetcode.com/problems/longest-palindromic-substring/"}
 *
 * @author zms
 * @date 4:18 PM 2019/12/23
 */
public class LongestPalindromicSubstring {
    /**
     * Given a string s, find the longest palindromic substring in s.
     * You may assume that the maximum length of s is 1000.
     * <p>
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * <p>
     * Input: "cbbd"
     * Output: "bb"
     */
    public static String longestPalindrome(String s) {
        String result = "";
        int max = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    result = test;
                    max = test.length();
                }
            }
        }
        return result;
    }

    // 暴力解
    public static String longestPalindrome01(String s) {
        int length = s.length();
        char[] str = s.toCharArray();
        Info info = process01(str, 0, length - 1);
        return new String(str, info.left, info.length);
    }

    private static class Info {
        int length;
        int left;
        int right;

        public Info(int length, int left, int right) {
            this.length = length;
            this.left = left;
            this.right = right;
        }
    }

    private static Info process01(char[] str, int left, int right) {
        if (left > right) {
            return new Info(0, left, right);
        }
        if (left == right) {
            return new Info(1, left, right);
        }
        if (str[left] == str[right]) {
            Info ans = process01(str, left + 1, right - 1);
            if (ans.length == right - 1 - left) {
                return new Info(ans.length + 2, left, right);
            }
        }
        Info l = process01(str, left, right - 1);
        Info r = process01(str, left + 1, right);

        return l.length >= r.length ? l : r;
    }

    // 记忆化搜索
    public static String longestPalindrome02(String s) {
        int length = s.length();
        char[] str = s.toCharArray();
        Info[][] dp = new Info[length][length];
        Info info = process02(str, 0, length - 1, dp);
        return new String(str, info.left, info.length);
    }

    private static Info process02(char[] str, int left, int right, Info[][] dp) {
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        if (left > right) {
            return new Info(0, left, right);
        }
        if (left == right) {
            return new Info(1, left, right);
        }
        if (str[left] == str[right]) {
            Info ans = process02(str, left + 1, right - 1, dp);
            if (ans.length == right - 1 - left) {
                dp[left][right] = new Info(ans.length + 2, left, right);
                return dp[left][right];
            }
        }
        Info l = process02(str, left, right - 1, dp);
        Info r = process02(str, left + 1, right, dp);
        dp[left][right] = l.length >= r.length ? l : r;
        return dp[left][right];
    }

    // 动态规划
    // dp[i][j] i：表示字符串长度，j：表示当前长度以j结尾 当前区间的字符串的最长回文子串
    // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
    public static String longestPalindrome03(String s) {
        int length = s.length();
        char[] str = s.toCharArray();
        // 最大长度改变，则结尾改变
        int end = 0;
        // 最大长度
        int ans = 1;
        int[][] dp = new int[length + 1][length + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = i; j <= length; j++) {
                if (i == 1) {
                    dp[i][j] = 1;
                } else if (str[j - i] == str[j - 1]) {
                    if (dp[i - 2][j - 1] == i - 2) {
                        dp[i][j] = dp[i - 2][j - 1] + 2;
                        if (ans < dp[i][j]) {
                            ans = dp[i][j];
                            end = j - 1;
                        }
                    }
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        return new String(str, end - ans + 1, ans);
    }

    public static String longestPalindrome05(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n][n];
        int start = 0;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                // 如果i到j不是回文，那么 i - 1 到 j + 1 也不是回文
                if (chars[i] == chars[j] && dp[i + 1][j - 1] == len - 2) {
                    dp[i][j] = len;
                }
                if (dp[i][j] > maxLen) {
                    start = i;
                    maxLen = dp[i][j];
                }
            }
            Utils.printTwoArrays(dp);
        }
        return s.substring(start, start + maxLen);
    }

    public static String longestPalindrome06(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] str = s.toCharArray();
        int start = 0, end = 0, length = s.length(), ans = 0;
        for (int mid = 0; mid < length; mid++) {
            // 奇数 以mid为中心向外扩
            int odd = longestPalindrome(str, mid, mid);
            // 偶数 以mid 和 mid + 1为中心
            int even = longestPalindrome(str, mid, mid + 1);
            int max = Math.max(odd, even);
            if (ans < max) {
                // 更具mid计算start 和end 位置
                start = mid - ((max - 1) >> 1);
                end = mid + (max >> 1);
                ans = max;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int longestPalindrome(char[] str, int l, int r) {
        while (l >= 0 && r < str.length && str[l] == str[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }


    public static void main(String[] args) {
        System.out.println((longestPalindrome06("babad")));
        System.out.println(longestPalindrome06("babad"));
        System.out.println(longestPalindrome06("cbbd"));
        Map<Character, Integer> map = new HashMap<>();
        System.out.println(new String(new char[]{'a'}, 0, 1));
        System.out.println(longestPalindrome06("aacdefcaa"));
        System.out.println(longestPalindrome06("aaaaaaaaaaaaaaabbbbbbbbbdddaeveaeveadbbbbbbbbbbbbbbbbbbbbc"));
    }

    /**
     * 判断字符串是否为回文串
     */
    public static boolean isPalindromic(String str) {
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求两个字符串最长公共子串 （不是回文串） aacdefcaa
     */
    public static String longestPalindrome04(String s) {
        if (s.isEmpty()) {
            return "";
        }
        int maxLength = 0;
        int endIndex = 0;
        String reverse = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (s.charAt(i) == reverse.charAt(j)) {

                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }

                }

                if (arr[i][j] > maxLength) {
                    if (isPalindromic(s.substring(i - arr[i][j] + 1, i + 1))) {
                        maxLength = arr[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return s.substring(endIndex - maxLength + 1, endIndex + 1);
    }

}
