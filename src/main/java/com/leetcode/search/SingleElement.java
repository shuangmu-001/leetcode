package com.leetcode.search;

/**
 * @author wcl
 * @date 3:06 PM 2020/5/12
 * <a href="https://leetcode.com/problems/single-element-in-a-sorted-array/">
 * Single Element in a Sorted Array</a>
 */
public class SingleElement {
    /**
     * You are given a sorted array consisting of only integers where every element appears exactly twice,
     * except for one element which appears exactly once. Find this single element that appears only once.
     *
     *  Example 1:
     *
     * Input: [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * Example 2:
     *
     * Input: [3,3,7,7,10,11,11]
     * Output: 10
     *
     * Note: Your solution should run in O(log n) time and O(1) space.
     */
    public static int singleNonDuplicate(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while(right - left > 0) {
            int mid = left + (right - left) / 2;
            if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            } else if(nums[mid] == nums[mid - 1]) {
                if(((mid - left + 1) & 1) == 0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else if(nums[mid] == nums[mid + 1]) {
                if(((right - mid + 1) & 1) == 0) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1,1,2}));
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3}));
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8,9,9,11,11}));
        System.out.println(singleNonDuplicate(new int[]{1,1,2,2,3,4,4,8,8,9,9}));
        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4}));

        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4,8,8}));
        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4,8,8,9,9}));
        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4,8,8,9,9,10,10}));
        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4,8,8,9,9,10,10,11,11}));
        System.out.println(singleNonDuplicate(new int[]{1,2,2,3,3,4,4,8,8,9,9,10,10,11,11,12,12}));

    }
}
