package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * @author zms
 * @date 4:05 下午 2021/10/20
 */
public class Code22SplitSumClosed implements Test {
    // 给定一个正整数数组arr
    // 请把arr中所有的数组分成两个集合，尽量让两个集合的累加和接近，
    // 最接近的情况下，较小集合的累加和
    public static int right01(int[] arr) {
        return process01(arr, 0, half(arr));
    }

    public static int process01(int[] arr, int index, int rest) {
        if (index >= arr.length) {
            return 0;
        }
        int ans01 = process01(arr, index + 1, rest);
        int ans02 = 0;
        if (rest >= arr[index]) {
            ans02 = process01(arr, index + 1, rest - arr[index]) + arr[index];
        }
        return Math.max(ans01, ans02);
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int half(int[] arr) {
        return (sum(arr) >> 1);
    }

    public static int right02(int[] arr) {
        int half = half(arr);
        int n = arr.length;
        int[][] dp = new int[n + 1][half + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = half; rest >= 0; rest--) {
                int ans01 = dp[index + 1][rest];
                int ans02 = 0;
                if (rest >= arr[index]) {
                    ans02 = dp[index + 1][rest - arr[index]] + arr[index];
                }
                dp[index][rest] = Math.max(ans01, ans02);
            }
        }
        return dp[0][half];
    }

    // 空间压缩
    public static int right03(int[] arr) {
        int half = half(arr);
        int n = arr.length;
        int[] dp = new int[half + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = half; rest >= arr[index]; rest--) {
                dp[rest] = Math.max(dp[rest], dp[rest - arr[index]] + arr[index]);
            }
        }
        return dp[half];
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int ans01 = right01(arr);
        int ans02 = right03(arr);
        if (ans01 != ans02) {
            Utils.printArrays(arr);
            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
