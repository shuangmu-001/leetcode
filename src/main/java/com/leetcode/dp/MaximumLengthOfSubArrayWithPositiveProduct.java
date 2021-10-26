package com.leetcode.dp;

/**
 * <a href="https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/">
 * Maximum Length of Subarray With Positive Product</a>
 *
 * @author zms
 * @date 3:04 下午 2021/10/26
 */
public class MaximumLengthOfSubArrayWithPositiveProduct {

    /**
     * 给你一个整数数组 nums，请你求出乘积为正数的最长子数组的长度。
     * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     * 请你返回乘积为正数的最长子数组长度。
     * <p>
     * 示例 1：
     * 输入：nums = [1,-2,-3,4]
     * 输出：4
     * 解释：数组本身乘积就是正数，值为 24 。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,-2,-3,-4]
     * 输出：3
     * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
     * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
     * <p>
     * 示例 3：
     * 输入：nums = [-1,-2,-3,0,1]
     * 输出：2
     * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
     * <p>
     * 示例 4：
     * 输入：nums = [-1,2]
     * 输出：1
     * <p>
     * 示例 5：
     * 输入：nums = [1,2,3,5,-6,4,0,10]
     * 输出：4
     * <p>
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i]<= 10^9
     */
    public static int getMaxLen01(int[] nums) {
        int[] ans = process01(nums, 0);
        return ans[0];
    }

    // ans[0] nums[index...]正数的最长的长度
    // ans[1] 包含当前位置的最长长度
    // ans[2] 负数的最长的长度（包含当前）
    public static int[] process01(int[] nums, int index) {
        if (index == nums.length) {
            return new int[]{0, 0, 0};
        }

        int[] ans = process01(nums, index + 1);
        if (nums[index] == 0) {
            ans[1] = 0;
            ans[2] = 0;
        } else if (nums[index] > 0) {
            ans[1]++;
            ans[2] = ans[2] == 0 ? 0 : ans[2] + 1;
        } else {
            int temp = ans[1] + 1;
            ans[1] = ans[2] == 0 ? 0 : ans[2] + 1;
            ans[2] = temp;
        }
        ans[0] = Math.max(ans[0], ans[1]);
        return ans;
    }
    // 负数
    public static int getMaxLen(int[] nums) {
        int ans = 0;
        int pos = 0;
        int neg = 0;
        for (int num : nums) {
            if(num == 0) {
                pos = 0;
                neg = 0;
            } else if(num > 0) {
                pos++;
                neg = neg == 0 ? 0 : neg + 1;
            } else {
                int temp = pos + 1;
                pos = neg == 0 ? 0 : neg + 1;
                neg = temp;
            }
            ans = Math.max(ans, pos);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLen(new int[]{1, -2, -3, 4}) == 4);
        System.out.println(getMaxLen(new int[]{0, 1, -2, -3, -4}) == 3);
        System.out.println(getMaxLen(new int[]{-1, -2, -3, 0, 1}) == 2);
        System.out.println(getMaxLen(new int[]{-1, 2}) == 1);
        System.out.println(getMaxLen(new int[]{1, 2, 3, 5, -6, 4, 0, 10}) == 4);
    }
}
