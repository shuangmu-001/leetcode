package com.leetcode.dp.fibonacci;

import java.util.Arrays;

/**
 * @author zms
 * @date 2:43 PM 2020/4/1
 * <a href="https://leetcode.com/problems/decode-ways/">
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
    public static int numDecodings(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }
        int first = 1;
        int second = 1;
        for (int i = 1; i < s.length(); i++) {
            char before = s.charAt(i - 1);
            char cur = s.charAt(i);
            if(cur == '0') {
                if(before == '1' || before == '2') {
                    second = first;
                } else {
                    return 0;
                }
            } else {
                int temp = second;
                if(before == '1' || (before == '2' && cur <= '6')) {
                    temp += first;
                }
                first = second;
                second = temp;
            }
        }
        return second;
    }

    public static int numDecodings1(String s) {
        if(s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();
        if(len == 1) {
            return 1;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            char before = s.charAt(i - 2);
            char cur = s.charAt(i - 1);
            if(cur == '0') {
                if(before == '1' || before == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            } else {
                dp[i] = dp[i - 1];
                if(before == '1' || (before == '2' && cur <= '6')) {
                    dp[i] += dp[i - 2];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("226113") == 9);
        System.out.println(numDecodings("132142321") == 24);
        System.out.println(numDecodings("120819102891829") == 4);
        System.out.println(numDecodings("12") == 2);
        System.out.println(numDecodings("226") == 3);
        System.out.println(numDecodings1("1201") == 1);
    }

    //120819102891829
}
