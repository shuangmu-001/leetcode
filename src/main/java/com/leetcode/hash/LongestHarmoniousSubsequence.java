package com.leetcode.hash;

import java.util.*;

/**
 * @author zms
 * @date 11:01 上午 2021/2/5
 * <a href="https://leetcode.com/problems/longest-harmonious-subsequence/">
 * Longest Harmonious Subsequence</a>
 */
public class LongestHarmoniousSubsequence {
    /**
     * We define a harmonious array as an array where the difference between
     * its maximum value and its minimum value is exactly 1.
     * Given an integer array nums, return the length of its
     * longest harmonious subsequence among all its possible subsequences.
     * A subsequence of array is a sequence that can be derived
     * from the array by deleting some or no elements without changing the order of the remaining elements.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,2,2,5,2,3,7]
     * Output: 5
     * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
     * Example 2:
     * <p>
     * Input: nums = [1,2,3,4]
     * Output: 2
     * Example 3:
     * <p>
     * Input: nums = [1,1,1,1]
     * Output: 0
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 2 * 10^4
     * -10^9 <= nums[i] <= 10^9
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
            if (map.containsKey(num - 1)) {
                res = Math.max(res, map.get(num) + map.get(num - 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestHarmoniousSubsequence lhs = new LongestHarmoniousSubsequence();
        System.out.println(5 == lhs.findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(2 == lhs.findLHS(new int[]{1, 2, 3, 4}));
        System.out.println(0 == lhs.findLHS(new int[]{1, 1, 1, 1, 1}));
        System.out.println(2 == lhs.findLHS(new int[]{1, 4, 1, 3, 1, -14, 1, -13}));
        Map<Integer, Integer> map = new WeakHashMap<>();
        map.put(null, 1);
        System.out.println(map);
    }
}
