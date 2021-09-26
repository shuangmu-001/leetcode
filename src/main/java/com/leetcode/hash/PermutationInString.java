package com.leetcode.hash;

import java.util.Arrays;

/**
 * @author zms
 * @date 3:27 PM 2020/5/18
 * <a href="https://leetcode.com/problems/permutation-in-string/">
 * Permutation In String</a>
 * @see FindAllAnagramsInAString
 */
public class PermutationInString {
    /**
     * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
     * In other words, one of the first string's permutations is the substring of the second string.
     *
     * Example 1:
     *
     * Input: s1 = "ab" s2 = "eidbaooo"
     * Output: True
     * Explanation: s2 contains one permutation of s1 ("ba").
     * Example 2:
     *
     * Input:s1= "ab" s2 = "eidboaoo"
     * Output: False
     *
     *
     * Note:
     *
     * The input strings only contain lower case letters.
     * The length of both given strings is in range [1, 10,000].
     */
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if(m > n) {
            return false;
        }
        int[] s1freq = new int[26];
        for (int i = 0; i < m; i++) {
            s1freq[s1.charAt(i) - 'a']++;
        }
        int[] s2freq = new int[26];
        for (int i = 0; i < m; i++) {
            s2freq[s2.charAt(i) - 'a']++;
        }
        for (int i = m; i < n; i++) {
            if(Arrays.equals(s1freq, s2freq)) {
                return true;
            }
            s2freq[s2.charAt(i) - 'a']++;
            s2freq[s2.charAt(i - m) - 'a']--;
        }
        return Arrays.equals(s1freq, s2freq);
    }
}
