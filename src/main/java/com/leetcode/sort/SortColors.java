package com.leetcode.sort;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 2:26 下午 2020/6/12
 * <a href="https://leetcode.com/problems/sort-colors/">
 *     Sort Colors</a>
 */
public class SortColors {
    /**
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     *
     * Example:
     *
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * Follow up:
     *
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
     * Could you come up with a one-pass algorithm using only constant space?
     */
    public static void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int index = 0;
        while(index >= left && right >= index) {
            if(nums[index] == 0 && index != left) {
                swap(nums, index, left);
                left++;
            } else if(nums[index] == 2 && index != right) {
                swap(nums, index, right);
                right--;
            } else {
                index++;
            }
        }
        Utils.printArrays(nums);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        sortColors(new int[]{2,0,2,1,1,0});
    }
}
