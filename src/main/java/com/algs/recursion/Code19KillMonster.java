package com.algs.recursion;

import com.Test;

/**
 * @author zms
 * @date 5:17 下午 2021/10/19
 */
public class Code19KillMonster implements Test {
    // 给定3个参数， N，M，K
    // 怪兽有N滴血，等着英雄来砍自己
    // 英雄每一次打击，都会让怪兽流失[0~M]的血量
    // 到底流失多少？每次在[0~M]上等概率的获得一个值
    // 求k次打击之后，英雄把怪兽砍死的概率
    public static double right01(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        // k次打击，每次都有M种可能，所以总共有 (M + 1) ^ k 次可能
        double total = Math.pow((M + 1), K);
        // 其中k次砍死怪兽的可能数
        long kill = process01(N, M, K);
        return (double) kill / total;
    }

    // 最多k次后，英雄把怪兽砍死的次数
    public static long process01(int hp, int M, int times) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        long ans = 0;
        for (int i = 0; i <= M; i++) {
            ans += process01(hp - i, M, times - 1);
        }
        return ans;
    }

    public static double right02(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double total = Math.pow((M + 1), K);
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[0][times] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ans = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i > 0) {
                        ans += dp[hp - i][times - 1];
                    } else {
                        ans += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[hp][times] = ans;
            }
        }
        long kill = dp[N][K];
        return (double) kill / total;
    }

    public static double right03(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double total = Math.pow((M + 1), K);
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[0][times] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[hp][times] = dp[hp - 1][times] + dp[hp][times - 1];
                if (hp > M) {
                    dp[hp][times] -= dp[hp - M - 1][times - 1];
                } else {
                    dp[hp][times] -= dp[0][times - 1];
                }
            }
        }
        long kill = dp[N][K];
        return (double) kill / total;
    }

    @Override
    public void test(int n) {
        int N = genRandomNum(n);
        int M = genRandomNum(n);
        int K = genRandomNum(n);
        double ans01 = right01(N, M, K);
        double ans02 = right03(N, M, K);
        if (ans01 != ans02) {
            System.out.printf("错误输入:%d,%d,%d\n", N, M, K);
            System.out.printf("错误输出:%s,%s\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
