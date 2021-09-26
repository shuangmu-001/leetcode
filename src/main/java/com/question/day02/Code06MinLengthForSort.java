package com.question.day02;

import com.Test;
import com.Utils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/">
 * Shortest Unsorted Continuous Subarray</a>
 *
 * @author zms
 * @date 5:36 下午 2021/9/17
 */
public class Code06MinLengthForSort implements Test {

    /**
     * 给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，返回满足这一设定的子数组中，最短的多长
     * 1，3，10，8，9，7，11，12，5
     *
     * @param arr 数组
     * @return 子数组的长度
     */
    public static int minLengthForSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int len = arr.length;
        // 需要交换的最大索引
        int maxIndex = -1;
        // 从左边开始最大值，找到需要交换的最大索引
        int maxValue = Integer.MIN_VALUE;
        // 需要交换的最小索引
        int minIndex = len;
        // 总右边开始最小值，找到需要交换的最小索引
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (arr[i] < maxValue) {
                maxIndex = i;
            }
            maxValue = Math.max(maxValue, arr[i]);
            if (arr[len - i - 1] > minValue) {
                minIndex = len - i - 1;
            }
            minValue = Math.min(minValue, arr[len - i - 1]);
        }
        return maxIndex == -1 ? 0 : maxIndex - minIndex + 1;
    }

    public static int minLengthForSort1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int len = arr.length;
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int start = len, end = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != copy[i]) {
                start = Math.min(i, start);
                end = Math.max(i, end);
            }
        }
        return end > start ? end - start + 1 : 0;
    }

    @Override
    public void test(int n) {
        int[] ints = genRandomArr(n);
        if (minLengthForSort(ints) != minLengthForSort1(ints)) {
            System.out.println("最短排序的子数组");
            Utils.printArrays(ints);
        }
    }
}
