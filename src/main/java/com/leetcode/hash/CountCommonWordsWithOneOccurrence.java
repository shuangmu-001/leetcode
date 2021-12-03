package com.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/count-common-words-with-one-occurrence/">Count Common Words With One Occurrence</a>
 *
 * @author zms
 * @date 9:54 PM 2021/12/2
 */
public class CountCommonWordsWithOneOccurrence {
    /**
     * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.
     * <p>
     * Example 1:
     * Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
     * Output: 2
     * Explanation:
     * - "leetcode" appears exactly once in each of the two arrays. We count this string.
     * - "amazing" appears exactly once in each of the two arrays. We count this string.
     * - "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
     * - "as" appears once in words1, but does not appear in words2. We do not count this string.
     * Thus, there are 2 strings that appear exactly once in each of the two arrays.
     * <p>
     * Example 2:
     * Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
     * Output: 0
     * Explanation: There are no strings that appear in each of the two arrays.
     * <p>
     * Example 3:
     * Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
     * Output: 1
     * Explanation: The only string that appears exactly once in each of the two arrays is "ab".
     * <p>
     * Constraints:
     * 1 <= words1.length, words2.length <= 1000
     * 1 <= words1[i].length, words2[j].length <= 30
     * words1[i] and words2[j] consists only of lowercase English letters.
     */
    public static int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map01 = new HashMap<>();
        Map<String, Integer> map02 = new HashMap<>();
        for (String s : words1) {
            map01.merge(s, 1, Integer::sum);
        }
        for (String s : words2) {
            if (map01.containsKey(s) && map01.get(s) == 1) {
                map02.merge(s, 1, Integer::sum);
            }
        }
        return (int) map02.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .count();
    }
}
