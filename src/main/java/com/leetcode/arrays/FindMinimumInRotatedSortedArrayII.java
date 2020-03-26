package com.leetcode.arrays;

/**
 * @author wcl
 * @date 5:34 PM 2020/3/25
 * <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/">
 *     Find Minimum in Rotated Sorted Array II</a>
 */
public class FindMinimumInRotatedSortedArrayII {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     * Find the minimum element.
     * The array may contain duplicates.
     *
     * Example 1:
     *
     * Input: [1,3,5]
     * Output: 1
     * Example 2:
     *
     * Input: [2,2,2,0,1]
     * Output: 0
     * Note:
     *
     * This is a follow up problem to Find Minimum in Rotated Sorted Array.
     * Would allow duplicates affect the run-time complexity? How and why?
     */
    public static int findMin(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(min > nums[i]) {
                return nums[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{2,2,2,0,1}));
        System.out.println(findMin(new int[]{2,2,2,0,1,2,2,2,2}));
        System.out.println(findMin(new int[]{3,1,3}));
        System.out.println(findMin(new int[]{3,3}));
        System.out.println(findMin(new int[]{1,3}));
    }
}
