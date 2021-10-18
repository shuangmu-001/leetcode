package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * 01 背包
 *
 * @author zms
 * @date 4:02 下午 2021/10/15
 */
public class Code15CoinsWayEveryPaperDifferent implements Test {
    // arr是货币数组，其中的值都是正数，再给定一个正数aim。
    // 每个值都认为是一张货币
    // 即便是值相同的货币也认为每一张都是不同的
    // 返回组成aim的方法数
    public static int coinWays01(int[] arr, int aim) {
        return process01(arr, 0, aim);
    }

    private static int process01(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process01(arr, index + 1, rest) + process01(arr, index + 1, rest - arr[index]);
    }

    public static int coinWays02(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index + 1][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 空间压缩
    public static int coinWays03(int[] arr, int aim) {
        int n = arr.length;
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            // dp[index][rest] 依赖的是dp[index + 1][rest - arr[index]]
            // 压缩成一维数组的原因是index至于index + 1有关，与其他行无关
            // 从右往左的原因是，rest 依赖的是index + 1层左侧的数据
            // 从左往右的 每次修改rest的数据还要记录旧的rest数据，rest + x 有可能用到
            // rest - arr[index] 不固定，所以一个变量没办法控制，至少还需要一个数组
            // 从右往左，左边的数据就是index + 1层的数据，修个rest不会影响rest - 1的数据
            for (int rest = aim; rest >= arr[index]; rest--) {
                dp[rest] += dp[rest - arr[index]];
            }
        }
        return dp[aim];
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int amount = genRandomNum(20);
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
