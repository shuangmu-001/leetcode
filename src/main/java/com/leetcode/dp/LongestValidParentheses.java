package com.leetcode.dp;

import java.util.Stack;

/**
 * @author wcl
 * @date 3:48 PM 2020/2/24
 * TODO {@link "https://leetcode.com/problems/longest-valid-parentheses/"}
 */
public class LongestValidParentheses {
    /**
     * Given a string containing just the characters '(' and ')',
     * find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     *      Input: "(()"
     *      Output: 2
     *      Explanation: The longest valid parentheses substring is "()"
     *
     * Example 2:
     *      Input: ")()())"
     *      Output: 4
     *      Explanation: The longest valid parentheses substring is "()()"
     */
    // 考虑所有情况 考虑细节
    // 什么情况下把所有的细节都包括呢
    public static int longestValidParentheses(String s) {
        if(s == null || s.length() < 1) {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '(' ? 1 : 0;
        int len = 0;
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                dp[i] = dp[i - 1] + 1;
            } else if(dp[i - 1] > 0) {
                len += 2;
                dp[i] = dp[i - 1] - 1;
            } else {
                result = Math.max(len, result);
                len = 0;
            }
        }
        result = Math.max(len, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()") == 2);
        System.out.println(longestValidParentheses(")()())") == 4);
        System.out.println(longestValidParentheses(")()(()") == 2);
        System.out.println(longestValidParentheses(")()(())") == 6);
        System.out.println(longestValidParentheses(")()(()))()") == 6);
        System.out.println(longestValidParentheses(")()(((())))(") == 10);
        System.out.println(longestValidParentheses(")(((((()())()()))()(()))(") == 22);
        System.out.println(longestValidParentheses("(()(((()") == 2);
    }

}
