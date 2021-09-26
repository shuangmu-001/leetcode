package com.leetcode.search;

/**
 * @author zms
 * @date 3:17 PM 2020/4/7
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array-ii/">
 *     Search in Rotated Sorted Array II</a>
 */
public class SearchInRotatedSortedArrayII {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
     *
     * You are given a target value to search. If found in the array return true, otherwise return false.
     *
     * Example 1:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     * Example 2:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 3
     * Output: false
     * Follow up:
     *
     * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
     * Would this affect the run-time complexity? How and why?
     * 有序数组被打乱了
     */
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target || nums[left] == target || nums[right] == target) {
                return true;
                // 说明左边有序
            } else if(nums[mid] > nums[left]) {
                if(nums[mid] < target || nums[left] > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
                // 右边有序
            } else if(nums[mid] < nums [right]) {
                if(nums[mid] > target || nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] == nums[left]) {
                left ++;
            } else if(nums[mid] == nums[right]) {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(!search(new int[]{2,5,6,0,0,1,2}, 3));
//        System.out.println(search(new int[]{2,5,6,0,0,1,2}, 0));
//        System.out.println(!search(new int[]{7,1,1,2}, 0));
//        System.out.println(search(new int[]{7,8,8,0}, 0));
//        System.out.println(search(new int[]{0}, 0));
//        System.out.println(search(new int[]{2,0,0,1,2}, 0));
        System.out.println(search(new int[]{1,3,1,1,1}, 3));
        System.out.println(search(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,4,0,0,0,0,0,}, 3));
        System.out.println(!search(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,}, 3));
    }
}
