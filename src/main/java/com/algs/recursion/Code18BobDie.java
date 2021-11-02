package com.algs.recursion;

/**
 * @author zms
 * @date 4:04 下午 2021/10/15
 */
public class Code18BobDie {

    // 给定5个参数，N，M， row， col，k
    // 表示在N * M的区域上，醉汉Bob初始在（row，col）位置
    // Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
    // 任何时候Bob只要离开N * M区域，就直接死亡
    // 返回k步之后，Bob还在N * M的区域的概率
    public static double livePosibility01(int row, int col, int k, int N, int M) {
        // 每一步有四种可能，k步，就有 4 ^ k种可能
        double total = Math.pow(4, k);
        // 在 N * M 区域的方法数
        double live = process01(row, col, k, N, M);
        return live / total;
    }

    public static long process01(int row, int col, int rest, int N, int M) {
        // 不在 N * M 区域
        if (row < 0 || col < 0 || row >= N || col >= M) {
            return 0;
        }
        // 在 N * M 区域，用完步数
        if (rest == 0) {
            return 1;
        }
        long up = process01(row, col - 1, rest - 1, N, M);
        long down = process01(row, col + 1, rest - 1, N, M);
        long left = process01(row - 1, col, rest - 1, N, M);
        long right = process01(row + 1, col, rest - 1, N, M);
        return up + down + left + right;
    }

    public static double livePosibility02(int row, int col, int k, int N, int M) {
        // 每一步有四种可能，k步，就有 4 ^ k种可能
        double total = Math.pow(4, k);
        // 在 N * M 区域的方法数
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][rest] = pick(dp, i, j - 1, rest - 1, N, M);
                    dp[i][j][rest] += pick(dp, i, j + 1, rest - 1, N, M);
                    dp[i][j][rest] += pick(dp, i - 1, j, rest - 1, N, M);
                    dp[i][j][rest] += pick(dp, i + 1, j, rest - 1, N, M);
                }
            }
        }
        return dp[row][col][k] / total;
    }

    public static long pick(long[][][] dp, int row, int col, int rest, int N, int M) {
        // 不在 N * M 区域
        if (row < 0 || col < 0 || row >= N || col >= M) {
            return 0;
        }
        return dp[row][col][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility01(6, 6, 10, 50, 50));
        System.out.println(livePosibility02(6, 6, 10, 50, 50));
    }
}
