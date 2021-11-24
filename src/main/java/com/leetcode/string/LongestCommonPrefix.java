package com.leetcode.string;

/**
 * <a href="https://leetcode.com/problems/longest-common-prefix/">Longest Common Prefix</a>
 *
 * @author zms
 * @date 4:35 下午 2021/11/24
 */
public class LongestCommonPrefix {
    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * <p>
     * If there is no common prefix, return an empty string "".
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     * <p>
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] consists of only lower-case English letters.
     */
    public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        int count = res.length();
        for (int i = 1; i < strs.length && count != 0; i++) {
            int r = 0;
            int min = Math.min(count, strs[i].length());
            while (r < min && res.charAt(r) == strs[i].charAt(r)) {
                r++;
            }
            count = Math.min(r, count);
        }
        return count == 0 ? "" : res.substring(0, count);
    }
}
