package com.leetcode.math;

/**
 * @author zms
 * @date 3:01 PM 2020/3/30
 * <a href="">
 *     Ugly Number</a>
 */
public class UglyNumber {
    /**
     * Write a program to check whether a given number is an ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     *
     * Example 1:
     *      Input: 6
     *      Output: true
     *      Explanation: 6 = 2 × 3
     *
     * Example 2:
     *      Input: 8
     *      Output: true
     *      Explanation: 8 = 2 × 2 × 2
     *
     * Example 3:
     *      Input: 14
     *      Output: false
     *      Explanation: 14 is not ugly since it includes another prime factor 7.
     *
     * Note:
     *      1 is typically treated as an ugly number.
     *      Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
     */
    public boolean isUgly(int num) {
        if(num <= 0) {
            return false;
        }
        while(num % 2 == 0 || num % 3 == 0 || num % 5 == 0) {
            if(num % 2 == 0) {
                num = num / 2;
            }
            if(num % 3 == 0) {
                num = num / 3;
            }
            if(num % 5 == 0) {
                num = num / 5;
            }
        }
        return num == 1;
    }
}
