package com.leetcode.dp;

/**
 * @author zms
 * @date 11:21 AM 2020/4/2
 * <a href="https://leetcode.com/problems/continuous-subarray-sum/">
 *     Continuous Subarray Sum</a>
 */
public class ContinuousSubarraySum {
    /**
     * Given a list of non-negative numbers and a target integer k,
     * write a function to check if the array has a continuous
     * subarray of size at least 2 that sums up to a multiple of k,
     * that is, sums up to n*k where n is also an integer.
     *
     * Example 1:
     *      Input: [23, 2, 4, 6, 7],  k=6
     *      Output: True
     *      Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     *
     * Example 2:
     *      Input: [23, 2, 6, 4, 7],  k=6
     *      Output: True
     *      Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
     *
     * Note:
     *      The length of the array won't exceed 10,000.
     *      You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
     */
    // 暴力求解
    public boolean checkSubarraySum1(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if(k == 0 && sum == 0) {
                    return true;
                }
                if(k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    //
//    public static boolean checkSubarraySum(int[] nums, int k) {
//
//    }
//
//    public static boolean checkSumArraysSumHelper(int[] nums, int k, int start, int end) {
//
//    }

    public static void main(String[] args) {
//        System.out.println(checkSubarraySum(new int[]{5,0,0}, 0));
    }
}
