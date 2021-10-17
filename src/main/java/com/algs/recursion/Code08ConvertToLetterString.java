package com.algs.recursion;

import com.Test;

/**
 * @author zms
 * @date 3:03 下午 2021/10/14
 */
public class Code08ConvertToLetterString implements Test {

    // 规定1和A对应、2和B对应、3和C对应...26和Z对应
    // 那么一个数字字符串比如"111" 就可以转化为：
    // "AAA"、"KA"和"AK"
    // 给定一个只有数字字符组成的字符串str，返回有多少中转化结果
    public static int number01(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process01(s.toCharArray(), 0);
    }

    // str[i...] 有多少中结果
    public static int process01(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        // str[i] 替换的
        int ans = process01(str, i + 1);
        // str[i] 和 str[i + 1] 组合成一起替换
        if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
            ans += process01(str, i + 2);
        }
        return ans;
    }

    public static int number02(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        char[] str = s.toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                // str[i] 替换的
                dp[i] = dp[i + 1];
                // str[i] 和 str[i + 1] 组合成一起替换
                if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
                    dp[i] += dp[i + 2];
                }
            }

        }
        return dp[0];
    }

    public static int number03(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        char[] str = s.toCharArray();
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int temp = i - 2 >= 0 ? dp[i - 2] : 1;
            if (str[i] != '0') {
                // str[i] 替换的
                dp[i] = dp[i - 1];
                // str[i] 和 str[i - 1] 组合成一起替换
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + (str[i] - '0') < 27) {
                    dp[i] += temp;
                }
            } else {
                if (str[i - 1] == '0' || (str[i - 1] - '0') > 2) {
                    return 0;
                } else {
                    dp[i] = temp;
                }
            }
        }
        return dp[n - 1];
    }

    @Override
    public String getSourceStr() {
        return "0123456789";
    }

    @Override
    public void test(int n) {
        String s = genTargetStr(n);
        int ans01 = number02(s);
        int ans02 = number03(s);
        if (ans01 != ans02) {
            System.out.printf("错误的输入:%s\n", s);
            System.out.printf("错误的输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
