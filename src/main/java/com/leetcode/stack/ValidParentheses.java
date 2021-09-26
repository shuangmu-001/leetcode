package com.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zms
 * @date 9:43 PM 2020/2/17
 * {@link "https://leetcode.com/problems/valid-parentheses/"}
 * 1、后进先出 --> 栈
 * 2、判断一个数能否被2^n整除 --> a & (b - 1) (b = 2^n)
 */
public class ValidParentheses {
    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * <p>
     * Note that an empty string is also considered valid.
     * <p>
     * Example 1:
     * Input: "()"
     * Output: true
     * <p>
     * Example 2:
     * Input: "()[]{}"
     * Output: true
     * <p>
     * Example 3:
     * Input: "(]"
     * Output: false
     * <p>
     * Example 4:
     * Input: "([)]"
     * Output: false
     * <p>
     * Example 5:
     * Input: "{[]}"
     * Output: true
     */
    public static boolean isValid1(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Map<Character, Character> characters = new HashMap<>();
        characters.put('{', '}');
        characters.put('[', ']');
        characters.put('(', ')');
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }
        ListNode lastPre = new ListNode('0');

        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (lastPre.val == '0' && !characters.containsKey(currentChar)) {
                return false;
            }
            if (characters.containsKey(currentChar)) {
                ListNode listNode = new ListNode(currentChar);
                listNode.next = lastPre;
                lastPre = listNode;
            } else if (currentChar != characters.get(lastPre.val)) {
                return false;
            } else {
                lastPre = lastPre.next;
            }

        }

        return lastPre.val == '0';
    }

    public static class ListNode {
        char val;
        ListNode next;

        ListNode(char val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static boolean isValid2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int len = s.length();
        if ((len & 1) != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (stack.empty()) {
                return false;
            } else {
                Character pop = stack.pop();
                char target;
                if (pop == '(') {
                    target = (char) (pop + 1);
                } else {
                    target = (char) (pop + 2);
                }
                if (target != c) {
                    return false;
                }
            }

        }
        return stack.empty();
    }
    static Map<Character, Character> maps = new HashMap<>();
    static {
        maps.put('}', '{');
        maps.put(')', '(');
        maps.put(']', '[');
    }
    public static boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        int length = s.length();
        if((length & 1 ) != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(maps.containsKey(c)) {
                if(stack.isEmpty()) {
                    return false;
                }
                char target = maps.get(c);
                char source = stack.pop();
                if(target != source) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{}"));
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(!isValid("(]"));
        System.out.println(!isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("{[]()}"));
        System.out.println(!isValid("{{"));
        System.out.println('}' - '{');
        System.out.println(')' - '(');
        System.out.println(']' - '[');
    }

}
