package com.leetcode.sort;

import java.util.TreeSet;

/**
 * @author wcl
 * @date 11:22 上午 2020/9/3
 * <a href="">Contains Duplicate III</a>
 * TODO 基准排序
 */
public class ContainsDuplicateIII {
    /**
     * Given an array of integers,
     * find out whether there are two distinct indices i and j in the array
     * such that the absolute difference between nums[i] and nums[j] is at most t
     * and the absolute difference between i and j is at most k.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3,1], k = 3, t = 0
     * Output: true
     * Example 2:
     * <p>
     * Input: nums = [1,0,1,1], k = 1, t = 2
     * Output: true
     * Example 3:
     * <p>
     * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
     * Output: false
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null) {
            return false;
        }
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= i + k && j < length; j++) {
                long x = nums[j];
                long y = nums[i];
                long diff = x < y ? y - x : x - y;
                if (diff <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || k < 0 || t < 0) {
            return false;
        }
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long x = nums[i];
            if (i > k) {
                treeSet.remove((long) nums[i - k - 1]);
            }
            if (treeSet.contains(x)) {
                return true;
            }
            Long before = treeSet.lower(x);
            if (before != null && nums[i] - before <= t) {
                return true;
            }
            Long after = treeSet.higher(x);
            if (after != null && after - nums[i] <= t) {
                return true;
            }
            treeSet.add(x);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(~4 & 1);
    }
}
