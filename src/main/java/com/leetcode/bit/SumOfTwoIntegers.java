package com.leetcode.bit;

/**
 * @author wcl
 * @date 5:36 PM 2020/4/23
 * TODO <a href="https://leetcode.com/problems/sum-of-two-integers/">
 * Sum of Two Integers</a>
 */
public class SumOfTwoIntegers {
    /**
     * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
     * <p>
     * Example 1:
     * <p>
     * Input: a = 1, b = 2
     * Output: 3
     * Example 2:
     * <p>
     * Input: a = -2, b = 3
     * Output: 1
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a;
    }
}
