package com.question.day04;

import com.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * <a href="https://leetcode.com/problems/closest-subsequence-sum/">
 * Closest Subsequence Sum</a>
 *
 * @author wcl
 * @date 8:31 下午 2021/9/19
 */
public class Code07ClosestSubsequenceSum implements Test {
    /**
     * You are given an integer array nums and an integer goal.
     * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
     * That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
     * Return the minimum possible value of abs(sum - goal).
     * <p>
     * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [5,-7,3,5], goal = 6
     * Output: 0
     * Explanation: Choose the whole array as a subsequence, with a sum of 6.
     * This is equal to the goal, so the absolute difference is 0.
     * Example 2:
     * <p>
     * Input: nums = [7,-9,15,-2], goal = -5
     * Output: 1
     * Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
     * The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
     * Example 3:
     * <p>
     * Input: nums = [1,2,3], goal = -7
     * Output: 7
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 40
     * -10^7 <= nums[i] <= 10^7
     * -10^9 <= goal <= 10^9
     */
    private static int[] l = new int[1 << 20];
    private static int[] r = new int[1 << 20];

    public int minAbsDifference1(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return Math.abs(goal);
        }
        int length = nums.length;
        int lLen = process1(nums, 0, length >> 1, 0, 0, l);
        int rLen = process1(nums, length >> 1, length, 0, 0, r);
        Arrays.sort(l, 0, lLen);
        Arrays.sort(r, 0, rLen--);
        int ans = Math.abs(goal);
        for (int i = 0; i < lLen; i++) {
            int rest = goal - l[i];

            while (rLen > 0 && Math.abs(rest - r[rLen - 1]) <= Math.abs(rest - r[rLen])) {
                rLen--;
            }
            ans = Math.min(ans, Math.abs(rest - r[rLen]));
            if(ans == 0) {
                return ans;
            }
        }
        return ans;
    }

    public int process1(int[] nums, int start, int end, int sum, int fill, int[] sums) {
        if (start == end) {
            sums[fill++] = sum;
        } else {
            // 不选当前节点
            fill = process1(nums, start + 1, end, sum, fill, sums);
            // 选当前节点
            fill = process1(nums, start + 1, end, sum + nums[start], fill, sums);
        }
        return fill;
    }


    public int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return Math.abs(goal);
        }
        int length = nums.length;
        TreeSet<Integer> lSet = new TreeSet<>();
        TreeSet<Integer> rSet = new TreeSet<>();
        process(nums, 0, length >> 1, 0, lSet);
        process(nums, length >> 1, length, 0, rSet);
        int ans = Math.abs(goal);
        for (Integer i : lSet) {
            int rest = goal - i;
            Integer h = rSet.ceiling(rest);
            h = h == null ? rSet.last() : h;
            Integer d = rSet.floor(rest);
            d = d == null ? rSet.first() : d;
            ans = Math.min(ans, Math.min(Math.abs(rest - h), Math.abs(rest - d)));
            if (ans == 0) {
                return ans;
            }
        }

        return ans;
    }

    public void process(int[] nums, int start, int end, int sum, TreeSet<Integer> set) {
        if (start == end) {
            set.add(sum);
        } else {
            // 不选当前节点
            process(nums, start + 1, end, sum, set);
            // 选当前节点
            process(nums, start + 1, end, sum + nums[start], set);
        }
    }


}
