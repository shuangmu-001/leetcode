package com.algs.recursion;

import com.Test;

/**
 * @author zms
 * @date 5:50 下午 2021/10/14
 */
public class Code12HorseJump implements Test {
    // 把整个棋盘放入第一象限，棋盘的最左下角是0，0位置
    // 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
    // 给你三个参数x、y、k
    // 返回"马"从（0，0）位置出发，必须走k步
    // 最后落在（x,y）上的方法数有多少种？
    public static int jump01(int a, int b, int k) {
        return process01(a, b, k, 0, 0);
    }

    // 当前来到的位置是（x，y）
    // 剩余步数rest
    // rest步之后正好跳到（a，b）
    public static int process01(int a, int b, int rest, int x, int y) {
        // 边界问题
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return a == x && y == b ? 1 : 0;
        }
        int ans = 0;
        ans += process01(a, b, rest - 1, x + 2, y + 1);
        ans += process01(a, b, rest - 1, x + 1, y + 2);
        ans += process01(a, b, rest - 1, x + 2, y - 1);
        ans += process01(a, b, rest - 1, x + 1, y - 2);
        ans += process01(a, b, rest - 1, x - 2, y + 1);
        ans += process01(a, b, rest - 1, x - 1, y + 2);
        ans += process01(a, b, rest - 1, x - 2, y - 1);
        ans += process01(a, b, rest - 1, x - 1, y - 2);
        return ans;
    }

    public static int jump02(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ans = 0;
                    ans += pick(dp, rest - 1, x + 2, y + 1);
                    ans += pick(dp, rest - 1, x + 1, y + 2);
                    ans += pick(dp, rest - 1, x + 2, y - 1);
                    ans += pick(dp, rest - 1, x + 1, y - 2);
                    ans += pick(dp, rest - 1, x - 2, y + 1);
                    ans += pick(dp, rest - 1, x - 1, y + 2);
                    ans += pick(dp, rest - 1, x - 2, y - 1);
                    ans += pick(dp, rest - 1, x - 1, y - 2);
                    dp[x][y][rest] = ans;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int rest, int x, int y) {
        // 边界问题
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    @Override
    public void test(int n) {
        int a = genRandomNum(10);
        int b = genRandomNum(7);
        int ways01 = jump01(a, b, n);
        int ways02 = jump02(a, b, n);
        if (ways01 != ways02) {
            System.out.printf("错误的输入:%d,%d,%d\n", a, b, n);
            System.out.printf("错误的输出:%d,%d\n", ways01, ways02);
            throw new RuntimeException();
        }
    }
}
