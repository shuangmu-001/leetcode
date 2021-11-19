package com.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/valid-anagram/">Valid Anagram</a>
 *
 * @author zms
 * @date 9:53 下午 2021/2/11
 */
public class ValidAnagram {
    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     * <p>
     * Example 1:
     * <p>
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     * <p>
     * Input: s = "rat", t = "car"
     * Output: false
     * Note:
     * You may assume the string contains only lowercase alphabets.
     * <p>
     * Follow up:
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     */
    public boolean isAnagram01(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            if (count - 1 == 0) {
                map.remove(c);
            } else {
                map.put(c, count - 1);
            }
        }
        return map.isEmpty();
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            int index = c - 'a';
            nums[index]--;
            if (nums[index] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
