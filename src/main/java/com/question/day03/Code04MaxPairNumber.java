package com.question.day03;

import java.util.Arrays;

/**
 * TODO 暴力解
 *
 * @author zms
 * @date 7:45 下午 2021/9/19
 */
public class Code04MaxPairNumber {

    // 给定一个数组arr，代表每个人的能力值，在给定一个非负数K，
    // 如果两个人能力差值正好为K，那么可以凑在一起比赛，
    // 一局比赛只有两个人，返回最多可以同时有多少场比赛
    public int maxPairNumber(int[] arr, int k) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        Arrays.sort(arr);
        int res = 0;
        int l = 0;
        int r = 0;
        int length = arr.length;
        boolean[] used = new boolean[length];
        while (l < length && r < length) {
            if (used[l]) {
                l++;
            } else if (l >= r) {
                r++;
            } else {
                if (arr[r] - arr[l] == k) {
                    used[r++] = true;
                    used[l++] = true;
                    res++;
                } else if(arr[r] - arr[l] > k){
                    l++;
                } else {
                    r++;
                }
            }
        }
        return res;
    }
}
