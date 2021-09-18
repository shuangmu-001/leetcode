package com.leetcode.dp.interval;

import com.Utils;

/**
 * @author wcl
 * @date 4:18 PM 2019/12/23
 * TODO {link "https://leetcode.com/problems/longest-palindromic-substring/"}
 */
public class LongestPalindromicSubstring {
    /**
     * Given a string s, find the longest palindromic substring in s.
     * You may assume that the maximum length of s is 1000.
     *
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     *
     * Input: "cbbd"
     * Output: "bb"
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String result = "";
        int max = 0;
        int length = s.length();
        for(int i = 0; i < length; i++) {
            for(int j = i + 1; j <= length; j++) {
                String test = s.substring(i, j);
                if(isPalindromic(test) && test.length() >max) {
                    result = test;
                    max = test.length();
                }
            }
        }
        return result;
    }

    public static String longestPalindrome01(String s) {
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
                // TODO 如果i到j不是回文，那么 i - 1 到 j + 1 也不是回文
                if(chars[i] == chars[j] && dp[i + 1][j - 1] == len - 2) {
                    dp[i][j] = len;
                }
                if(dp[i][j] > maxLen) {
                    start = i;
                    maxLen = dp[i][j];
                }
            }
            Utils.printTwoArrays(dp);
        }
        return s.substring(start, start + maxLen);
    }


    public static void main(String[] args) {
//        System.out.println((longestPalindrome01("babad")));
//        System.out.println(longestPalindrome01("babad"));
//        System.out.println(longestPalindrome01("cbbd"));
//        Map<Character, Integer> map =  new HashMap<>();
//        System.out.println(new String(new char[]{'a'}, 0, 1));
//        System.out.println(longestPalindrome01("aacdefcaa"));
        System.out.println(longestPalindrome01("aaaaaaaaaaaaaaabbbbbbbbbdddaeveaeveadbbbbbbbbbbbbbbbbbbbbc"));
    }

    /**
     * 判断字符串是否为回文串
     * @param str
     * @return
     */
    public static boolean isPalindromic(String str) {
        int length = str.length();
        for(int i = 0; i < length/2; i++ ){
            if(str.charAt(i) != str.charAt(length - i - 1 )) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求两个字符串最长公共子串 （不是回文串） aacdefcaa
     * @param s
     * @return
     */
    public static String longestPalindrome02(String s) {
        if(s.isEmpty()) {
            return "";
        }
        int maxLength = 0;
        int endIndex = 0;
        String reverse = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length ; j++) {
                if(s.charAt(i) == reverse.charAt(j)) {

                    if(i == 0 || j ==0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }

                }

                if(arr[i][j] > maxLength) {
                    if(isPalindromic(s.substring(i - arr[i][j] + 1, i + 1))) {
                        maxLength = arr[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return s.substring(endIndex - maxLength + 1, endIndex + 1);
    }

}
