package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 7:48 PM 2020/2/21
 * {@link "https://leetcode.com/problems/substring-with-concatenation-of-all-words/"}
 * TODO 实现排列组合
 * @see GenerateParentheses
 * @see com.leetcode.arrays.Permutations
 */
public class SubstringWithConcatenationOfAllWords {
    /**
     * You are given a string, s, and a list of words, words, that are all of the same length.
     * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
     *
     * Example 1:
     *      Input: s = "barfoothefoobarman", words = ["foo","bar"]
     *      Output: [0,9]
     *      Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
     *                   The output order does not matter, returning [9,0] is fine too.
     *
     * Example 2:
     *      Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     *      Output: []
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.isEmpty() || words == null) {
            return result;
        }
        int sourceLen = s.length();
        int targetLen = words.length;
        int wordLen = words[0].length();
        if(wordLen * targetLen > sourceLen) {
            return  result;
        }




        return result;
    }

//    public static void

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
    }

}
