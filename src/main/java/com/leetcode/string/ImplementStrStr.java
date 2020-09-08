package com.leetcode.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author wcl
 * @date 2:14 PM 2020/2/20
 * {@link "https://leetcode.com/problems/implement-strstr/"}
 *
 * @see java.lang.String#indexOf(String)
 */
public class ImplementStrStr {
    /**
     * Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack,
     * or -1 if needle is not part of haystack.
     *
     * Example 1:
     *      Input: haystack = "hello", needle = "ll"
     *      Output: 2
     *
     * Example 2:
     *      Input: haystack = "aaaaa", needle = "bba"
     *      Output: -1
     *
     * Clarification:
     *      What should we return when needle is an empty string?
     *      This is a great question to ask during an interview.
     *      For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     *
     * Runtime: 3 ms, faster than 31.08% of Java online submissions for Implement strStr().
     * Memory Usage: 37.9 MB, less than 67.17% of Java online submissions for Implement strStr().
     */
    public static int strStr1(String haystack, String needle) {
        if(needle.isEmpty()) {
            return 0;
        }
        int index = -1;
        int sourceLen = haystack.length();
        int targetLen = needle.length();
        //needle 长度必须要小于 haystack
        if(sourceLen < targetLen) {
            return index;
        }
        for (int i = 0, j = 0; i < sourceLen && j < targetLen; i++) {
            char source = haystack.charAt(i);
            char target = needle.charAt(j);
            if(source == target) {
                index = index == -1 ? i : index;
                j++;
            } else if(index != -1) {
                index = -1;
                i -= j;
                j = 0;
            }

            if(sourceLen - i <= targetLen - j) {
                return -1;
            }
        }
        return index;
    }

    /**
     * Runtime: 1 ms, faster than 64.29% of Java online submissions for Implement strStr().
     * Memory Usage: 38.4 MB, less than 55.72% of Java online submissions for Implement strStr().
     */
    public static int strStr(String haystack, String needle) {
        if(needle.isEmpty()) {
            return 0;
        }
        int index = -1;
        int sourceLen = haystack.length();
        int targetLen = needle.length();
        //needle 长度必须要小于 haystack
        if(sourceLen < targetLen) {
            return index;
        }
        char first = needle.charAt(0);
        int max = sourceLen - targetLen;
        for (int i = 0; i <= max; i++) {
            if(haystack.charAt(i) != first) {
                while (++i <= max && haystack.charAt(i) != first) {
                    ;
                }
            }
            if(i <= max) {
                int j = i + 1;
                int end = j + targetLen - 1;
                for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++){
                    ;
                }
                if(j == end) {
                    return i;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hel0llo", "ll"));
        System.out.println(strStr("mississippi","issip"));
        System.out.println(strStr("mississippi","issipi"));
        System.out.println(strStr("mississippi","sippia"));
        for (int i = 1; i < 30; i++) {
            int pow = (int) Math.pow(3, i);
            System.out.println(pow);
            if(pow == 2147483647) {
                System.out.println(i);
            }
            System.out.println(Integer.toBinaryString(pow));
            System.out.println(Integer.toBinaryString(pow >> 4));
        }
        System.out.println(Math.pow(2, 4) + Math.pow(2, 3) + Math.pow(2,1) + Math.pow(2, 0));
    }
}
