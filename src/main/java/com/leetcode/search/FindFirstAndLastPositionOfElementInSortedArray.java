package com.leetcode.search;


/**
 * @author wcl
 * @date 2:39 PM 2020/4/23
 * TODO <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">
 *     Find First and Last Position of Element in Sorted Array</a>
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
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[2];
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[left] < target) {
                if(nums[left + 1] == target) {
                    res[0] = left + 1;
                }
            }
            if(nums[right] > target) {
                 if(nums[right + 1] == target) {
                     res[1] = right + 1;
                 }
            }
            if(nums[mid] == target) {

            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
