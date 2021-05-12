package com.algs.sort;

import com.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序 O(NlogN)
 *
 * @author wcl
 * @date 10:52 上午 2021/5/12
 */
public class MergeSort implements Test {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        int len = 1;
        while (len < n) {
            int i = 0;
            int mid = i + len - 1;
            while (mid < n) {
                int j = Math.min(mid + len, n - 1);
                merge(arr, i, mid, j);
                i = j + 1;
                mid = i + len - 1;
            }
            if (len > n / 2) {
                break;
            }
            len <<= 1;
        }
    }

    public static void mergeSort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[l...r]范围上，变成有序的
    // l...r N T(N) = 2 * T(N/2) + O(N)
    // O(NlogN)
    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int n = r - l + 1;
        int[] help = new int[n];
        int i = 0, p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < n; i++) {
            arr[l++] = help[i];
        }
    }

    @Override
    public void test(int n) {
        int[] arr1 = genRandomArr(n);
        int[] arr2 = new int[n];
        System.arraycopy(arr1, 0, arr2, 0, n);
        mergeSort01(arr2);
        mergeSort(arr1);
        for (int i = 0; i < n; i++) {
            if (arr1[i] != arr2[i]) {
                System.err.println("merge sort error : " + Arrays.toString(arr2));
                break;
            }
        }
    }
}
