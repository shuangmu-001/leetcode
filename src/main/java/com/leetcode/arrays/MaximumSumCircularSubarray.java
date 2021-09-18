package com.leetcode.arrays;

import com.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wcl
 * @date 5:07 PM 2020/5/15
 * <a href="https://leetcode-cn.com/problems/maximum-sum-circular-subarray/">
 * Maximum Sum Circular Subarray</a>
 */
public class MaximumSumCircularSubarray {
    /**
     * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
     *
     * Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
     *
     * Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
     *
     *
     *
     * Example 1:
     *
     * Input: [1,-2,3,-2]
     * Output: 3
     * Explanation: Subarray [3] has maximum sum 3
     * Example 2:
     *
     * Input: [5,-3,5]
     * Output: 10
     * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
     * Example 3:
     *
     * Input: [3,-1,2,-1]
     * Output: 4
     * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
     * Example 4:
     *
     * Input: [3,-2,2,-3]
     * Output: 3
     * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
     * Example 5:
     *
     * Input: [-2,-3,-1]
     * Output: -1
     * Explanation: Subarray [-1] has maximum sum -1
     *
     *
     * Note:
     *
     * -30000 <= A[i] <= 30000
     * 1 <= A.length <= 30000
     */
    // TLE
    public static int maxSubarraySumCircular1(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                res = Math.max(res, sum);
                if(i == 0) {
                    map.put(j, res);
                }
                if(j == A.length - 1 ) {
                    res = Math.max(res, map.getOrDefault(i - 1, 0) + sum);
                }
            }
            res = Math.max(res, A[i]);
        }
        return res;
    }

    public static int maxSubarraySumCircular(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int sum = 0;
        int length = A.length;
        int[] maxNums = new int[length];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            sum += A[i];
            maxSum = Math.max(sum, maxSum);
            map.put(i, maxSum);
            if(i == 0) {
                maxNums[i] = A[i];
            } else {
                maxNums[i] = Math.max(A[i], maxNums[i - 1] + A[i]);
            }
            res = Math.max(res, maxNums[i]);
        }
        sum = 0;
        for (int i = length - 1; i > 0 ; i--) {
            sum += A[i];
            int max = map.getOrDefault(i - 1, 0);
            res = Math.max(res, sum);
            res = Math.max(res, sum + max);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{1,-2,3,-2}) == 3);
        System.out.println(maxSubarraySumCircular(new int[]{1,-1,3,-2,-1,4}) == 7);
        System.out.println(maxSubarraySumCircular(new int[]{4,-9,3,-2}) == 5);
        System.out.println(maxSubarraySumCircular(new int[]{2,-2,3,-3}) == 3);
        System.out.println(maxSubarraySumCircular(new int[]{-2,-3,-1}) == -1);

        //1173096
        System.out.println(maxSubarraySumCircular
                (Objects.requireNonNull(Utils.read("./src/main/resources/array.txt", 30000)).get(0)) == 1173096);
    }
}
