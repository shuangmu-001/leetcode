package com.leetcode.twopoints;

import com.Utils;

/**
 * @author wcl
 * @date 2:38 下午 2020/6/5
 * <a href='https://leetcode.com/problems/reverse-string/'>
 *     Reverse String</a>
 */
public class ReverseString {
    /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     *
     *
     * Example 1:
     *
     * Input: ['h','e','l','l','o']
     * Output: ['o','l','l','e','h']
     * Example 2:
     *
     * Input: ['H','a','n','n','a','h']
     * Output: ['h','a','n','n','a','H']
     */
    public static void reverseString(char[] s) {
        if(s == null || s.length < 2) {
            return;
        }
        int length = s.length;
        int left = 0;
        int right = length - 1;
        while(left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
        Utils.printArrays(s);
    }

    public static void main(String[] args) {
        reverseString(new char[]{'H','a','n','n','a','h'});
    }

}
