package com.leetcode.arrays;

import com.leetcode.Utils;

import java.util.Arrays;

/**
 * @author wcl
 * @date 11:30 PM 2020/4/14
 * <a href="https://leetcode.com/problems/squares-of-a-sorted-array/">
 *     Squares of a Sorted Array</a>
 */
public class SquaresOfASortedArray {
    /**
     * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
     *
     *
     *
     * Example 1:
     *
     * Input: [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Example 2:
     *
     * Input: [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     *
     *
     * Note:
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A is sorted in non-decreasing order.
     */
    public static int[] sortedSquares(int[] A) {
        int index = Arrays.binarySearch(A, 0);
        int[] res = new int[A.length];
        if(index < 0) {
            index = ~index;
        }
        int left = index - 1;
        int right = index;
        int newIndex = 0;
        while(left >= 0 && right < A.length) {
            if(Math.abs(A[left]) < A[right]) {
                res[newIndex++] = Math.abs(A[left]) * Math.abs(A[left]);
                left--;
            } else {
                res[newIndex++] = A[right] * A[right];
                right++;
            }
        }
        while(left >= 0) {
            res[newIndex++] = Math.abs(A[left]) * Math.abs(A[left]);
            left--;
        }
        while(right < A.length) {
            res[newIndex++] = A[right] * A[right];
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        Utils.printArrays(sortedSquares(new int[]{-4,-1,0,3,10}));
        Utils.printArrays(sortedSquares(new int[]{-7,-3,2,3,11}));
        Utils.printArrays(sortedSquares(new int[]{0,2,3,11}));
        Utils.printArrays(sortedSquares(new int[]{2,3,11}));
        Utils.printArrays(sortedSquares(new int[]{-20,-13,-11}));
    }
}
