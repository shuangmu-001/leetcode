package com.leetcode.dp.linear;

import java.util.List;

/**
 * @author zms
 * @date 2:13 下午 2021/2/23
 * <a href="https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/">
 * Longest Word in Dictionary through Deleting</a>
 * TODO 子序列的问题
 */
public class LongestWordInDictionaryThroughDeleting {
    /**
     * Given a string and a string dictionary, find the longest string in the dictionary
     * that can be formed by deleting some characters of the given string.
     * If there are more than one possible results,
     * return the longest word with the smallest lexicographical order.
     * If there is no possible result, return the empty string.
     * Example 1:
     * Input:
     * s = "abpcplea", d = ["ale","apple","monkey","plea"]
     * Output:
     * "apple"
     * <p>
     * Example 2:
     * Input:
     * s = "abpcplea", d = ["a","b","c"]
     * Output:
     * "a"
     * Note:
     * All the strings in the input will only contain lower-case letters.
     * The size of the dictionary won't exceed 1,000.
     * The length of all the strings in the input won't exceed 1,000.
     */
    public String findLongestWord(String s, List<String> d) {
        d.sort((s1, s2) ->
                s1.length() != s2.length() ? (s2.length() - s1.length()) : s1.compareTo(s2)
        );
        for (String str : d) {
            if (isSubsequence(s, str)) {
                return str;
            }
        }
        return "";
    }

    public boolean isSubsequence(String source, String target) {
        int j = 0;
        for (int i = 0; i < source.length() && j < target.length(); i++) {
            if (source.charAt(i) == target.charAt(j)) {
                j++;
            }
        }
        return target.length() == j;
    }
}
