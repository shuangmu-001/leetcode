package com.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * @date 9:53 下午 2021/2/11
 * <a href="https://leetcode.com/problems/valid-anagram/">
 *     Valid Anagram</a>
 */
public class ValidAnagram {
    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     * Output: false
     * Note:
     * You may assume the string contains only lowercase alphabets.
     *
     * Follow up:
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        for(char c : t.toCharArray()) {
            if(!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            if(count - 1 == 0) {
                map.remove(c);
            } else {
                map.put(c, count - 1);
            }
        }
        return map.isEmpty();
    }
}
