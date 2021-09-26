package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zms
 * @date 3:11 PM 2020/4/16
 * TODO <a href="https://leetcode.com/problems/valid-parenthesis-string/">
 *     Valid Parenthesis String</a>
 */
public class ValidParenthesisString {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*',
     * write a function to check whether this string is valid. We define the validity of a string by these rules:
     * 1、Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * 2、Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * 3、Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * 4、'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * 5、An empty string is also valid.
     *
     * Example 1:
     *      Input: "()"
     *      Output: True
     *
     * Example 2:
     *      Input: "(*)"
     *      Output: True
     *
     * Example 3:
     *      Input: "(*))"
     *      Output: True
     *
     * Note:The string size will be in the range [1, 100].
     */
    public static boolean checkValidString(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        int len = s.length();
        if(s.charAt(0) == ')' || s.charAt(len - 1) == '(') {
            return false;
        }
        int l = 0, r = 0, a = 0;
        int ll = 0, rr = 0, aa = 0;
        for (int i = 0; i < len; i++) {
            char before = s.charAt(i);
            if(before == '*') {
                a += 1;
            } else if(before == '(') {
                l += 1;
            } else if(before == ')') {
                r += 1;
            }
            char after = s.charAt(len - 1 - i);
            if(after == '*') {
                aa += 1;
            } else if(after == '(') {
                ll += 1;
            } else if(after == ')') {
                rr += 1;
            }
            if(r > l + a) {
                return false;
            }
            if(ll > rr + aa) {
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
//        System.out.println(checkValidString("*"));
//        System.out.println(checkValidString("(*)"));
//        System.out.println(checkValidString("(((*))"));
//        System.out.println(checkValidString("()*)"));
//        System.out.println(checkValidString("(*()))"));
//        System.out.println(!checkValidString("*()))"));
//        System.out.println(!checkValidString("*()(()"));
//        System.out.println(!checkValidString("*((())"));
        System.out.println(!checkValidString(
                "(((()*()()()))()"
                + "((()()(*()())))"
                + "(((*)()"
        ));
    }
}
