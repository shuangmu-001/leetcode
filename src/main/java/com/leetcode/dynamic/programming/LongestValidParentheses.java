package com.leetcode.dynamic.programming;

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
        int maxResult = 0;
        int before = 0;
        int current = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                if(stack.isEmpty()) {
                    before += current;
                    current = 0;
                }
                stack.push('(');
            } else if(!stack.isEmpty()){
                current++;
                stack.pop();
            } else {
                current = before + current;
                maxResult = Math.max(current, maxResult);
                current = 0;
                before = 0;
            }
            if(i == s.length() - 1) {
                if(s.charAt(i) == '(') {
                    before += current;
                    maxResult = Math.max(before, maxResult);
                } else if(!stack.isEmpty()){
                    maxResult = Math.max(current, maxResult);
                } else {
                    current += before;
                    maxResult = Math.max(current, maxResult);
                }
            }
        }

        return maxResult << 1;
    }

    public static void main(String[] args) {
//        System.out.println(longestValidParentheses("(()") == 2);
//        System.out.println(longestValidParentheses(")()())") == 4);
//        System.out.println(longestValidParentheses(")()(()") == 2);
//        System.out.println(longestValidParentheses(")()(())") == 6);
//        System.out.println(longestValidParentheses(")()(()))()") == 6);
//        System.out.println(longestValidParentheses(")()(((())))(") == 10);
//        System.out.println(longestValidParentheses(")(((((()())()()))()(()))(") == 22);
        System.out.println(longestValidParentheses("(()(((()") == 2);
    }

}
