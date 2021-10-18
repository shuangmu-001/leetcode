package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * @author zms
 * @date 4:00 下午 2021/10/15
 */
public class Code14MinPathSum implements Test {

    // 给定一个二维数组matrix，一个人必须从左上角，最后到达右下角，
    // 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
    // 返回最小距离累加和
    public static int minPathSum01(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    // 空间压缩
    // n 和 m 如果相差比较大的话，选择较小的作为dp数组的长度
    public static int minPathSum02(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] dp = new int[m];
        dp[0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            dp[0] += matrix[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
            }
        }
        return dp[m - 1];
    }

    @Override
    public void test(int n) {
        int[][] matrix = genRandomTwoArr(n);
        int ans01 = minPathSum01(matrix);
        int ans02 = minPathSum02(matrix);
        if (ans01 != ans02) {
            Utils.printTwoArrays(matrix);
            System.out.printf("错误的输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
