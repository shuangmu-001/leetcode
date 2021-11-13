package com.question.day10;

import com.Test;
import com.Utils;

/**
 * <a href="https://leetcode.com/problems/jump-game-ii/">jump game ii</a>
 *
 * @author zms
 * @date 3:02 下午 2021/9/29
 */
public class Code01JumpGameII implements Test {
    /**
     * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
     * <p>
     * 示例 1:
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
     * <p>
     * 示例 2:
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     * <p>
     * 提示:
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 1000
     */
    public int jump01(int[] nums) {
        if (nums == null || nums.length <= 1 || nums[0] == 0) {
            return 0;
        }
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            int end = Math.min(i + nums[i], length - 1);
            for (int j = i + 1; j <= end; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[i] + 1;
                } else {
                    dp[j] = Math.min(dp[i] + 1, dp[j]);
                }
            }
        }
        return dp[length - 1];
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1 || nums[0] == 0) {
            return 0;
        }
        int ans = 1;
        int length = nums.length;
        int cur = nums[0];
        int next = nums[0];
        for (int i = 1; i < length; i++) {
            if (i > cur) {
                ans++;
                cur = next;
            }
            next = Math.max(next, nums[i] + i);
        }
        return ans;
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int jump = jump(arr);
        int jump01 = jump01(arr);
        if (jump != jump01) {
            Utils.printArrays(arr);
            throw new RuntimeException();
        }
    }
}
