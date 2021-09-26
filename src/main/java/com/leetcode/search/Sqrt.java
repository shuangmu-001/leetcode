package com.leetcode.search;

/**
 * @author zms
 * @date 6:05 PM 2020/4/3
 * <a href="https://leetcode.com/problems/sqrtx/">
 *     Sqrt(x)</a>
 */
public class Sqrt {
    /**
     * Implement int sqrt(int x).
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     *
     * Example 1:
     *      Input: 4
     *      Output: 2
     *
     * Example 2:
     *      Input: 8
     *      Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     */
    public static int mySqrt(int x) {
        if(x <= 1) {
            return x;
        }
        int left = 0;
        int right = x;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            long target = (long)mid * mid;
            System.out.println(mid + " " + target);
            if(target == x) {
                return mid;
            } else if(target < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(100299229));
    }

}
