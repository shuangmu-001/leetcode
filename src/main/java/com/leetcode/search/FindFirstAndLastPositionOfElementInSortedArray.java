package com.leetcode.search;


import java.util.Arrays;

/**
 * @author wcl
 * @date 2:39 PM 2020/4/23
 * <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">
 *     Find First and Last Position of Element in Sorted Array</a>
 * 二分法 有相同的数，最左边和最右边
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * Given an array of integers nums sorted in ascending order,
     * find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */
    public static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[2];
        int mid = left;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                break;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(left > right) {
            return new int[]{-1, -1};
        }
        int before = mid;
        while(left <= before) {
            int newMid = left + (before - left) / 2;
            if (nums[newMid] == target) {
                before = newMid - 1;
            } else {
                left = newMid + 1;
            }
        }
        res[0] = left;
        before = mid;
        while(before <= right) {
            int newMid = before + (right - before) / 2;
            if (nums[newMid] == target) {
                before = newMid + 1;
            } else {
                right = newMid - 1;
            }
        }
        res[1] = right;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 11, 12, 14, 15, 15, 16, 18, 19, 20}, 18)));
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10, 11, 12, 14, 15, 15, 16, 18, 19, 20}, 20)));
        System.out.println(Integer.MAX_VALUE);
    }
}
