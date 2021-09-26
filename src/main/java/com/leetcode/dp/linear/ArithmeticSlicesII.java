package com.leetcode.dp.linear;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * @date 2:22 下午 2020/9/9
 * <a href="https://leetcode.com/problems/arithmetic-slices-ii-subsequence/">
 * Arithmetic Slices II - Subsequence</a>
 */
public class ArithmeticSlicesII {
    /**
     * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
     * <p>
     * For example, these are arithmetic sequences:
     * <p>
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * The following sequence is not arithmetic.
     * <p>
     * 1, 1, 2, 5, 7
     * <p>
     * A zero-indexed array A consisting of N numbers is given.
     * A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
     * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic.
     * In particular, this means that k ≥ 2.
     * The function should return the number of arithmetic subsequence slices in the array A.
     * The input contains N integers. Every integer is in the range of -2^31 and 2^31-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 2^31-1.
     * <p>
     * <p>
     * Example:
     * <p>
     * Input: [2, 4, 6, 8, 10]
     * <p>
     * Output: 7
     * <p>
     * Explanation:
     * All arithmetic subsequence slices are:
     * [2,4,6]
     * [4,6,8]
     * [6,8,10]
     * [2,4,6,8]
     * [4,6,8,10]
     * [2,4,6,8,10]
     * [2,6,10]
     */
    public static int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        long res = 0;
        int length = A.length;
        Map<Long, Long>[] maps = new Map[length];

        for (int i = 0; i < A.length; i++) {
            maps[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                // 加减乘除都需要考虑溢出的问题
                long diff = (long) A[i] - (long) A[j];
                if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
                    continue;
                }
                long before = maps[i].getOrDefault(diff, 0L);
                long dp = maps[j].getOrDefault(diff, 0L);
                res += dp;
                maps[i].put(diff, before + dp + 1);
            }
        }

        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}) == 7);
        System.out.println(numberOfArithmeticSlices(new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}) == 1081012);
        System.out.println(numberOfArithmeticSlices(new int[]{2, 2, 2}) == 1);
        System.out.println(numberOfArithmeticSlices(new int[]{2, 2, 2, 2}) == 5);
        System.out.println(numberOfArithmeticSlices(new int[]{2, 2, 2, 2, 2}) == 16);
        System.out.println(numberOfArithmeticSlices(new int[]{0, 2000000000, -294967296}) == 0);
    }
}
