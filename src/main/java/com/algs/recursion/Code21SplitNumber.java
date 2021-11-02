package com.algs.recursion;

/**
 * @author zms
 * @date 5:33 下午 2021/10/19
 */
public class Code21SplitNumber {
    // 给定一个正数n，求裂开的方法数
    // 1 = 1
    // 2 = 1，1； 2
    // 3 = 1，1，1；1，2；3
    // 4 = 1，1，1，1；1，1，2；1，3；2，2；4
    public static int ways01(int n) {
        if (n <= 0) {
            return 0;
        }
        return process01(n, 1);
    }

    public static int process01(int rest, int pre) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += process01(rest - i, i);
        }
        return ans;
    }

    public static int ways02(int n) {
        if (n <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ans = 0;
                for (int i = pre; i <= rest; i++) {
                    ans += dp[i][rest - i];
                }
                dp[pre][rest] = ans;
            }
        }
//        Utils.printTwoArrays(dp);
        return dp[1][n];
    }
    // 斜率优化
    public static int ways03(int n) {
        if (n <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre > 0; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    // 空间压缩
    public static int ways04(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[n] = 1;
        for (int pre = n - 1; pre > 0; pre--) {
            dp[pre] = 1;
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[rest] = dp[rest] + dp[rest - pre];
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(ways01(12));
        System.out.println(ways02(12));
        System.out.println(ways03(12));
        System.out.println(ways04(12));
    }
}
