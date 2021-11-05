package com.algs.slidingwindow;

import com.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zms
 * @date 2:18 下午 2021/11/4
 */
public class Code02AllLessNumSubArray implements Test {
    /**
     * 给定一个整型数组arr,和一个整数num
     * arr中的子数组sub，如果想达标，必须满足：
     * sub中最大值-sub中最小值 <= num,
     * 返回arr中达标子数组的数量
     */
    // force 绝对正确的方式
    public static int right(int[] arr, int sum) {
        if (sum < 0 || arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                int min = arr[l];
                int max = arr[l];
                for (int i = l + 1; i <= r; i++) {
                    min = Math.min(min, arr[i]);
                    max = Math.max(max, arr[i]);
                }
                if (max - min <= sum) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 如果arr[l ～ r] 的max - min > sum 那么arr[l ～ r + 1] 也一定是
    // 如果arr[l ～ r] 的max - min <= sum 那么arr[l ～ r - 1] 也一定是
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        Deque<Integer> maxWindow = new LinkedList<>();
        Deque<Integer> minWindow = new LinkedList<>();
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[r]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(r);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[r]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(r);
                int max = maxWindow.peekFirst();
                int min = minWindow.peekFirst();
                if (arr[max] - arr[min] > sum) {
                    break;
                }
                r++;
            }
            ans += r - l;
            if (l == maxWindow.peekFirst()) {
                maxWindow.pollFirst();
            }
            if (l == minWindow.peekFirst()) {
                minWindow.pollFirst();
            }
        }
        return ans;
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int sum = genRandomNum(n);
        int ans01 = right(arr, sum);
        int ans02 = num(arr, sum);
        if (ans01 != ans02) {
            System.out.printf("错误输入:%s,%d\n", Arrays.toString(arr), sum);
            System.out.printf("错误输出:%d,%d\n", ans01, ans02);
            throw new RuntimeException();
        }
    }
}
