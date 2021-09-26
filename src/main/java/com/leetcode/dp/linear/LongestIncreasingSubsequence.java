package com.leetcode.dp.linear;

import java.util.*;

import static com.Utils.printTwoArrays;

/**
 * @author zms
 * @date 6:15 PM 2019/12/26
 * {@link "https://leetcode.com/problems/longest-increasing-subsequence/"}
 * @see NumberOfLongestIncreasingSubsequence
 */
public class LongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     * <p>
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * <p>
     * Note:
     * There may be more than one LIS combination, it is only necessary for you to return the length.
     * <p>
     * Your algorithm should run in O(n^2) complexity.
     * <p>
     * Follow up: Could you improve it to O(n log n) time complexity?
     */
    public static int lengthOfLIS1(int[] nums) {

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

    public static int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int result = 1;
        int min = nums[length - 1];
        if(nums[length - 2] < nums [length - 1]) {
            min = nums[length - 2];
            result = 2;
        }

        for (int i = length - 3; i >= 0; i--) {

            if(nums[i] < min) {
                min = nums[i];
                result++;
            } else {
                int current = 1;
                int before = 0;
                for (int j = i + 1; j < length; j++) {
                    int h = nums[j] - nums[i];
                    if(h > before) {
                        before = h;
                        current++;
                    }

                }
                if(current >= result) {
                    min = nums[i];
                    result = current;
                }
            }
        }
        return result;
    }

    public static int lengthOfLIS3(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int result = 1;
        int min = nums[length - 1];

        for (int i = length - 2; i >= 0; i--) {

            if(nums[i] < min) {
                min = nums[i];
                result++;
            } else  {
                int current = 1;
                int before = 0;
                for (int j = i + 1; j < length; j++) {
                    int h = nums[j] - nums[i];
                    if(h > before) {
                        before = h;
                        current++;
                    }
                    if(current >= result) {
                        min = nums[i];
                        result = current;
                    }
                }

            }
        }
        return result;
    }
    // dp
    public static int lengthOfLIS4(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        int result = 1;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    // O(nlogn）
    public static int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return length;
        }
        List<Integer> lIS = new ArrayList<>();
        lIS.add(nums[0]);
        for (int i = 1; i < length; i++) {
            int index = Collections.binarySearch(lIS, nums[i]);
            if(index < 0) {
                if(~index == lIS.size()) {
                    lIS.add(nums[i]);
                } else {
                    lIS.set(~index, nums[i]);
                }
            }
        }
        return lIS.size();
    }

    public static void main(String[] args) {

        int[] ints = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(ints));
        System.out.println(lengthOfLIS(ints) == 4);

        int[] int1 = {1, 11, 12, 2, 3, 4, 101};
        System.out.println(lengthOfLIS(int1) == 5);

        int[] int2 = {1, 3, 6, 11, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(int2) == 6);

        int[] int3 = {-2, -1};
        System.out.println(lengthOfLIS(int3) == 2);

        int[] int4 = {2, 1};
        System.out.println(lengthOfLIS(int4) == 1);

        int[] int5 = { 10,22,9,33,21,50,41,60,80};
        System.out.println(lengthOfLIS(int5) == 6);

        int[] int6 = {11, 12, 13, 15, 5, 6, 7};
        System.out.println(lengthOfLIS(int6) == 4);

        int[] int7 = {11,12,13,14,15,6,7,8,101,18};
        System.out.println(lengthOfLIS(int7) == 6);

        int[] int8 = {3,5,6,2,5,4,19,5,6,7,12};
        System.out.println(lengthOfLIS(int8) == 6);

        int[] int9 = {2,15,3,7,8,6,18};
        System.out.println(lengthOfLIS(int9));
    }

}
