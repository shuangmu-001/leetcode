package com.leetcode.search;

/**
 * @author zms
 * @date 11:11 AM 2020/4/7
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">
 *     Search in Rotated Sorted Array</a>
 */
public class SearchInRotatedSortedArray {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * Example 1:
     *      Input: nums = [4,5,6,7,0,1,2], target = 0
     *      Output: 4
     *
     * Example 2:
     *      Input: nums = [4,5,6,7,0,1,2], target = 3
     *      Output: -1
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[left] == target) {
                return left;
            } else if(nums[right] == target) {
                return right;
            } else if(nums[mid] > nums[left]) {
                if(nums[mid] > target) {
                    if(nums[left] > target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                   left = mid + 1;
                }
            } else  {
                if(nums[mid] < target) {
                    if(nums[right] > target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(search(new int[]{7,0,1,2}, 0));
        System.out.println(search(new int[]{7,0,1,2,3,4,5,6}, 0));
        System.out.println(search(new int[]{1,2,3,4,5,6,7,8,9,10,11,19,0}, 18));
        System.out.println(search(new int[]{0,1,2,3,4,5,6,7,8,9,10,11,19}, 18));
        System.out.println(search(new int[]{1,2,3,4,5,6,7,8,9,10,11,19,0}, 20));
        System.out.println(search(new int[]{1,2,3,4,5,6,7,8,9,10,11,19,0}, 19));
    }
}
