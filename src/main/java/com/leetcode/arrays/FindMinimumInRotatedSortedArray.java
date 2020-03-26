package com.leetcode.arrays;

/**
 * @author wcl
 * @date 5:22 PM 2020/3/25
 * <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">
 *     Find Minimum in Rotated Sorted Array</a>
 */
public class FindMinimumInRotatedSortedArray {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     * Find the minimum element.
     * You may assume no duplicate exists in the array.
     *
     * Example 1:
     *      Input: [3,4,5,1,2]
     *      Output: 1
     *
     * Example 2:
     *      Input: [4,5,6,7,0,1,2]
     *      Output: 0
     */
    public int findMin1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            if(nums[left] <= nums[right]) {
                if(right != nums.length - 1) {
                    return nums[right + 1];
                } else {
                    return nums[0];
                }
            } else {
                right--;
            }
        }
        return -1;
    }
    public int findMin(int[] nums) {
        // If the list has just one element then return that element.
        if (nums.length == 1) {
            return nums[0];
        }

        // initializing left and right pointers.
        int left = 0, right = nums.length - 1;

        // if the last element is greater than the first element then there is no rotation.
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
        // Hence the smallest element is first element. A[0]
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        // Binary search way
        while (right >= left) {
            // Find the mid element
            int mid = left + (right - left) / 2;

            // if the mid element is greater than its next element then mid+1 element is the smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // if the mid element is lesser than its previous element then mid element is the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with elements
            // greater than nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value is somewhere to
                // the left
                right = mid - 1;
            }
        }
        return -1;
    }
}
