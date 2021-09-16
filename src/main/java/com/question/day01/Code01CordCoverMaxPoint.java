package com.question.day01;

import com.Test;
import com.leetcode.Utils;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？即使绳子边缘处盖住点也算盖住
 *
 * @author wcl
 * @date 2:36 下午 2021/9/16
 */
public class Code01CordCoverMaxPoint implements Test {

    public int cordCoverMaxPoint1(int[] arr, int K) {
        int res = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int count = 1;
            // j 不用每次都从j + 1 开始
            // 上次循环最后的j为下次开始
            // arr[j - 1] - arr[i + 1] <= K
            for (int j = i + 1; j < len && arr[j] - arr[i] <= K; j++) {
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public int cordCoverMaxPoint2(int[] arr, int K) {
        int res = 0;
        int len = arr.length;
        int left = 0;
        int right = 0;
        while (left < len && right < len) {
            while (right < len && arr[right] - arr[left] <= K) {
                right++;
            }
            res = Math.max(res, right - left++);
        }
        return res;
    }

    public int cordCoverMaxPoint3(int[] arr, int K) {
        int res = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            // 找到左边
            int nearestIndex = nearestIndex(arr, i, arr[i] - K);
            res = Math.max(res, i - nearestIndex + 1);
        }
        return res;
    }

    public int nearestIndex(int[] arr, int right, int target) {
        int left = 0;
        int index = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // TODO 重复数据 找到最左边
            if (arr[mid] >= target) {
                index = mid;
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return index;
    }

    @Override
    public void test(int n) {
        int[] ints = genRandomArr(n);
        int k = genRandomNum(n);
        Arrays.sort(ints);
        if (cordCoverMaxPoint1(ints, k) != cordCoverMaxPoint2(ints, k)) {
            System.out.println("当前数据为");
            Utils.printArrays(ints);
            System.out.println(k);
        }

        if (cordCoverMaxPoint1(ints, k) != cordCoverMaxPoint3(ints, k)) {
            System.out.printf("结果应该是:%d\n", cordCoverMaxPoint1(ints, k));
            System.out.println("当前数据为");
            Utils.printArrays(ints);
            System.out.println(k);
        }
    }
}
