package com.leetcode.integers;

/**
 * @author wcl
 * @date 5:38 PM 2020/2/20
 * {@link "https://leetcode.com/problems/divide-two-integers/"}
 * 逻辑运算实现加减乘除
 */
public class DivideTwoIntegers {
    /**
     * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
     * Return the quotient after dividing dividend by divisor.
     * The integer division should truncate toward zero.
     * <p>
     * Example 1:
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * <p>
     * Example 2:
     * Input: dividend = 7, divisor = -3
     * Output: -2
     * <p>
     * Note:
     * Both dividend and divisor will be 32-bit signed integers.
     * The divisor will never be 0.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
     * For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.
     */
    public static int divide(int dividend, int divisor) {

        if (dividend == 0 || divisor == 1) {
            return dividend;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }
        int flag = dividend == Integer.MIN_VALUE ? 1 : 0;
        int a = dividend < 0 ? (~dividend) + 1 : dividend;
        int b = divisor < 0 ? (~divisor) + 1 : divisor;
        a = a < 0 ? ~a : a;
        if (a < b) {
            return 0;
        }
        int quotient = 0;
        if (b == 1) {
            quotient = a;
        } else {
            quotient = divideUnSign(a, b, quotient, flag);
        }

        if ((dividend ^ divisor) < 0) {
            quotient = (~quotient) + 1;
        }
        return quotient;
    }

    public static int divideUnSign(int dividend, int divisor, int quotient, int flag) {

        if (dividend < divisor) {

            return flag + dividend == divisor ? quotient + 1 : quotient;
        }
        if (dividend == divisor) {
            return quotient + 1;
        }
        int sum = 0;
        int count = 0;
        while (sum < dividend && sum >= 0) {
            count++;
            sum = divisor << count;
            if (sum == dividend) {
                return quotient + (1 << count);
            }
        }

        sum = divisor << (count - 1);
        quotient += (1 << (count - 1));
        return divideUnSign(dividend - sum, divisor, quotient, flag);

    }

    public static void main(String[] args) {
//        System.out.println(divide(12, 3));
//        System.out.println(divide(7, -3));
        System.out.println(divide(Integer.MIN_VALUE, -3) == 715827882);

//        715827882
//        1610612736

//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(-2147483648 / -1);
//        System.out.println(Integer.MIN_VALUE >> 1);
//
//        System.out.println(~Integer.MIN_VALUE);
    }

    /**
     * 1 + 1 = 0   -->  1 ^ 1 = 0
     * 1 + 0 = 1   -->  1 ^ 0 = 1
     * 0 + 1 = 1   -->  0 ^ 1 = 1
     * 0 + 0 = 0   -->  0 ^ 0 = 0
     * <p>
     * 1 + 1 = 10   -->  (1 & 1) << 1 = 10
     * 1 + 0 = 1    -->  (1 & 0) << 1 = 0
     * 0 + 1 = 1    -->  (0 & 1) << 1 = 0
     * 0 + 0 = 0    -->  (0 & 0) << 1 = 0
     */
    public static int sum(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static int subtract(int a, int b) {
        int subtractor = sum(~b, 1);
        return sum(a, subtractor);
    }

    public static int multiply(int a, int b) {
        int multiplicand = a < 0 ? sum(~a, 1) : a;
        int multiplier = b < 0 ? sum(~b, 1) : b;
        int product = 0;
        int count = 0;
        while (count < multiplier) {
            product = sum(multiplicand, product);
            count = sum(count, 1);
        }
        if ((a ^ b) < 0) {
            product = sum(~product, 1);
        }
        return product;
    }
}
