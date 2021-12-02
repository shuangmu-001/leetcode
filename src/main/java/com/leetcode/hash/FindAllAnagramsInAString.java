package com.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">Find All Anagrams In A String</a>
 *
 * @author zms
 * @date 6:51 PM 2020/5/17
 */
public class FindAllAnagramsInAString {
    /**
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     * <p>
     * The order of output does not matter.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * s: "cbaebabacd" p: "abc"
     * <p>
     * Output:
     * [0, 6]
     * <p>
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     * Example 2:
     * <p>
     * Input:
     * s: "abab" p: "ab"
     * <p>
     * Output:
     * [0, 1, 2]
     * <p>
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        if (m < n) {
            return new ArrayList<>();
        }
        int[] sfreq = new int[26];
        int[] qfreq = new int[26];
        for (int i = 0; i < n; i++) {
            qfreq[p.charAt(i) - 'a']++;
            sfreq[s.charAt(i) - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = n; i < m; i++) {
            if (Arrays.equals(sfreq, qfreq)) {
                res.add(i - n);
            }
            sfreq[s.charAt(i) - 'a']++;
            sfreq[s.charAt(i - n) - 'a']--;
        }
        if (Arrays.equals(sfreq, qfreq)) {
            res.add(m - n);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
}
