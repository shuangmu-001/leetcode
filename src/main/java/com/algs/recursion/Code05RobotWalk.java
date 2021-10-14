package com.algs.recursion;

/**
 * @author zms
 * @date 2:03 下午 2021/10/12
 */
public class Code05RobotWalk {
    /**
     * 假设有排成一行的N个位置，记为1-N，N一定大于或等于2
     * 开始时机器人在其中的M位置上（M一定是1-N中的一个）
     * 如果机器人来到1位置，那么下一步只能往右来到2位置
     * 如果机器人来到N位置，那么下一步只能往左来到N-1位置
     * 如果机器人来到中间位置，那么下一步可以往左走或者往右走
     * 规定机器人必须走K步，最终能来到P位置（P也是1-N中的一个）的方法有多少种
     * 给定4个参数N、M、K、P，返回方法数
     */
    public static int ways01(int n, int m, int p, int k) {
        if (n < 2 || m < 1 || m > n || p < 1 || p > n || k < 1) {
            return -1;
        }
        return process01(n, m, p, k);
    }

    public static int process01(int n, int m, int p, int k) {
        if (k == 0) {
            return p == m ? 1 : 0;
        }
        int ways01 = 0, ways02 = 0;
        // n 左边都可以向右方向走
        if (m >= 1 && m < n) {
            ways01 = process01(n, m + 1, p, k - 1);
        }
        // 1 右边都可以向左方向走
        if (m <= n && m > 1) {
            ways02 = process01(n, m - 1, p, k - 1);
        }
        return ways01 + ways02;
    }

    public static int ways02(int n, int m, int p, int k) {
        if (n < 2 || m < 1 || m > n || p < 1 || p > n || k < 1) {
            return -1;
        }
        // 缓存 m 和 k 的行为
        Integer[][] dp = new Integer[n + 1][k + 1];
        return process02(n, m, p, k, dp);
    }

    // 记忆化搜索
    public static int process02(int n, int m, int p, int k, Integer[][] dp) {
        if (dp[m][k] != null) {
            return dp[m][k];
        }
        if (k == 0) {
            dp[m][k] = p == m ? 1 : 0;
        } else if (m == 1) {
            dp[m][k] = process02(n, 2, p, k - 1, dp);
        } else if (m == n) {
            dp[m][k] = process02(n, n - 1, p, k - 1, dp);
        } else {
            dp[m][k] = process02(n, m + 1, p, k - 1, dp) +
                    process02(n, m - 1, p, k - 1, dp);
        }
        return dp[m][k];
    }

    // dp
    public static int ways03(int n, int m, int p, int k) {
        if (n < 2 || m < 1 || m > n || p < 1 || p > n || k < 1) {
            return -1;
        }
        // 缓存 m 和 k 的行为
        // dp[i][j] 从i出发到p位置走j步的方法数
        int[][] dp = new int[n + 1][k + 1];
        dp[p][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < n; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[n][i] = dp[n - 1][i - 1];
        }
        return dp[m][k];
    }

    // 空间压缩
    public static int ways04(int n, int m, int p, int k) {
        if (n < 2 || m < 1 || m > n || p < 1 || p > n || k < 1) {
            return -1;
        }
        int[] dp = new int[n + 1];
        dp[p] = 1;
        for (int i = 1; i <= k; i++) {
            int pre = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == n) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + dp[j + 1];
                }
                pre = temp;
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        System.out.println(ways01(10, 2, 5, 9));
        System.out.println(ways02(10, 2, 5, 9));
        System.out.println(ways03(10, 2, 5, 9));
        System.out.println(ways04(10, 2, 5, 9));
    }
}
