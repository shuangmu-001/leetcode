package com.leetcode.arrays;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 6:40 PM 2020/4/4
 * <a href="https://leetcode-cn.com/problems/move-zeroes/">
 *     Move Zeroes</a>
 */
public class MoveZeroes {
    /**
     * Given an array nums, write a function to move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.
     * Example:
     *      Input: [0,1,0,3,12]
     *      Output: [1,3,12,0,0]
     * Note:
     *      You must do this in-place without making a copy of the array.
     *      Minimize the total number of operations.
     */
    public static void moveZeroes(int[] nums) {
        int first = 0;
        int second = 0;
        for(int num : nums) {
            if(num != 0) {
                int temp = nums[first];
                nums[first] = 0;
                nums[second++] = temp;
            }

            first++;
        }
        Utils.printArrays(nums);
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});
    }
}
