package com.leetcode.integers;

/**
 * @author wcl
 * @date 5:31 PM 2020/2/25
 * {@link "https://leetcode.com/problems/powx-n/"}
 */
public class Pow {
    /**
     * Implement pow(x, n), which calculates x raised to the power n (x^n).
     *
     * Example 1:
     *      Input: 2.00000, 10
     *      Output: 1024.00000
     *
     * Example 2:
     *      Input: 2.10000, 3
     *      Output: 9.26100
     *
     * Example 3:
     *      Input: 2.00000, -2
     *      Output: 0.25000
     *      Explanation: 2-2 = 1/22 = 1/4 = 0.25
     * Note:
     *      -100.0 < x < 100.0
     *      n is a 32-bit signed integer, within the range [−231, 231 − 1]
     */
    public static double myPow(double x, int n) {
        if(n < 0) {
            if(n == Integer.MIN_VALUE) {
                return 1 / (myPow(x, -(n + 1)) * x);
            }
            return 1 / myPow(x, -n);
        }
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return x;
        }
        if(x == 2.0) {
            return 1 << n;
        }
        double res = myPow(x, n / 2);
        if((n & 1) == 0) {
            return res * res;
        }
        return res * res * x;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.0000, 10));
    }

}
