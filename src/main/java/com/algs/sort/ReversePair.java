package com.algs.sort;

import com.Test;

import java.util.Arrays;

/**
 * 逆序对
 *
 * @author zms
 * @date 4:53 下午 2021/5/12
 */
public class ReversePair implements Test {

    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length, res = 0;
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
        int res = 0, p1 = 0, p2 = i, p3 = mid + 1;
        while (p2 <= mid && p3 <= j) {
            if (arr[p2] > arr[p3]) {
                res += (mid - p2 + 1);
                temp[p1++] = arr[p3++];
            } else {
                temp[p1++] = arr[p2++];
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

    public static int reversePair01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public void test(int n) {
        int[] arr1 = genRandomArr(n);
        if (reversePair01(arr1) != reversePair(arr1)) {
            System.err.println("reverse pair num error : " + Arrays.toString(arr1));
        }
    }
}
