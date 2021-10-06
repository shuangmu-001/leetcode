package com.leetcode.recursion;

/**
 * <a href="https://leetcode.com/problems/basic-calculator/">Basic Calculator</a>
 * 括号嵌套
 *
 * @author zms
 * @date 6:41 下午 2021/9/26
 */
public class BasicCalculator {
    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 示例 1：
     * 输入：s = "1 + 1"
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * <p>
     * 示例 3：
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     * <p>
     * 提示：
     * 1 <= s.length <= 3 * 10^5
     * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * s 表示一个有效的表达式
     */
    public static int calculate(String s) {
        return process(s, 0).result;
    }

    public static class Info {
        // 括号里计算的结果
        int result;
        // 括号结束的位置
        int end;

        public Info(int result, int end) {
            this.result = result;
            this.end = end;
        }
    }

    public static Info process(String s, int index) {
        int length = s.length();
        if (index >= length) {
            return new Info(0, length);
        }

        char op = '+';
        int ans = 0;
        int cur = 0;
        while (index < length && s.charAt(index) != ')') {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                cur = cur * 10 + (c - '0');
                index++;
            } else if (c == '(') {
                Info process = process(s, index + 1);
                cur = process.result;
                index = process.end + 1;
            } else if (isOperator(c)) {
                // 运算符
                ans += (op == '-') ? -cur : cur;
                op = c;
                cur = 0;
                index++;
            } else {
                // 空白字符
                index++;
            }
        }
        ans += (op == '-') ? -cur : cur;
        return new Info(ans, index);
    }

    // 是否是运算符
    public static boolean isOperator(char op) {
        return op == '+' || op == '-';
    }

    public static void main(String[] args) {
        System.out.println(calculate("((1+(4+5+ 2)- 3 ) + ( 6+ 8 ) ) ") == 23);
    }

}
