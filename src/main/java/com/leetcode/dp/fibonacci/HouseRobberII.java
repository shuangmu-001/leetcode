package com.leetcode.dp.fibonacci;

/**
 * @author zms
 * @date 11:02 AM 2020/3/24
 * <a href="https://leetcode.com/problems/house-robber-ii/">
 * House Robber II</a>
 * @see HouseRobber
 */
public class HouseRobberII {
    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
     * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
     * Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given a list of non-negative integers representing the amount of money of each house,
     * determine the maximum amount of money you can rob tonight without alerting the police.
     * <p>
     * Example 1:
     * Input: [2,3,2]
     * Output: 3
     * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
     * because they are adjacent houses.
     * <p>
     * Example 2:
     * Input: [1,2,3,1]
     * Output: 4
     * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
     * Total amount you can rob = 1 + 3 = 4.
     * 从0到n-2的最大值 和 从1到n-1的最大值
     */
    public int rob01(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[][] dp = new int[2][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j < nums.length - 1 + i; j++) {
                if (j == 0) {
                    dp[i][0] = nums[j];
                } else if (j == 1) {
                    dp[i][1] = Math.max(nums[j], dp[i][0]);
                } else {
                    int temp = dp[i][1];
                    dp[i][1] = Math.max(dp[i][0] + nums[j], dp[i][1]);
                    dp[i][0] = temp;
                }
            }
        }

        return Math.max(dp[0][1], dp[1][1]);
    }

    public int rob02(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if(nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }
        int ans01 = processor(nums, 1, false, false);
        int ans02 = processor(nums, 1, true, true) + nums[0];
        return Math.max(ans01, ans02);
    }

    /**
     * @param nums   价值数组
     * @param index  当前操作的索引
     * @param status 前一个是否拿了
     * @param first  是否拿了
     * @return 返回最大
     */
    public static int processor(int[] nums, int index, boolean status, boolean first) {
        if (index >= nums.length) {
            return 0;
        }
        if (status) {
            return processor(nums, index + 1, false, first);
        } else {
            int ans01 = processor(nums, index + 1, false, first);
            if (index == nums.length - 1 && first) {
                return ans01;
            }
            int ans02 = processor(nums, index + 1, true, first) + nums[index];
            return Math.max(ans01, ans02);
        }
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int ans01 = processor(nums, false);
        int ans02 = processor(nums, true) + nums[0];
        return Math.max(ans01, ans02);
    }

    public static int processor(int[] nums, boolean first) {
        int pre = first ? 0 : nums[1];
        int cur = Math.max(pre, nums[2]);
        for (int i = 3; i < nums.length - 1; i++) {
            int temp = pre;
            pre = cur;
            cur = Math.max(temp + nums[i], cur);
        }
        cur = first ? cur : Math.max(pre + nums[nums.length - 1], cur);
        return cur;
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobberII().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new HouseRobberII().rob(new int[]{3, 2, 2, 3, 5}));
        System.out.println(new HouseRobberII().rob(new int[]{2, 3}));
        System.out.println(new HouseRobberII().rob(new int[]{2}));
    }
}
