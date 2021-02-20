package com.leetcode.stack;

import java.util.Stack;

/**
 * @author zms
 * @date 2:09 下午 2021/2/20
 * <a href="https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/">
 * Minimum Remove to Make Valid Parentheses</a>
 */
public class MinimumRemoveToMakeValidParentheses {
    /**
     * Given a string s of '(' , ')' and lowercase English characters.
     * <p>
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
     * <p>
     * Formally, a parentheses string is valid if and only if:
     * <p>
     * It is the empty string, contains only lowercase characters, or
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     * <p>
     * Example 1:
     * Input: s = "lee(t(c)o)de)"
     * Output: "lee(t(c)o)de"
     * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
     * <p>
     * <p>
     * Example 2:
     * Input: s = "a)b(c)d"
     * Output: "ab(c)d"
     * <p>
     * Example 3:
     * Input: s = "))(("
     * Output: ""
     * Explanation: An empty string is also valid.
     * <p>
     * Example 4:
     * Input: s = "(a(b(c)d)"
     * Output: "a(b(c)d)"
     * <p>
     * Constraints:
     * 1 <= s.length <= 10^5
     * s[i] is one of  '(' , ')' and lowercase English letters.
     */
    public static String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        Stack<Integer> index = new Stack<>();
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(chars[i]);
                newChars[i] = chars[i];
                index.push(i);
            } else if(chars[i] == ')') {
                if(stack.isEmpty()) {
                    newChars[i] = ' ';
                } else {
                    newChars[i] = chars[i];
                    stack.pop();
                    index.pop();
                }
            } else {
                newChars[i] = chars[i];
            }
        }
        while(!index.isEmpty()) {
            Integer pop = index.pop();
            newChars[pop] = ' ';
        }

        for (int i = 0; i < chars.length; i++) {
            if(newChars[i] != ' ') {
                sb.append(newChars[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de))(("));
    }
}
