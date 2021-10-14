package com.algs.recursion;

import com.Test;

/**
 * 样本对应模型
 * <a href="https://leetcode.com/problems/longest-common-subsequence/">Longest Common Subsequence</a>
 *
 * @author zms
 * @date 4:15 下午 2021/10/14
 */
public class Code10LongestCommonSubsequence implements Test {

    public int longestCommonSubsequence01(String text1, String text2) {
        if (text1 == null || text2 == null || text2.length() == 0 || text1.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        return process01(str1, str2, 0, 0);
    }

    public int process01(char[] str1, char[] str2, int i, int j) {
        if (i == str1.length || j == str2.length) {
            return 0;
        }
        int ans = 0;
        if (i == str1.length - 1 && j == str2.length - 1) {
            ans = str1[i] == str2[j] ? 1 : 0;
        } else if (i == str1.length - 1) {
            if (str1[i] == str2[j]) {
                ans = 1;
            } else {
                ans = process01(str1, str2, i, j + 1);
            }
        } else if (j == str2.length - 1) {
            if (str1[i] == str2[j]) {
                ans = 1;
            } else {
                ans = process01(str1, str2, i + 1, j);
            }
        } else {
            if (str1[i] == str2[j]) {
                ans = process01(str1, str2, i + 1, j + 1) + 1;
            }
            int move1 = process01(str1, str2, i + 1, j);
            int move2 = process01(str1, str2, i, j + 1);
            ans = Math.max(ans, Math.max(move1, move2));
        }
        return ans;
    }

    public int longestCommonSubsequence02(String text1, String text2) {
        if (text1 == null || text2 == null || text2.length() == 0 || text1.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = str1[n - 1] == str2[m - 1] ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = str1[i] == str2[m - 1] ? 1 : dp[i + 1][m - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = str1[n - 1] == str2[i] ? 1 : dp[n - 1][i + 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    @Override
    public void test(int n) {
        int len01 = genRandomNum(n);
        int len02 = genRandomNum(n);
        String str01 = genTargetStr(len01);
        String str02 = genTargetStr(len02);
        int target = longestCommonSubsequence01(str01, str02);
        int ans = longestCommonSubsequence02(str01, str02);
        if (target != ans) {
            System.out.printf("错误的输入:%s,%s\n", str01, str02);
            System.out.printf("错误的输出:%d,%d\n", target, ans);
            throw new RuntimeException();
        }
    }
}
