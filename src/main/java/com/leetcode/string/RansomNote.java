package com.leetcode.string;

/**
 * @author zms
 * @date 3:37 PM 2020/5/3
 * <a href="https://leetcode.com/problems/ransom-note/">
 *     Ransom Note</a>
 */
public class RansomNote {
    /**
     * Given an arbitrary ransom note string and another string containing letters from all the magazines,
     * write a function that will return true if the ransom note can be constructed from the magazines ;
     * otherwise, it will return false.
     * Each letter in the magazine string can only be used once in your ransom note.
     *
     * Note:
     * You may assume that both strings contain only lowercase letters.
     *
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        int length = magazine.length();
        for (int i = 0; i < length; i++) {
            char c = magazine.charAt(i);
            int index = c - 'a';
            count[index] += 1;
        }
        length = magazine.length();
        for (int i = 0; i < length; i++) {
            char c = ransomNote.charAt(i);
            int index = c - 'a';
            if(count[index] <= 0) {
                return false;
            }
            count[index] -= 1;
        }
        return true;
    }
}
