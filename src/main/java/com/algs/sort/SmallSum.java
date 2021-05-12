package com.algs.sort;

import com.Test;

import java.util.Arrays;

/**
 *
 * @author wcl
 * @date 2:35 下午 2021/5/12
 */
public class SmallSum implements Test {
    // 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和，求数组小和
    // 例子：{1, 3, 4, 2, 5}
    // 1左边比1小的数，没有
    // 3左边比3小的数，1
    // 4左边比4小的数，1，3
    // 2左边比2小的数，1
    // 5左边比5小的数，1，3，4，2
    // 所以数组的小和为 1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int res = 0;
        int n = arr.length;
        int len = 1;
        while (len < n) {
            int i = 0;
            int mid = i + len - 1;
            while (mid < n) {
                int j = Math.min(mid + len, n - 1);
                res += merge(arr, i, mid, j);
                i = j + 1;
                mid = i + len - 1;
            }
            len <<= 1;
        }
        return res;
    }

    private static int merge(int[] arr, int i, int mid, int j) {
        int n = j - i + 1;
        int[] temp = new int[n];
        int res = 0;
        int p1 = 0, p2 = i, p3 = mid + 1;
        while (p2 <= mid && p3 <= j) {
            if (arr[p2] < arr[p3]) {
                res += arr[p2] * (j - p3 + 1);
                temp[p1++] = arr[p2++];
            } else {
                temp[p1++] = arr[p3++];
            }
        }
        while (p2 <= mid) {
            temp[p1++] = arr[p2++];
        }
        while (p3 <= j) {
            temp[p1++] = arr[p3++];
        }
        for (p1 = 0; p1 < n; p1++) {
            arr[i++] = temp[p1];
        }
        return res;
    }

    public static int smallSum01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    res += arr[j];
                }
            }
        }
        return res;
    }

    @Override
    public void test(int n) {
        int[] arr1 = genRandomArr(n);
        if (smallSum01(arr1) != smallSum(arr1)) {
            System.err.println("small sum error : " + Arrays.toString(arr1));
        }
    }

}
