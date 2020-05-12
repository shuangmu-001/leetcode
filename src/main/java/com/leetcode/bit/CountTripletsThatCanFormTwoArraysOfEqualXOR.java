package com.leetcode.bit;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 6:36 PM 2020/5/12
 * <a href="https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/">
 * Count Triplets That Can Form Two Arrays Of Equal XOR</a>
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {
    /**
     * Given an array of integers arr.
     *
     * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
     *
     * Let's define a and b as follows:
     *
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * Note that ^ denotes the bitwise-xor operation.
     *
     * Return the number of triplets (i, j and k) Where a == b.
     *
     *  
     *
     * Example 1:
     *
     * Input: arr = [2,3,1,6,7]
     * Output: 4
     * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
     * Example 2:
     *
     * Input: arr = [1,1,1,1,1]
     * Output: 10
     * Example 3:
     *
     * Input: arr = [2,3]
     * Output: 0
     * Example 4:
     *
     * Input: arr = [1,3,5,7,9]
     * Output: 3
     * Example 5:
     *
     * Input: arr = [7,11,12,9,5,2,7,17,22]
     * Output: 8
     *  
     *
     * Constraints:
     *
     * 1 <= arr.length <= 300
     * 1 <= arr[i] <= 10^8
     */
    public static int countTriplets(int[] arr) {
        int length = arr.length;
        if(length < 2) {
            return 0;
        }
        int[] left = new int[length];

        int res = 0;
        for (int i = 0; i < length - 1; i++) {
            left[i] = arr[i];
            for (int j = i + 1; j < length; j++) {
                left[j] = left[j - 1] ^ arr[j];
                if(left[j] == 0) {
                    res += (j - i);
                }
            }
            Utils.printArrays(left);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2,3,1,6,7}));
        System.out.println(countTriplets(new int[]{1,1,1,1,1}));
    }
}
