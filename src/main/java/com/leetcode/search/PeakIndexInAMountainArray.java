package com.leetcode.search;

/**
 * @author zms
 * @date 2:12 PM 2020/4/3
 * <a href="https://leetcode.com/problems/peak-index-in-a-mountain-array/">
 *     Peak Index in a Mountain Array</a>
 * 感觉题有问题，测试用例是求最大值的测试用例，不明白
 */
public class PeakIndexInAMountainArray {
    /**
     * Let's call an array A a mountain if the following properties hold:
     *
     * A.length >= 3
     * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
     * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
     *
     * Example 1:
     *
     * Input: [0,1,0]
     * Output: 1
     * Example 2:
     *
     * Input: [0,2,1,0]
     * Output: 1
     * Note:
     *      3 <= A.length <= 10000
     *      0 <= A[i] <= 10^6
     *      A is a mountain, as defined above.
     */
    public int peakIndexInMountainArray(int[] A) {
        int res = 0;
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if(A[i] > max) {
                res = i;
                max = A[i];
            }
        }
        return res;
    }
}
