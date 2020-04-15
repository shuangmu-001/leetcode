package com.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 5:41 PM 2020/4/13
 * TODO <a href="https://leetcode.com/problems/contiguous-array/">
 *     Contiguous Array</a>
 */
public class ContiguousArray {
    /**
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     *
     * Example 1:
     * Input: [0,1]
     * Output: 2
     * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
     * Example 2:
     * Input: [0,1,0]
     * Output: 2
     * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
     * Note: The length of the given binary array will not exceed 50,000.
     */
    public static int findMaxLength(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }

        int max = 0;
        nums[0] = nums[0] == 0 ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] == 0 ? nums[i - 1] + 1 : nums[i - 1];
            int zeroNum = nums[i];
            if(zeroNum == (i + 1) / 2) {
                max = i + 1;
            } else {
                int index = (i & 1) == 0 ? 1 : 0;
                zeroNum = nums[i] - nums[index];
                while(index < i && zeroNum != (i - index + 1) / 2) {
                    zeroNum = nums[i] - nums[index];
                    zeroNum = nums[index] == 0 ? zeroNum + 1 : zeroNum;
                    index = index + 2;
                }
                max = Math.max(max, zeroNum * 2);
            }

        }

        return max;
    }

    public static int findMaxLength1(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int zeroNum = 0;
            int oneNum = 0;
            for (int j = i; j < nums.length; j++) {
                if(nums[j] == 0) {
                    zeroNum++;
                } else if(nums[j] == 1) {
                    oneNum++;
                }
                if(zeroNum == oneNum) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        System.out.println(findMaxLength(new int[]{0,1}) == 2);
//        System.out.println(findMaxLength(new int[]{0,1,0}) == 2);
//        System.out.println(findMaxLength(new int[]{0,1,0,1}) == 4);
//        System.out.println(findMaxLength(new int[]{0,1,0,0}) == 2);
        System.out.println(findMaxLength(new int[]{0,1,0,0,0,1,1,0}) == 6);
    }
}
