package com.leetcode.greedy;


/**
 * @author zms
 * @date 10:39 AM 2020/4/26
 * <a href="https://leetcode.com/problems/jump-game-ii/">
 *     Jump Game II</a>
 * @see JumpGame
 */
public class JumpGameII {
    /**
     * Given an array of non-negative integers,
     * you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     * Input: [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Note:
     * You can assume that you can always reach the last index.
     */
    public static int jump1(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        // dp[i] : 到达i的最小step
        // dp[i + 1] : 所有能到i+1的step的最小值+1
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                int index = j + nums[j];
                if(index >= i) {
                    if(dp[i] == 0) {
                        dp[i] = dp[j] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        return dp[length - 1];
    }

    public static int jump(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }
        int length = nums.length;
        if(length == 2 || nums[0] >= length - 1) {
            return 1;
        }
        int maxIndex = nums[0];
        int step = 1;
        int nextMaxIndex = maxIndex;
        for (int i = 1; i < length - 1; i++) {
            int index = i + nums[i];
            if(index >= length - 1) {
                return step + 1;
            }
            nextMaxIndex = Math.max(nextMaxIndex, index);
            if(i == maxIndex) {
                step++;
                maxIndex = nextMaxIndex;
            }

        }
        return step;
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{3,2,1}) == 1);
        System.out.println(jump(new int[]{2,1}) == 1);
        System.out.println(jump(new int[]{2,3,1,1,4}) == 2);
        System.out.println(jump(new int[]{5,9,3,2,1,0,2,3,3,1,0,0}) == 3);
        System.out.println(jump(new int[]{5,4,0,1,3,6,8,0,9,4,9,1,8,7,4,8}) == 3);
    }
}
