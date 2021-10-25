package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * @author zms
 * @date 5:33 下午 2021/10/19
 */
public class Code20MinCoinsNoLimit implements Test {
    // arr是面值数组，其中的值都是正数且没有重复，在给定一个正数aim。
    // 每个值都认为是一种面值，且认为张数无限的，
    // 返回组成aim的最少货币数
    public static int minCoins01(int[] arr, int aim) {
        return process01(arr, 0, aim);
    }

    private static int process01(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int count = process01(arr, index + 1, rest - zhang * arr[index]);
            if (count != Integer.MAX_VALUE) {
                ans = Math.min(ans, zhang + count);
            }
        }
        return ans;
    }

    public static int minCoins02(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int count = dp[index + 1][rest - zhang * arr[index]];
                    if (count != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + count);
                    }
                }
                dp[index][rest] = ans;
            }
        }

        return dp[0][aim];
    }

    public static int minCoins03(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= arr[index] && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    public static int minCoins04(int[] arr, int aim) {
        int n = arr.length;
        int[] dp = new int[aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                if (rest >= arr[index] &&
                        dp[rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[rest] = Math.min(dp[rest], dp[rest - arr[index]] + 1);
                }
            }
        }
        return dp[aim];
    }

    @Override
    public int[] genRandomArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * n) + 1;
        }
        return arr;
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int amount = genRandomNum(n);
        int ans01 = minCoins04(arr, amount);
        int ans02 = minCoins03(arr, amount);
        if (ans01 != ans02) {
            Utils.printArrays(arr);
            System.out.printf("错误输入:%d\n", amount);
            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
