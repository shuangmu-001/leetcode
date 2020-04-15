package com.leetcode.arrays;

/**
 * @author wcl
 * @date 3:27 PM 2020/4/13
 * <a href="https://leetcode.com/problems/merge-sorted-array/">
 *     Merge Sorted Array</a>
 */
public class MergeSortedArray {
    /**
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     *
     * Note:
     *
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * Example:
     *
     * Input:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * Output: [1,2,2,3,5,6]
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2 == null || nums2.length == 0) {
            return;
        }
        int index1 = m - 1;
        int index2 = n - 1;
        int index = nums1.length - 1;
        while(index2 >= 0) {
            if(index1 < 0) {
                nums1[index--] = nums2[index2--];
            }else if(nums1[index1] > nums2[index2]) {
                nums1[index--] = nums1[index1--];
            } else {
                nums1[index--] = nums2[index2--];
            }
        }
    }

}
