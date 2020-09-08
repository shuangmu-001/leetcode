package com.leetcode.string;

import java.util.Arrays;

/**
 * @author wcl
 * @date 11:16 上午 2020/9/4
 * <a href="https://leetcode.com/problems/repeated-substring-pattern/">
 * Repeated Substring Pattern</a>
 */
public class RepeatedSubstringPattern {
    /**
     * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
     * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
     * <p>
     * Example 1:
     * <p>
     * Input: "abab"
     * Output: True
     * Explanation: It's the substring "ab" twice.
     * Example 2:
     * <p>
     * Input: "aba"
     * Output: False
     * Example 3:
     * <p>
     * Input: "abcabcabcabc"
     * Output: True
     * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
     */
    public static boolean repeatedSubstringPattern1(String s) {
        int length = s.length();
        if(length <= 1) {
            return false;
        }
        for (int i = 0; i <= length / 2; i++) {
            if (length % (i + 1) != 0) {
                continue;
            }
            boolean result = repeatedSubstringPattern(s, i + 1);
            if (result) {
                return true;
            }
        }
        return false;
    }

    public static boolean repeatedSubstringPattern(String s, int length) {
        int index = 0;
        if(s.length() == length) {
            return false;
        }
        for (int i = index + length; i < s.length(); i++) {
            char a = s.charAt(index);
            char b = s.charAt(i);
            if (a != b) {
                return false;
            }
            index++;
            if (index == length) {
                index = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(repeatedSubstringPattern("abab"));
//        System.out.println(!repeatedSubstringPattern("aba"));
//        System.out.println(repeatedSubstringPattern("abcabcabcabc"));
//        System.out.println(repeatedSubstringPattern("abcabcabc"));
//        System.out.println(repeatedSubstringPattern("abcabcabcabcabc"));
//        System.out.println(!repeatedSubstringPattern("abcabcaabcabc"));
//        System.out.println(repeatedSubstringPattern("abcabcaabcabca"));
//        System.out.println(!repeatedSubstringPattern("a"));
//        System.out.println(!repeatedSubstringPattern("ab"));

    }

    public static boolean repeatedSubstringPattern(String s) {
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int l = s.length();
        for (int i : primes) {
            if (i > l) {
                break;
            }
            if (l % i == 0) {
                boolean valid = true;
                int j = l / i;
                for (int n = l; n > j; n -= j) {
                    if (!s.substring(n - j, n).equals(s.substring(n - 2 * j, n - j))) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean repeatedSubstringPattern2(String str) {
        String s = str + str;
        return s.substring(1, s.length() - 1).contains(str);
    }


}
