package com.leetcode.stack;

import java.util.Stack;

/**
 * @author zms
 * @date 10:29 上午 2021/2/25
 * <a href="https://leetcode.com/problems/score-of-parentheses/">
 * Score of Parentheses</a>
 */
public class ScoreOfParentheses {
    /**
     * Given a balanced parentheses string S, compute the score of the string based on the following rule:
     * <p>
     * () has score 1
     * AB has score A + B, where A and B are balanced parentheses strings.
     * (A) has score 2 * A, where A is a balanced parentheses string.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: "()"
     * Output: 1
     * Example 2:
     * <p>
     * Input: "(())"
     * Output: 2
     * Example 3:
     * <p>
     * Input: "()()"
     * Output: 2
     * Example 4:
     * <p>
     * Input: "(()(()))"
     * Output: 6
     * <p>
     * <p>
     * Note:
     * <p>
     * S is a balanced parentheses string, containing only ( and ).
     * 2 <= S.length <= 50
     */
    // ((()(())))
    public static int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(-1);
            } else {
                int score = 0;
                while (stack.peek() != -1) {
                    score += stack.pop();
                }
                stack.pop();
                if (score == 0) {
                    stack.push(1);
                } else {
                    stack.push(score * 2);
                }
            }
        }
        int score = 0;
        while (!stack.isEmpty()) {
            score += stack.pop();
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("()") == 1);
        System.out.println(scoreOfParentheses("()()") == 2);
        System.out.println(scoreOfParentheses("()(())") == 3);
        System.out.println(scoreOfParentheses("(()(()))") == 6);
        System.out.println(scoreOfParentheses("(((())))") == 8);
        System.out.println(scoreOfParentheses("((((((())))()())))") == 80);

    }

}
