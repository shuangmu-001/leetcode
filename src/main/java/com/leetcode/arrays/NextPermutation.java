package com.leetcode.arrays;

import com.Utils;

/**
 * @author wcl
 * @date 11:27 AM 2020/5/11
 * <a href="https://leetcode.com/problems/next-permutation/">
 * Next Permutation</a>
 * @see com.leetcode.backtracking.Permutations
 * @see com.leetcode.backtracking.PermutationsII
 */
public class NextPermutation {
    /**
     * Implement next permutation,
     * which rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such arrangement is not possible,
     * it must rearrange it as the lowest possible order (ie, sorted in ascending order).
     * The replacement must be in-place and use only constant extra memory.
     *
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */

    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int length = nums.length;
        int index = length - 1;
        // 找到index - 1 之后是最大值，
        while(index > 0 && nums[index - 1] >= nums[index]) {
            index--;
        }
        int right = length - 1;
        if(index > 0) {
            // 1,2,3,6,4,3,2 -> 1,2,4,2,3,3,6
            // 找index - 1位置的下一个元素
            while (right > index && nums[index - 1] >= nums[right]) {
                right--;
            }
            swap(nums, index - 1, right);
        }

        int left = index;
        right = length - 1;
        while(left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }

        Utils.printArrays(nums);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        // 1,2,6,2,3,3
        nextPermutation(new int[]{1,2,3,6,3,2});
        // 5,5,2,3,4,7
        nextPermutation(new int[]{5,4,7,5,3,2});
        // 1,1
        nextPermutation(new int[]{1,1});
    }
}
