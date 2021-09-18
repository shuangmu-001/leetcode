package com.question.day01;


import com.Test;
import com.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr，你可以在每个数字之前决定 + 或者 - 但是必须所有数字都参与，在给定一个数target，请问最后算出target的方法数是多少？
 * <a href="https://leetcode.com/problems/target-sum/">
 * Target Sum</a>
 *
 * @author wcl
 * @date 3:46 下午 2021/9/15
 */
public class Code07TargetSum implements Test {

    public static int findTargetSumWays1(int[] arr, int target) {
        return findTargetSumWays1Helper(arr, 0, target);
    }

    public static int findTargetSumWays1Helper(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        return findTargetSumWays1Helper(arr, index + 1, target + arr[index])
                + findTargetSumWays1Helper(arr, index + 1, target - arr[index]);
    }

    // 增加缓存
    public static int findTargetSumWays2(int[] arr, int target) {
        return findTargetSumWays2Helper(arr, 0, target, new HashMap<>());
    }

    public static int findTargetSumWays2Helper(int[] arr, int index, int target, Map<Integer, Map<Integer, Integer>> map) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        if (map.containsKey(index) && map.get(index).containsKey(target)) {
            return map.get(index).get(target);
        }
        int res = findTargetSumWays2Helper(arr, index + 1, target + arr[index], map)
                + findTargetSumWays2Helper(arr, index + 1, target - arr[index], map);
        if (!map.containsKey(index)) {
            map.put(index, new HashMap<>());
        }
        Map<Integer, Integer> param = map.get(index);
        param.put(target, res);
        return res;
    }

    /**
     * 优化点
     * 1、将数组变成非负数
     * 2、sum < target 0中方式
     * 3、sum 和 target 的奇偶性不相同 0中方式 怎么证明 同奇同偶的两个数的和一定是偶数
     * 4、target < sum 则 sum 可以分成 P N 两个数能获得 P - N = target   2 * P = target + sum
     * 将问题转化成背包问题
     */
    public static int findTargetSumWays(int[] arr, int target) {
        if (target < 0) {
            target = -target;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        // sum < target 和 sum 和 target 的奇偶性不相同
        if (sum < target || ((target & 1) ^ (sum & 1)) == 1) {
            return 0;
        }
        // TODO 01背包
        int len = ((sum + target) >> 1);
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i : arr) {
            for (int j = len; j >= i; j--) {
                dp[j] += dp[j - i];
            }
        }
        return dp[len];
    }

    @Override
    public void test(int n) {
        int[] ints = genRandomArr(n);
        int target = genRandomNum(n);
        if (findTargetSumWays1(ints, target) != findTargetSumWays(ints, target)) {
            System.out.println("当前数据为");
            Utils.printArrays(ints);
            System.out.println(target);
        }
    }
}
