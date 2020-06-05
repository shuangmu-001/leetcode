package com.leetcode.dp.fibonacci;

/**
 * @author wcl
 * @date 3:05 下午 2020/5/22
 * <a href="https://leetcode.com/problems/fibonacci-number/">
 * Fibonacci Number</a>
 */
public class FibonacciNumber {
    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence,
     * called the Fibonacci sequence, such that each number is the sum of the two preceding ones,
     * starting from 0 and 1. That is,
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), for N > 1.
     * Given N, calculate F(N).
     *
     * Example 1:
     *
     * Input: 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     * Example 2:
     *
     * Input: 3
     * Output: 2
     * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
     * Example 3:
     *
     * Input: 4
     * Output: 3
     * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
     *
     *
     * Note:
     *
     * 0 ≤ N ≤ 30.
     */
    public static int fib(int N) {
        if(N < 2) {
            return N;
        }
        int first = 0;
        int second = 1;
        for (int i = 2; i < N; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return first + second;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(fib(i));
        }

    }
}
