package com.algs.recursion;

import com.Test;
import com.Utils;

/**
 * 背包
 *
 * @author zms
 * @date 2:22 下午 2021/10/14
 */
public class Code07Knapsack implements Test {

    // 所有的货，重量和价值，都在w和v数组中
    // 其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue01(int[] w, int[] v, int bag) {
        return process01(w, v, bag, 0);
    }

    /**
     * 尝试函数
     *
     * @param w     货物的重量
     * @param v     货物的价值
     * @param rest  背包剩余载重
     * @param index 当前货物的下标
     * @return 载重为rest的背包，从index...中选取最大价值的货物存放
     */
    public static int process01(int[] w, int[] v, int rest, int index) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 不要index的货
        int cost01 = process01(w, v, rest, index + 1);
        // 要index的货
        int cost02 = process01(w, v, rest - w[index], index + 1);
        if (cost02 != -1) {
            cost02 += v[index];
        }
        return Math.max(cost01, cost02);
    }

    public static int maxValue02(int[] w, int[] v, int bag) {
        if (bag < 0 || w == null || v == null || w.length == 0 || v.length == 0 || w.length != v.length) {
            return 0;
        }
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                // 不要index的货
                int cost01 = dp[index + 1][rest];
                int cost02 = 0;
                // 要index的货
                if (rest >= w[index]) {
                    cost02 = dp[index + 1][rest - w[index]] + v[index];
                }
                dp[index][rest] = Math.max(cost01, cost02);
            }
        }
        return dp[0][bag];
    }

    @Override
    public void test(int n) {
        int[] w = genRandomArr(n);
        int[] v = genRandomArr(n);
        int bag = genRandomNum(n);
        int ans01 = maxValue01(w, v, bag);
        int ans02 = maxValue02(w, v, bag);
        if (ans01 != ans02) {
            Utils.printArrays(w);
            Utils.printArrays(v);
            System.out.printf("错误的输入:%d\n", bag);
            System.out.printf("错误的输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
