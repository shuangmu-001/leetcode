package com.leetcode.greedy;

/**
 * @author wcl
 * @date 9:22 PM 2020/4/25
 * <a href="https://leetcode.com/problems/jump-game/">
 *     Jump Game</a>
 */
public class JumpGame {
    /**
     * Given an array of non-negative integers,
     * you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     *
     * Example 1:
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Example 2:
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     *              jump length is 0, which makes it impossible to reach the last index.
     */
    static boolean res = false;
    public static boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }
        canJumpHelper(nums, 0);
        return res;
    }

    public static void canJumpHelper(int[] nums, int index) {
        if(index >= nums.length - 1 || res) {
            res = true;
            return;
        }
        for (int i = nums[index]; i > 0; i--) {
            if(i + index == nums.length - 1) {
                res = true;
                return;
            }
            canJumpHelper(nums, index + i);
        }
    }

    public static boolean canJump1(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return true;
        }
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == 0 && max <= i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJump1(new int[]{2,3,1,1,4}));
        System.out.println(canJump1(new int[]{3,2,1,0,4}));
    }
}
