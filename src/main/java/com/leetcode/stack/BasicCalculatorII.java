package com.leetcode.stack;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/basic-calculator-ii/">Basic Calculator II</a>
 *
 * @author zms
 * @date 6:46 下午 2021/9/26
 */
public class BasicCalculatorII {

    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 整数除法仅保留整数部分。
     * <p>
     * 示例 1：
     * 输入：s = "3+2*2"
     * 输出：7
     * <p>
     * 示例 2：
     * 输入：s = " 3/2 "
     * 输出：1
     * <p>
     * 示例 3：
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     * <p>
     * 提示：
     * 1 <= s.length <= 3 * 10^5
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * s 表示一个 有效表达式
     * 表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
     * 题目数据保证答案是一个 32-bit 整数
     */
    public static int calculate01(String s) {
        LinkedList<Object> linked = new LinkedList<>();
        int cur = 0;
        char op;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                cur = cur * 10 + (c - '0');
            } else {
                priorityCalculator(linked, cur);
                linked.addLast(c);
                cur = 0;
            }
        }
        priorityCalculator(linked, cur);
        cur = (int) linked.pop();
        while (!linked.isEmpty()) {
            op = (char) linked.pop();
            int a = (int) linked.pop();
            cur = calculator(cur, a, op);
        }
        return cur;
    }

    public static void priorityCalculator(LinkedList<Object> linked, int cur) {
        if (!linked.isEmpty() && isPriority((char) linked.peekLast())) {
            char op = (char) linked.removeLast();
            int a = (int) linked.removeLast();
            cur = calculator(a, cur, op);
        }
        linked.addLast(cur);
    }

    public static boolean isPriority(char op) {
        return op == '*' || op == '/';
    }

    public static int calculator(int left, int right, char op) {
        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            default:
                return left / right;
        }
    }

    public static int calculate(String s) {
        int ans = 0, left = 0, right = 0;
        int length = s.length();
        char op = '+';
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            // 统计数字大小
            if (Character.isDigit(c)) {
                right = right * 10 + (c - '0');
            }
            // 符号或者最后一位
            if (!Character.isDigit(c) &&
                    !Character.isWhitespace(c) ||
                    i == length - 1) {
                if (!isPriority(op)) {
                    ans += left;
                    left = 0;
                }
                left = calculator(left, right, op);
                right = 0;
                op = c;
            }
        }

        return ans + left;
    }

    public static void main(String[] args) {
        System.out.println(calculate(" 3+5 / 2 ") == 5);
        System.out.println(calculate("3/2  ") == 1);
        System.out.println(calculate("3+2*2") == 7);
        System.out.println(calculate("3-2+2") == 3);
        System.out.println(calculate("4/3+2") == 3);
    }
}
