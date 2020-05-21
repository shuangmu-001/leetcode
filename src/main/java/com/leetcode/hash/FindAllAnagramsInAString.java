package com.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wcl
 * @date 6:51 PM 2020/5/17
 * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">
 * Find All Anagrams In A String</a>
 */
public class FindAllAnagramsInAString {
    /**
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     *
     * Example 1:
     *
     * Input:
     * s: "cbaebabacd" p: "abc"
     *
     * Output:
     * [0, 6]
     *
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     * Example 2:
     *
     * Input:
     * s: "abab" p: "ab"
     *
     * Output:
     * [0, 1, 2]
     *
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     */
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        if(m < n) {
            return new ArrayList<>();
        }
        int[] sfreq = new int[26];
        int[] qfreq = new int[26];
        for (int i = 0; i < n; i++) {
            qfreq[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            sfreq[s.charAt(i) - 'a']++;
        }
        List<Integer> res= new ArrayList<>();
        for (int i = n; i < m; i++) {
            if(Arrays.equals(sfreq, qfreq)) {
                res.add(i - n);
            }
            sfreq[s.charAt(i) - 'a']++;
            sfreq[s.charAt(i - n) - 'a']++;
        }
        if(Arrays.equals(sfreq, qfreq)) {
            res.add(m - n);
        }
        return res;
    }
}
