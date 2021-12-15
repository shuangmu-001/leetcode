package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * TODO
 * @author zms
 * @date 4:35 下午 2021/10/20
 */
public class Code23SplitSumClosedSizeHalf implements Test {
    // 给定一个正数数组arr，请把arr中所有数分成两个集合
    // 如果arr长度为偶数，两个集合包含数的个数要一样多
    // 如果arr长度为奇数，两个集合包含数的个数必须只差一个
    // 请尽量让两个集合的累加和接近
    // 返回最接近的情况下，较小集合的累加和
    public static int right01(int[] arr) {
        int n = arr.length;
        int picks = (n >> 1);
        if ((n & 1) == 0) {
            return process01(arr, 0, half(arr), picks);
        } else {
            return Math.max(process01(arr, 0, half(arr), picks),
                    process01(arr, 0, half(arr), picks + 1));
        }
    }

    // 个数
    public static int process01(int[] arr, int index, int rest, int picks) {
        if (index >= arr.length) {
            return picks != 0 ? -1 : 0;
        }
        int ans01 = process01(arr, index + 1, rest, picks);
        int ans02 = -1;
        int next = -1;
        if (rest >= arr[index]) {
            next = process01(arr, index + 1, rest - arr[index], picks - 1);
        }
        if (next != -1) {
            ans02 = next + arr[index];
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
        int count = (n >> 1);
        int[][][] dp = new int[n + 1][half + 1][count + 1];
        for (int index = 0; index <= n; index++) {
            for (int rest = 0; rest <= half; rest++) {
                for (int picks = 0; picks <= count; picks++) {
                    dp[index][rest][picks] = -1;
                }
            }
        }
        // 初始化 dp[n]
        for (int rest = 0; rest <= half; rest++) {
            dp[n][rest][0] = 0;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= half; rest++) {
                for (int picks = 0; picks <= count; picks++) {
                    
                }
            }
        }
        return dp[0][half][count];
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
//        int ans01 = right01(arr);
//        int ans02 = right02(arr);
//        if (ans01 != ans02) {
        Utils.printArrays(arr);
//            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
//            throw new RuntimeException();
//        }
    }
}
