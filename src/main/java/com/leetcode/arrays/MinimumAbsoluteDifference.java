package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 11:30 AM 2020/1/15
 * {@link "https://leetcode.com/problems/minimum-absolute-difference/"}
 */
public class MinimumAbsoluteDifference {
    /**
     * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
     * <p>
     * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
     * <p>
     * a, b are from arr
     * a < b
     * b - a equals to the minimum absolute difference of any two elements in arr
     * <p>
     * <p>
     * Example 1:
     * Input: arr = [4,2,1,3]
     * Output: [[1,2],[2,3],[3,4]]
     * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
     * <p>
     * Example 2:
     * Input: arr = [1,3,6,10,15]
     * Output: [[1,3]]
     * <p>
     * Example 3:
     * Input: arr = [3,8,-10,23,19,-4,-14,27]
     * Output: [[-14,-10],[19,23],[23,27]]
     * <p>
     * Constraints:
     * 2 <= arr.length <= 10^5
     * -10^6 <= arr[i] <= 10^6
     *
     * @param arr an array of distinct integers
     * @return
     */
    public static List<List<Integer>> minimumAbsDifference1(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        List<List<Integer>> results = new ArrayList<>();
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int diff = arr[i] - arr[i - 1];

            if (min > diff) {
                min = diff;
                results = new ArrayList<>();
            }
            if (min == diff) {
                List<Integer> result = new ArrayList<>();
                result.add(arr[i - 1]);
                result.add(arr[i]);
                results.add(result);
            }
        }
        return results;
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        return new java.util.AbstractList<List<Integer>>() {

            int buffer[];
            int size = 0;
            int min;
            boolean init = false;
            // 缺少范围检查
            @Override
            public List<Integer> get(int index) {
                if (!init) {
                    init();
                }
                return Arrays.asList(buffer[index] - min, buffer[index]);
            }

            @Override
            public int size() {
                if (!init) {
                    init();
                }
                return size;
            }

            private void init() {
                Arrays.sort(arr);
                buffer = new int[arr.length];
                min = Integer.MAX_VALUE;
                size = 0;
                for (int i = 1; i < arr.length; i++) {
                    int abs = arr[i] - arr[i - 1];
                    if (abs == min) {
                        buffer[size++] = arr[i];
                    } else if (abs < min) {
                        size = 0;
                        buffer[size++] = arr[i];
                        min = abs;
                    }
                }
                init = true;
            }
        };
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 2, 1, 3};
        System.out.println(minimumAbsDifference(arr1));
        int[] arr2 = {1, 3, 6, 10, 15};
        System.out.println(minimumAbsDifference(arr2));
        int[] arr3 = {3, 8, -10, 23, 19, -4, -14, 27};
        System.out.println(minimumAbsDifference(arr3));
    }

}
