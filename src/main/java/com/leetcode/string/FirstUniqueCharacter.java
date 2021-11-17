package com.leetcode.string;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/first-unique-character-in-a-string/">First Unique Character in a String</a>
 *
 * @author zms
 * @date 7:54 PM 2020/5/5
 */
public class FirstUniqueCharacter {
    /**
     * Given a string, find the first non-repeating character in it and return it's index.
     * If it doesn't exist, return -1.
     * <p>
     * Examples:
     * s = "leetcode"
     * return 0.
     * <p>
     * s = "loveleetcode",
     * return 2.
     * Note: You may assume the string contain only lowercase letters.
     */
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        int[] count = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            int index = c - 'a';
            count[index]++;
        }
        for (int i = 0; i < cs.length; i++) {
            int index = cs[i] - 'a';
            if (count[index] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        String[] s = " abc       cd        ".split(" ");
        for (String str : s) {

            System.out.println(str.isEmpty());
            System.out.println(str);
        }
        System.out.println(Arrays.toString(s));
    }
}
