package com.leetcode.medianOfTwoSortedArrays;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        int total = len1 + len2;
        int medIndex = total / 2;
        int[] nums = new int[medIndex + 1];
        int j = 0;
        int l = 0;
        for(int i = 0; i <= medIndex; i++) {
            if(len1 <= j) {
                nums[i] = nums2[l];
                l++;
            } else if(len2 <= l) {
                nums[i] = nums1[j];
                j++;
            } else if(nums1[j] < nums2[l]) {
                nums[i] = nums1[j];
                j++;
            } else {
                nums[i] = nums2[l];
                l++;
            }
        }
        if(total % 2 == 0) {
            return ((double) (nums[medIndex] + nums[medIndex - 1]))/2;
        } else {
            return (double) nums[medIndex];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2) == 2.0);
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(solution.findMedianSortedArrays(nums3, nums4) == 2.5);
    }

}
