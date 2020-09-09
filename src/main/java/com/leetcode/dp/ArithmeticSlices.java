package com.leetcode.dp;

/**
 * @author wcl
 * @date 11:46 上午 2020/9/9
 * <a href="https://leetcode.com/problems/arithmetic-slices/">
 *     Arithmetic Slices</a>
 */
public class ArithmeticSlices {
    /**
     * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
     *
     * For example, these are arithmetic sequences:
     *
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * The following sequence is not arithmetic.
     *
     * 1, 1, 2, 5, 7
     *
     * A zero-indexed array A consisting of N numbers is given.
     * A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
     *
     * A slice (P, Q) of the array A is called arithmetic if the sequence:
     * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
     *
     * The function should return the number of arithmetic slices in the array A.
     *
     *
     * Example:
     *
     * A = [1, 2, 3, 4]
     *
     * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     */
    public int numberOfArithmeticSlices1(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int start = 0;
        int diff = A[1] - A[0];
        int result = 0;
        for (int i = 2; i < A.length; i++) {
            int newDiff = A[i] - A[i - 1];
            if(newDiff != diff) {
                int len = i - start;
                if(len >= 3) {
                    result += (1 + len - 2) * (len - 2) / 2;
                }
                start = i - 1;
                diff = newDiff;
            } else if(i == A.length - 1) {
                int len = i - start + 1;
                if(len >= 3) {
                    result += (1 + len - 2) * (len - 2) / 2;
                }
            }
        }
        return result;
    }

    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int result = 0;
        int dp = 0;

        for (int i = 2; i < A.length; i++) {
            // 包含A[i]的等差序列有dp个
            if(A[i - 1] - A[i - 2] == A[i] - A[i - 1]) {
                dp += 1;
                result += dp;
            } else {
                dp = 0;
            }
        }
        return result;
    }
}
