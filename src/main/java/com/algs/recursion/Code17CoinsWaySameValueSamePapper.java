package com.algs.recursion;

import com.Test;
import com.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 部分背包
 *
 * @author zms
 * @date 4:03 下午 2021/10/15
 */
public class Code17CoinsWaySameValueSamePapper implements Test {

    // arr是货币数组，其中的值都是正数，再给定一个正数aim。
    // 每个值都认为是一张货币
    // 值相同的货币没有任何不同，
    // 返回组成aim的方法数
    public static int coinWays01(int[] arr, int aim) {
        Info info = getInfo(arr);
        return process01(info.coins, info.zhangs, 0, aim);
    }

    // 统计有多少种货币和每张货币的数量
    public static Info getInfo(int[] arr) {
        // 统计有多少种货币和每张货币的数量
        Map<Integer, Integer> map = new HashMap<>();
        for (int coin : arr) {
            map.merge(coin, 1, Integer::sum);
        }
        int n = map.size();
        int[] coins = new int[n];
        int[] zhangs = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    public static class Info {
        // 不同面额的货币
        int[] coins;
        // 每种面额对应的张数
        int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    /**
     * @param coins  货币面额数组
     * @param zhangs 每张面额对应的张数
     * @param index  当前选择面额的索引
     * @param rest   剩余的金额
     * @return coins[index...] 在有限张数组成rest的种数
     */
    public static int process01(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int zhang = 0; zhang <= zhangs[index] && zhang * coins[index] <= rest; zhang++) {
            ans += process01(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }
        return ans;
    }

    public static int coinWays02(int[] arr, int aim) {
        Info info = getInfo(arr);
        int n = info.coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                for (int zhang = 0; zhang <= info.zhangs[index] && zhang * info.coins[index] <= rest; zhang++) {
//                    dp[index][rest] += dp[index + 1][rest - zhang * info.coins[index]];
//                }
                dp[index][rest] = dp[index + 1][rest];
                if (info.zhangs[index] > 0 && rest - info.coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - info.coins[index]];
                }
                if (info.zhangs[index] > 0 && rest - (info.zhangs[index] + 1) * info.coins[index] >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - (info.zhangs[index] + 1) * info.coins[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static int coinWays03(int[] arr, int aim) {
        Info info = getInfo(arr);
        int n = info.coins.length;
        int[] dp = new int[aim + 1];
        int[] old = new int[aim + 1];
        old[0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[rest] = old[rest];
                if (info.zhangs[index] > 0 && rest - info.coins[index] >= 0) {
                    dp[rest] += dp[rest - info.coins[index]];
                }
                if (info.zhangs[index] > 0 && rest - (info.zhangs[index] + 1) * info.coins[index] >= 0) {
                    dp[rest] -= old[rest - (info.zhangs[index] + 1) * info.coins[index]];
                }
            }
            int[] temp = dp;
            dp = old;
            old = temp;
        }
        return old[aim];
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
        int amount = genRandomNum(n * n);
        int ans02 = coinWays02(arr, amount);
        int ans01 = coinWays01(arr, amount);
        int ans03 = coinWays03(arr, amount);
        if (ans01 != ans02 || ans01 != ans03) {
            Utils.printArrays(arr);
            coinWays01(arr, amount);
            System.out.printf("错误输入:%d\n", amount);
            System.out.printf("错误输出:%d,%d,%d\n", ans01, ans02, ans03);
            throw new RuntimeException();
        }
    }

}
