package com.leetcode.arrays;

import java.util.Deque;
import java.util.TreeMap;

/**
 * @author wcl
 * @date 11:58 AM 2020/5/7
 * <a href="https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/">
 *     Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit</a>
 * TODO 滑动窗口最大值和最小值
 */
public class LongestSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    /**
     * Given an array of integers nums and an integer limit,
     * return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.
     * In case there is no subarray satisfying the given condition return 0.
     *
     *
     * Example 1:
     * Input: nums = [8,2,4,7], limit = 4
     * Output: 2
     * Explanation: All subarrays are:
     * [8] with maximum absolute diff |8-8| = 0 <= 4.
     * [8,2] with maximum absolute diff |8-2| = 6 > 4.
     * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
     * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
     * [2] with maximum absolute diff |2-2| = 0 <= 4.
     * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
     * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
     * [4] with maximum absolute diff |4-4| = 0 <= 4.
     * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
     * [7] with maximum absolute diff |7-7| = 0 <= 4.
     * Therefore, the size of the longest subarray is 2.
     *
     * Example 2:
     * Input: nums = [10,1,2,4,7,2], limit = 5
     * Output: 4
     * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
     *
     * Example 3:
     * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
     * Output: 3
     *
     * Constraints:
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= limit <= 10^9
     */
    public static int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int right = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.merge(nums[0], 1, Integer::sum);
        int length = nums.length;
        int res = 1;
        while(left < length && right < length) {
            int max = treeMap.lastKey();
            int min = treeMap.firstKey();
            if(max - min > limit) {
                Integer count = treeMap.get(nums[left]);
                if(count > 1) {
                    treeMap.merge(nums[left], -1, Integer::sum);
                } else {
                    treeMap.remove(nums[left]);
                }
                res = Math.max(res, right - left);
                left++;
            } else if(right < length - 1){
                treeMap.merge(nums[++right], 1, Integer::sum);
            } else {
                res = Math.max(res, right - left + 1);
                right++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{8,2,4,7}, 4));
        System.out.println(longestSubarray(new int[]{10,1,2,4,7,2}, 5));
        System.out.println(longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
    }
}
