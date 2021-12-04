package com.leetcode.string;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/repeated-dna-sequences/">Repeated DNA Sequences</a>
 *
 * @author zms
 * @date 1:57 PM 2021/12/4
 */
public class RepeatedDNASequences {
    /**
     * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
     * For example, "ACGAATTCCG" is a DNA sequence.
     * When studying DNA, it is useful to identify repeated sequences within the DNA.
     * <p>
     * Given a string s that represents a DNA sequence,
     * return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
     * You may return the answer in any order.
     * <p>
     * Example 1:
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC","CCCCCAAAAA"]
     * <p>
     * Example 2:
     * Input: s = "AAAAAAAAAAAAA"
     * Output: ["AAAAAAAAAA"]
     * <p>
     * Constraints:
     * 1 <= s.length <= 10^5
     * s[i] is either 'A', 'C', 'G', or 'T'.
     */
    public static final int MAX = 0x000fffff;

    public static List<String> findRepeatedDnaSequences01(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        int target = 0;
        for (int i = 0; i < n; i++) {
            target <<= 2;
            int a = charToInt(s.charAt(i));
            target = (target | a) & MAX;
            if (i >= 9) {
                if (map.containsKey(target) && map.get(target) == 1) {
                    ans.add(s.substring(i - 9, i + 1));
                }
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }
        return ans;
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        int[] count = new int[MAX];
        int target = 0;
        for (int i = 0; i < n; i++) {
            target <<= 2;
            int a = charToInt(s.charAt(i));
            target = (target | a) & MAX;
            if (i >= 9) {
                if (count[target] == 1) {
                    ans.add(s.substring(i - 9, i + 1));
                }
               count[target]++;
            }
        }
        return ans;
    }

    public static int charToInt(char c) {
        switch (c) {
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
                return 4;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("CCCCCAAAAA"));
        System.out.println(findRepeatedDnaSequences("CCCCCAAAAACCCCCAAAAAGGGTTT"));
    }
}
