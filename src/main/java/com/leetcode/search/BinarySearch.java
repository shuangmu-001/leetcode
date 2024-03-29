package com.leetcode.search;

/**
 * @author zms
 * @date 11:54 AM 2020/4/3
 * <a href="https://leetcode.com/problems/binary-search/">
 *     Binary Search</a>
 */
public class BinarySearch {
    /**
     * Given a sorted (in ascending order) integer array nums of n elements and a target value,
     * write a function to search target in nums.
     * If target exists, then return its index, otherwise return -1.
     *
     * Example 1:
     *      Input: nums = [-1,0,3,5,9,12], target = 9
     *      Output: 4
     *      Explanation: 9 exists in nums and its index is 4
     *
     * Example 2:
     *      Input: nums = [-1,0,3,5,9,12], target = 2
     *      Output: -1
     *      Explanation: 2 does not exist in nums so return -1
     *
     *
     * Note:
     *      You may assume that all elements in nums are unique.
     *      n will be in the range [1, 10000].
     *      The value of each element in nums will be in the range [-9999, 9999].
     */
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9) == 4);
//        System.out.println(search(new int[]{9,12}, 9) == 0);
//        System.out.println(search(new int[]{-1,0,3,5,9}, 9) == 4);
//        System.out.println(search(new int[]{9}, 9) == 0);
//        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2) == -1);


//        System.out.println(search(new int[]{-1,0,3,5,9,12}, 11) == 4);
//        System.out.println(search(new int[]{-1,0,3,5,9}, 10) == 4);
//        System.out.println(search(new int[]{-1,0,3,5}, -2) == 4);
//        System.out.println(search(new int[]{9,12}, 9) == 0);
//        System.out.println(search(new int[]{-1,0,3,5,9}, 9) == 4);
//        System.out.println(search(new int[]{9}, 9) == 0);
//        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2) == -1);
    }
}
