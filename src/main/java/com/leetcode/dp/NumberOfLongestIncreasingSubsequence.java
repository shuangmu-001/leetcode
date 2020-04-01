package com.leetcode.dp;



import com.leetcode.Utils;

import java.util.Arrays;

import static com.leetcode.Utils.printTwoArrays;


/**
 * @author wcl
 * @date 10:40 AM 2020/1/3
 * TODO {@link "https://leetcode.com/problems/number-of-longest-increasing-subsequence/"}
 * @see LongestIncreasingSubsequence
 */
public class NumberOfLongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the number of longest increasing subsequence.
     * <p>
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 2
     * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
     * <p>
     * Example 2:
     * Input: [2,2,2,2,2]
     * Output: 5
     * Explanation: The length of longest continuous increasing subsequence is 1,
     * and there are 5 subsequences' length is 1, so output 5.
     * <p>
     * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
     *
     * @param nums an unsorted array of integers
     * @return num
     */
    public static int findNumberOfLIS1(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int result = 0;
        int[][] results = new int[length][length];
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                results[i][i] = results[i - 1][i];
            }
            for (int j = i + 1; j < length; j++) {
                if (nums[j] > nums[i]) {
                    if(i != 0) {
                        results[i][j] = Math.max(results[i][i] + 1, results[i - 1][j]);
                    } else {
                        results[i][j] = results[i][i] + 1;
                    }

                    result = Math.max(result, results[i][j]);
                } else {
                    if(i!=0) {
                        results[i][j] = results[i - 1][j];
                    }

                }
            }
        }
        printTwoArrays(results);
        return result + 1;
    }

    public static int findNumberOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int result = 0;
        int[] dp = new int[length];
        int[] count = new int[length];
        int num = 0;
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    if(dp[j] + 1 > dp[i]) {
                       dp[i] = dp[j] + 1;
                    } else if(dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    } else {
                        count[i] = 1;
                    }
                }
            }
            if(result < dp[i]) {
                result = dp[i];
                num = count[i];
            } else if(result == dp[i]) {
                num++;
            }
        }
        if(result == 1) {
            return length;
        }
        Utils.printArrays(count);
        return num;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums1) == 2);

        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println(findNumberOfLIS(nums2) == 5);

        int[] ints = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(findNumberOfLIS(ints) == 4);

        int[] int1 = {1, 11, 12, 2, 3, 4, 101};
        System.out.println(findNumberOfLIS(int1) == 1);

        int[] int2 = {1, 3, 6, 11, 7, 9, 4, 10, 5, 6};
        System.out.println(findNumberOfLIS(int2) == 1);

        int[] int3 = {-2, -1};
        System.out.println(findNumberOfLIS(int3) == 1);

        int[] int4 = {1, 2};
        System.out.println(findNumberOfLIS(int4) == 1);

        int[] int5 = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println(findNumberOfLIS(int5) == 2);

        int[] int6 = {11, 12, 13, 15, 5, 6, 7};
        System.out.println(findNumberOfLIS(int6) == 1);

        int[] int7 = {11, 12, 13, 14, 15, 6, 7, 8, 101, 18};
        System.out.println(findNumberOfLIS(int7) == 2);

        int[] int8 = {3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        System.out.println(findNumberOfLIS(int8) == 2);

        int[] int9 = {2, 15, 3, 7, 8, 6, 18};
        System.out.println(findNumberOfLIS(int9) == 1);

        int[] int10 = {4, 6, 7, 7};
        System.out.println(findNumberOfLIS(int10) == 2);

        int[] int11 = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        System.out.println(findNumberOfLIS(int11) == 10);
    }
}
