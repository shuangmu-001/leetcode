package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * 完全背包
 *
 * @author zms
 * @date 4:03 下午 2021/10/15
 */
public class Code16CoinsWayNoLimit implements Test {
    // arr是货币数组，其中的值都是正数且没有重复，再给定一个正数aim。
    // 每个值都认为是一张面值，且认为张数是无限的
    // 返回组成aim的方法数
    public static int coinWays01(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process01(arr, 0, aim);
    }

    public static int process01(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ans += process01(arr, index + 1, rest - zhang * arr[index]);
        }
        return ans;
    }

    public static int coinWays02(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        // 可变参数怎么变
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    dp[index][rest] += dp[index + 1][rest - zhang * arr[index]];
//                }
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static int coinWays03(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        // 可变参数怎么变
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                if (rest - arr[index] >= 0) {
                    dp[rest] += dp[rest - arr[index]];
                }
            }
        }
        return dp[aim];
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int amount = genRandomNum(n * n);
        int ans01 = coinWays02(arr, amount);
        int ans02 = coinWays03(arr, amount);
        if (ans01 != ans02) {
            Utils.printArrays(arr);
            System.out.printf("错误输入:%d\n", amount);
            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
