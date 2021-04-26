package com.leetcode.dp.string;

import com.leetcode.Utils;

/**
 * @author zms
 * @date 6:10 下午 2021/4/26
 * <a href="https://leetcode.com/problems/interleaving-string/">interleaving string</a>
 */
public class InterleavingString {
    /**
     * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
     * <p>
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
     * <p>
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 提示：a + b 意味着字符串 a 和 b 连接。
     * <p>
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * <p>
     * 示例 3：
     * 输入：s1 = "", s2 = "", s3 = ""
     * 输出：true
     * <p>
     * 提示：
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1、s2、和 s3 都由小写英文字母组成
     */
    // 状态：dp[i][j] s3(i + j) 是由 s1(i) 和 s2(j) 组成
    // 状态转移方程：dp[i][j] (dp[i - 1][j] && s1[i] == s3[i + j]) OR (dp[i][j - 1] && s2[j] == s3[i + j])
    // 初始化：i = 0 的时候 和 j = 0的时候
    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        if (n + m != l) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] t = s3.toCharArray();
        // TODO 压缩空间
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (c1[i - 1] == t[i - 1]) {
                dp[i][0] |= dp[i - 1][0];
            }
        }

        for (int i = 1; i <= m; i++) {
            if (c2[i - 1] == t[i - 1]) {
                dp[0][i] |= dp[0][i - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (c1[i - 1] == t[i + j - 1]) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (c2[j - 1] == t[i + j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        Utils.printTwoArrays(dp);
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(!isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("", "", ""));
        System.out.println(isInterleave("a", "", "a"));
    }
}
