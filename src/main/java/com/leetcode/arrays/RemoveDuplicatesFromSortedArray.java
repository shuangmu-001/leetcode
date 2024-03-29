package com.leetcode.arrays;

/**
 * @author zms
 * @date 12:00 PM 2020/2/20
 * {@link "https://leetcode.com/problems/remove-duplicates-from-sorted-array/"}
 * @see RemoveElement
 */
public class RemoveDuplicatesFromSortedArray {
    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * Example 1:
     * Given nums = [1,1,2],
     * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
     * It doesn't matter what you leave beyond the returned length.
     * <p>
     * Example 2:
     * Given nums = [0,0,1,1,1,2,2,3,3,4],
     * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
     * It doesn't matter what values are set beyond the returned length.
     * <p>
     * Clarification:
     * Confused why the returned value is an integer but your answer is an array?
     * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
     * Internally you can think of this:
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeDuplicates(nums);
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len elements.
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     */
    public static int removeDuplicates01(int[] nums) {
        int length = nums.length;
        if (length <= 0) {
            return length;
        }
        int newLen = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[newLen] = nums[i];
                newLen++;
            }
        }
        return newLen;
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 0) {
            return n;
        }
        int l = 0;
        for (int r = l + 1; r < n; r++) {
            if (nums[l] != nums[r]) {
                nums[++l] = nums[r];
            }
        }
//        Utils.printArrays(nums);
        return l + 1;
    }

    public static void main(String[] args) {
//        System.out.println(removeDuplicates(new int[]{1, 1, 2}));
//        System.out.println(removeDuplicates(new int[]{1, 2}));
        System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }
}
