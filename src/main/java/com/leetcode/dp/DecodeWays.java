package com.leetcode.dp;

/**
 * @author wcl
 * @date 2:43 PM 2020/4/1
 * TODO <a href="https://leetcode.com/problems/decode-ways/">
 *     Decode Ways</a>
 */
public class DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *      Input: "12"
     *      Output: 2
     *      Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     *
     * Example 2:
     *      Input: "226"
     *      Output: 3
     *      Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     */
    public int numDecodings(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        int[] dp = new int[s.length()];
        return 0;
    }

    //120819102891829
}
