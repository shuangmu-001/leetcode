package com.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 10:37 上午 2020/9/8
 * <a href="https://leetcode.com/problems/word-pattern/">Word Pattern</a>
 */
public class WordPattern {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     * Here follow means a full match,
     * such that there is a bijection between a letter in pattern and a non-empty word in str.
     *
     * Example 1:
     * Input: pattern = "abba", str = "dog cat cat dog"
     * Output: true
     *
     * Example 2:
     * Input:pattern = "abba", str = "dog cat cat fish"
     * Output: false
     *
     * Example 3:
     * Input: pattern = "aaaa", str = "dog cat cat dog"
     * Output: false
     *
     * Example 4:
     * Input: pattern = "abba", str = "dog dog dog dog"
     * Output: false
     * Notes:
     * You may assume pattern contains only lowercase letters,
     * and str contains lowercase letters that may be separated by a single space.
     */
    public static boolean wordPattern(String pattern, String str) {
        Map<String, Integer> target = new HashMap<>();
        Map<Character, Integer> source = new HashMap<>();
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char c = pattern.charAt(i);

            if(target.containsKey(s) != source.containsKey(c)) {
                return false;
            }
            Integer sIndex = source.get(c);
            if(sIndex == null) {
                source.put(c, i);
                target.put(s, i);
            } else {
                Integer tIndex = target.get(s);
                if(!sIndex.equals(tIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Map<Character, Integer> source = new HashMap<>();
        Map<String, Integer> target = new HashMap<>();
        target.put("323",0);
        source.put('1',0 );
        System.out.println(target.containsKey("33") == source.containsKey('2'));
    }

}
