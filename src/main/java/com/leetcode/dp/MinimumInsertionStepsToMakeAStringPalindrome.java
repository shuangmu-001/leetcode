package com.leetcode.dp;

import java.util.Random;

/**
 * <a href="https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/">
 * Minimum Insertion Steps to Make a String Palindrome</a>
 *
 * @author zms
 * @date 11:18 上午 2021/9/30
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {
    /**
     * 给你一个字符串s，每一次操作你都可以在字符串的任意位置插入任意字符。
     * 请你返回让s成为回文串的最少操作次数。
     * 「回文串」是正读和反读都相同的字符串。
     * <p>
     * 示例 1：
     * 输入：s = "zzazz"
     * 输出：0
     * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
     * <p>
     * 示例 2：
     * 输入：s = "mbadm"
     * 输出：2
     * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
     * <p>
     * 示例 3：
     * 输入：s = "leetcode"
     * 输出：5
     * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
     * <p>
     * 示例 4：
     * 输入：s = "g"
     * 输出：0
     * <p>
     * 示例 5：
     * 输入：s = "no"
     * 输出：1
     * <p>
     * 提示：
     * 1 <= s.length <= 500
     * s中所有字符都是小写字母。
     */
    // 暴力递归
    public static int minInsertions01(String s) {
        char[] str = s.toCharArray();
        return process01(str, 0, s.length() - 1);
    }

    public static int process01(char[] str, int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (str[left] == str[right]) {
            return process01(str, left + 1, right - 1);
        } else {
            // 左边插入
            int l = process01(str, left, right - 1);
            // 右边插入
            int r = process01(str, left + 1, right);
            return Math.min(r, l) + 1;
        }
    }

    // 记忆化搜索
    public static int minInsertions02(String s) {
        char[] str = s.toCharArray();
        int length = s.length();
        Integer[][] dp = new Integer[length][length];
        return process02(str, 0, s.length() - 1, dp);
    }

    public static int process02(char[] str, int left, int right, Integer[][] dp) {
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        if (left >= right) {
            return 0;
        }
        int ans;
        if (str[left] == str[right]) {
            ans = process02(str, left + 1, right - 1, dp);
        } else {
            // 左边插入
            int l = process02(str, left, right - 1, dp);
            // 右边插入
            int r = process02(str, left + 1, right, dp);
            ans = Math.min(r, l) + 1;
        }
        dp[left][right] = ans;
        return ans;
    }

    // 动态规划
    // 通过记忆化搜索得到：dp[0][length - 1] = Math.min(dp[1][length - 1], dp[0][length - 2]) + 1;
    // 区间dp 那就区间的长度
    // dp[i][j] i 表示区间的长度，j表示结尾是第j个字符
    // dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + 1;
    public static int minInsertions(String s) {
        char[] str = s.toCharArray();
        int length = s.length();
        int[][] dp = new int[length + 1][length + 1];
        for (int i = 2; i <= length; i++) {
            for (int j = i; j <= length; j++) {
                int start = j - i;
                if (str[start] == str[j - 1]) {
                    dp[i][j] = dp[i - 2][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[length][length];
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz") == 0);
        System.out.println(minInsertions("mbadm") == 2);
        System.out.println(minInsertions("leetcode") == 5);
        System.out.println(minInsertions("g") == 0);
        System.out.println(minInsertions("no") == 1);
        char[] chars = new char[500];
        for (int i = 0; i < 500; i++) {
            chars[i] = getSourceStr().charAt(new Random().nextInt(getSourceStr().length()));
        }
        String s = new String(chars);
        System.out.println(minInsertions(s) == minInsertions02(s));
    }

    public static String getSourceStr() {
        return "abcdefghijklmnopqrstwvuxyz";
    }
}
