package com.question.day02;

import com.Test;
import com.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 现有司机N人，调度中心会将所有司机平分给A、B两个区域
 * 第i个司机去A可得收入为income[i][0],
 * 第i个司机去B可得收入为income[i][1],
 * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
 *
 * @author wcl
 * @date 11:58 下午 2021/9/17
 */
public class Code04Drive implements Test {

    // income -> N * 2 的矩阵 N是偶数
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int len = income.length >> 1;
        return maxMoney1Helper(income, 0, len);
    }

    /**
     * @param income 收入
     * @param index  索引，第i个司机
     * @param rest   剩余去A区域的人数
     * @return 第i个司机及以后的司机最高收入
     */
    public static int maxMoney1Helper(int[][] income, int index, int rest) {
        int len = income.length;
        if (index >= len) {
            return 0;
        }
        if (rest == len - index) {
            return income[index][0] + maxMoney1Helper(income, index + 1, rest - 1);
        }
        if (rest <= 0) {
            return income[index][1] + maxMoney1Helper(income, index + 1, rest);
        }
        int a1 = income[index][0] + maxMoney1Helper(income, index + 1, rest - 1);
        int b1 = income[index][1] + maxMoney1Helper(income, index + 1, rest);
        return Math.max(a1, b1);
    }

    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int len = income.length >> 1;
        return maxMoney2Helper(income, 0, len, new HashMap<>());
    }

    /**
     * @param income 收入
     * @param index  索引，第i个司机
     * @param rest   剩余去A区域的人数
     * @param map    缓存数据
     * @return 第i个司机及以后的司机最高收入
     */
    public static int maxMoney2Helper(int[][] income, int index, int rest, Map<Integer, Map<Integer, Integer>> map) {
        int len = income.length;
        if (index >= len) {
            return 0;
        }
        if (map.containsKey(index) && map.get(index).containsKey(rest)) {
            return map.get(index).get(rest);
        }
        if (rest == len - index) {
            int res = income[index][0] + maxMoney2Helper(income, index + 1, rest - 1, map);
            cache(index, rest, res, map);
            return res;
        }
        if (rest <= 0) {
            int res = income[index][1] + maxMoney2Helper(income, index + 1, rest, map);
            cache(index, rest, res, map);
            return res;
        }
        int a1 = income[index][0] + maxMoney2Helper(income, index + 1, rest - 1, map);
        int b1 = income[index][1] + maxMoney2Helper(income, index + 1, rest, map);
        int res = Math.max(a1, b1);
        cache(index, rest, res, map);
        return res;
    }

    private static void cache(int index, int rest, int res, Map<Integer, Map<Integer, Integer>> map) {
        if (!map.containsKey(index)) {
            map.put(index, new HashMap<>());
        }
        Map<Integer, Integer> dp = map.get(index);
        dp.put(rest, res);
    }

    public static int maxMoney(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int n = income.length;
        int m = n >> 1;
        // dp[n][m] 表示第n个司机的时候，A区还可以去m个司机
        // 初始化 dp[n][0] = income[n - 1][1]; dp[n][1] = income[n - 1][0];
        int[][] dp = new int[n + 1][m + 1];
        dp[n][0] = income[n - 1][1];
        dp[n][1] = income[n - 1][0];
        for (int i = n - 1; i > 0; i--) {
            // 最少剩余人数
            int start = Math.max(m - i - 1, 0);
            // 最多剩余人数
            int end = Math.min(n - i + 1, m);
            for (int j = start; j <= end; j++) {
                if (n - i + 1 == j) {
                    dp[i][j] = dp[i + 1][j - 1] + income[i - 1][0];
                } else if (j == 0) {
                    dp[i][j] = dp[i + 1][j] + income[i - 1][1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j] + income[i - 1][1],
                            dp[i + 1][j - 1] + income[i - 1][0]);
                }
            }
        }
        return dp[1][m];
    }

    // dp的空间压缩
    public static int maxMoney3(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int n = income.length;
        int m = n >> 1;
        int[] dp = new int[m + 1];
        dp[0] = income[n - 1][1];
        dp[1] = income[n - 1][0];
        for (int i = n - 1; i > 0; i--) {
            int start = i > m ? 0 : m - i;
            int end = Math.min(n - i + 1, m);
            int before = dp[0];
            int next;
            for (int j = start; j <= end; j++) {
                next = dp[j];
                if (n - i + 1 == j) {
                    dp[j] = before + income[i - 1][0];
                } else if (j == 0) {
                    dp[j] = dp[j] + income[i - 1][1];
                } else {
                    dp[j] = Math.max(dp[j] + income[i - 1][1], before + income[i - 1][0]);
                }
                before = next;
            }
        }
        return dp[m];
    }

    // TODO 贪心
    // 10个人都先去A 然后将其中5个替换调
    // 如何替换调5个数
    // a, b c, d e, f g, h
    // h - g > f - e > d - c > b - a
    // a + c + e + g + （h - g）+ (f - e)
    // a + c + e + g + （d - c）+ (b - a)
    public static int maxMoney4(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int n = income.length;
        int[] diff = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(diff);
        int m = n >> 1;
        for (int i = n - 1; i >= m; i--) {
            sum += diff[i];
        }
        return sum;
    }

    @Override
    public int[][] genRandomTwoArr(int n) {
        int N = ThreadLocalRandom.current().nextInt(1, n);
        // N 必须是偶数
        if ((N & 1) != 0) {
            N = N + 1;
        }
        int[][] ints = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                ints[i][j] = new Random().nextInt(n);
            }
        }
        return ints;
    }

    @Override
    public void test(int n) {
        int[][] ints = genRandomTwoArr(n);
        if (maxMoney4(ints) != maxMoney3(ints)) {
            System.out.println("司机收入不一致 : " + maxMoney3(ints) + ";" + maxMoney4(ints));
            Utils.printTwoArrays(ints);
        }
    }
}
