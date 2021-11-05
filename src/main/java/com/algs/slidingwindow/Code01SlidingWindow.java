package com.algs.slidingwindow;

import com.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口的最大最小值
 *
 * @author zms
 * @date 10:47 上午 2020/5/25
 */
public class Code01SlidingWindow implements Test {
    /**
     * 假设一个固定大小为k的窗口，依次划过nums，
     * 返回每一次滑出状况的最大值
     * 例如：nums=[4,3,5,4,3,3,6,7],k = 3
     * 返回：【5，5，5，4，6，7】
     */
    public static int[] maxForce(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1;
        int[] res = new int[m];
        int l = 0;
        int r = k - 1;
        int index = 0;
        while (r < n) {
            int max = nums[l];
            for (int i = l + 1; i <= r; i++) {
                max = Math.max(max, nums[i]);
            }
            res[index++] = max;
            l++;
            r++;
        }
        return res;
    }

    public static int[] getMax(int[] nums, int k) {
        int length = nums.length;
        int newLen = length - k + 1;
        int[] res = new int[newLen];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.add(i);
            if (deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    public static int[] minForce(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1;
        int[] res = new int[m];
        int l = 0;
        int r = k - 1;
        int index = 0;
        while (r < n) {
            int min = nums[l];
            for (int i = l + 1; i <= r; i++) {
                min = Math.min(min, nums[i]);
            }
            res[index++] = min;
            l++;
            r++;
        }
        return res;
    }

    public static int[] getMin(int[] nums, int k) {
        int length = nums.length;
        int newLen = length - k + 1;
        int[] res = new int[newLen];
        int index = 0;
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i - k == deque.peekFirst()) {
                deque.pollFirst();
            }
            if (i >= k - 1) {
                // get 会抛异常，peek 返回null
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        // 窗口的边界问题
        int k = genRandomNum(n) + 1;
        int[] ans01 = maxForce(arr, k);
        int[] ans02 = getMax(arr, k);
        if (!Arrays.equals(ans01, ans02)) {
            System.out.printf("错误输入:%s,%d\n", Arrays.toString(arr), k);
            System.out.printf("错误输出:%s,%s\n", Arrays.toString(ans01), Arrays.toString(ans02));
            throw new RuntimeException();
        }

        int[] ans03 = minForce(arr, k);
        int[] ans04 = getMin(arr, k);
        if (!Arrays.equals(ans03, ans04)) {
            System.out.printf("错误输入:%s,%d\n", Arrays.toString(arr), k);
            System.out.printf("错误输出:%s,%s\n", Arrays.toString(ans03), Arrays.toString(ans04));
            throw new RuntimeException();
        }
    }
}
