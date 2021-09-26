package com.leetcode.bit;

/**
 * @author zms
 * @date 5:44 PM 2020/4/23
 * <a href="https://leetcode.com/problems/power-of-two/">
 *     Power of Two</a>
 */
public class PowerOfTwo {
    /**
     * Given an integer, write a function to determine if it is a power of two.
     *
     * Example 1:
     *
     * Input: 1
     * Output: true
     * Explanation: 20 = 1
     * Example 2:
     *
     * Input: 16
     * Output: true
     * Explanation: 24 = 16
     * Example 3:
     *
     * Input: 218
     * Output: false
     */
    public boolean isPowerOfTwo(int n) {
//        if(n == Integer.MIN_VALUE) {
//            return false;
//        }
//        return Integer.bitCount(n) == 1;
        if(n <= 0) {
            return false;
        }
        return ((n & (n - 1)) == 0);
    }
}
