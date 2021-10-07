package com.question.day08;


import java.util.LinkedList;

/**
 * @author zms
 * @date 4:06 下午 2021/9/26
 */
public class Code01ExpressionCompute {

    // 给定一个字符串str，str表示一个公式，
    // 公式里可能有整数，加减乘除符号和左右括号
    // 返回公式的计算结果，难点在于括号可能嵌套很多层，
    // str = "48 * ((70 - 65) - 43) + 8 * 1" 返回 -1816
    // str = "3 + 4 * 1" 返回 7
    // str = "3 + (4 * 1)" 返回 7
    // 【说明】
    // 1、可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性校验
    // 2、如果是负数，就需要用括号括起来，比如"4*(-3)"但如果负数作为公式的开头或括号部分
    // 3、不用考虑计算过程中会发生溢出的情况
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
        LinkedList<Object> linked = new LinkedList<>();
        int cur = 0;
        for (int i = index; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 括号里面交给递归来计算
                // 当前流程只需要括号里面的结果就行了
                Info process = process(s, i + 1);
                cur = process.result;
                i = process.end;
            } else if (c >= '0' && c <= '9') {
                // 当前数字
                cur = cur * 10 + (c - '0');
            } else if (isOperator(c)) {
                // 操作符号
                priorityCalculator(linked, cur);
                linked.addLast(c);
                cur = 0;
            } else if (c == ')') {
                // 完成一个括号返回
                linked.addLast(cur);
                int ans = calculator(linked);
                return new Info(ans, i);
            }
            if (i == length - 1) {
                priorityCalculator(linked, cur);
            }
        }
        int ans = calculator(linked);
        return new Info(ans, length);
    }

    // 链表中只包含 + - 运算
    public static int calculator(LinkedList<Object> linked) {
        if (linked.isEmpty()) {
            return 0;
        }
        int cur = (int) linked.pop();
        while (!linked.isEmpty()) {
            char op = (char) linked.pop();
            int a = (int) linked.pop();
            cur = calculator(cur, a, op);
        }
        return cur;
    }

    // * / 优先计算出结果
    public static void priorityCalculator(LinkedList<Object> linked, int cur) {
        if (!linked.isEmpty() && isPriority((char) linked.peekLast())) {
            char op = (char) linked.removeLast();
            int a = (int) linked.removeLast();
            cur = calculator(a, cur, op);
        }
        linked.addLast(cur);
    }

    // 是否是运算符
    public static boolean isOperator(char op) {
        return op == '*' || op == '/' || op == '+' || op == '-';
    }

    // 是否是 * /
    public static boolean isPriority(char op) {
        return op == '*' || op == '/';
    }

    // 运算
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

    public static void main(String[] args) {
        System.out.println(calculate("48 * ((70 - 65) - 43) + 8 * 1") == -1816);
        System.out.println(calculate("48 * ((70 - 65) - 43)") == -1824);
        System.out.println(calculate("3 + (4 * 1)") == 7);
    }

}
