package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

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


    public static void main(String[] args) {
//        assert "bab".equals(longestPalindrome02("babad")) ? false : true;
//        assert "aba".equals(longestPalindrome02("babad"));
//        assert "bb".equals(longestPalindrome02("cbbd"));
        Map<Character, Integer> map =  new HashMap<>();
        System.out.println(longestPalindrome02("aacdefcaa"));
        System.out.println(longestPalindrome("a"));
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
