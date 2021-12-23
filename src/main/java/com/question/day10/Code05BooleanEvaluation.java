package com.question.day10;


/**
 * TODO 区间dp
 * <a href="https://leetcode-cn.com/problems/boolean-evaluation-lcci/">boolean-evaluation-lcci</a>
 *
 * @author zms
 * @date 5:13 下午 2021/9/29
 */
public class Code05BooleanEvaluation {
    /**
     * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
     * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     * <p>
     * 示例 1:
     * 输入: s = "1^0|0|1", result = 0
     * 输出: 2
     * 解释:两种可能的括号方法是
     * 1^(0|(0|1))
     * 1^((0|0)|1)
     * <p>
     * 示例 2:
     * 输入: s = "0&0&0&1^1|0", result = 1
     * 输出: 10
     * <p>
     * 提示：
     * 运算符的数量不超过 19 个
     */
    public int countEval01(String s, int result) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        Info process = process01(str, 0, s.length() - 1);
        return result == 1 ? process.t : process.f;
    }

    // s.length() 一定是奇数
    // 逻辑运算符号一定在奇数位置上
    // 以某个逻辑运算符号为最后一个运算符，则左边的结果*右边的结果就是
    // l到r之间的字符数一定是奇数
    public Info process01(char[] str, int l, int r) {
        int t = 0;
        int f = 0;
        if (l == r) {
            t = str[l] == '1' ? 1 : 0;
            f = str[l] == '0' ? 1 : 0;
            return new Info(t, f);
        }

        for (int i = l + 1; i < r; i += 2) {
            // 符号左边的数量
            Info left = process01(str, l, i - 1);
            // 符号右边的数量
            Info right = process01(str, i + 1, r);
            switch (str[i]) {
                case '&':
                    // 必须都成功
                    t += left.t * right.t;
                    f += left.t * right.f + left.f * right.t + left.f * right.f;
                    break;
                case '|':
                    // 只要有一个成功
                    t += left.t * right.t + left.t * right.f + left.f * right.t;
                    f += left.f * right.f;
                    break;
                case '^':
                    // 相同是false，不同是true
                    t += left.t * right.f + left.f * right.t;
                    f += left.t * right.t + left.f * right.f;
                    break;
            }
        }
        return new Info(t, f);
    }

    public static class Info {
        // 字符串组合成true的方法数
        int t;
        // 字符串组合成false的方法数
        int f;

        public Info(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    public int countEval(String s, int result) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int length = s.length();
        char[] str = s.toCharArray();
        Info[][] dp = new Info[length][length];
        Info process = process(str, 0, length - 1, dp);
        return result == 1 ? process.t : process.f;
    }

    public Info process(char[] str, int l, int r, Info[][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        int t = 0;
        int f = 0;
        if (l == r) {
            t = str[l] == '1' ? 1 : 0;
            f = str[l] == '0' ? 1 : 0;
        } else {
            for (int i = l + 1; i < r; i += 2) {
                // 符号左边的数量
                Info left = process(str, l, i - 1, dp);
                // 符号右边的数量
                Info right = process(str, i + 1, r, dp);
                switch (str[i]) {
                    case '&':
                        // 必须都成功
                        t += left.t * right.t;
                        f += left.t * right.f + left.f * right.t + left.f * right.f;
                        break;
                    case '|':
                        // 只要有一个成功
                        t += left.t * right.t + left.t * right.f + left.f * right.t;
                        f += left.f * right.f;
                        break;
                    case '^':
                        // 相同是false，不同是true
                        t += left.t * right.f + left.f * right.t;
                        f += left.t * right.t + left.f * right.f;
                        break;
                }
            }
        }
        Info info = new Info(t, f);
        dp[l][r] = info;
        return info;
    }
}
