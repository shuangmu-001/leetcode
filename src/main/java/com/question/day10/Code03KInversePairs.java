package com.question.day10;

import com.Test;

/**
 * <a href="https://leetcode.com/problems/k-inverse-pairs-array/">
 * k inverse pairs array</a>
 * TODO 1、动态规划的斜率优化；2、根据范围猜解法
 *
 * @author zms
 * @date 4:42 下午 2021/9/29
 */
public class Code03KInversePairs implements Test {
    /**
     * 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数。
     * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对；否则不是。
     * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
     * <p>
     * 示例 1:
     * 输入: n = 3, k = 0
     * 输出: 1
     * 解释:
     * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
     * <p>
     * 示例 2:
     * 输入: n = 3, k = 1
     * 输出: 2
     * 解释:
     * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
     * <p>
     * 说明:
     * n的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
     */
    public int kInversePairs01(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int mod = 1_000_000_007;
        // dp[i][j] 表示第i个数据有j个逆序对
        // 0 个逆序对 所有数字范围都是1；
        // 数字为1，不可能存在逆序对
        // dp[i][j] = dp[i - 1][j - i + 1] + .... + dp[i - 1][j]
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    int start = Math.max(0, j - i + 1);
                    for (int l = start; l <= j; l++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][l]) % mod;
                    }
                }
            }
        }
        return dp[n][k];
    }

    public int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int mod = 1_000_000_007;
        // dp[i][j] 表示第i个数据有j个逆序对
        // 0 个逆序对 所有数字范围都是1；
        // 数字为1，不可能存在逆序对
        // dp[i][j] = dp[i - 1][j - i + 1] + .... + dp[i - 1][j]
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                // 斜率优化
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }

    @Override
    public void test(int n) {
        int num = genRandomNum(n);
        int k = genRandomNum(n);
        int ans01 = kInversePairs01(num, k);
        int ans02 = kInversePairs(num, k);
        if (ans01 != ans02) {
            System.out.printf("当前入参[n]:%d;[k]:%d\n", num, k);
            System.out.printf("结果[ans01]:%d;[ans02]:%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
