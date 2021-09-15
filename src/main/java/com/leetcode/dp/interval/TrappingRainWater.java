package com.leetcode.dp.interval;



/**
 * @author zms
 * @date 3:10 下午 2021/4/27
 * TODO <a href="https://leetcode.com/problems/trapping-rain-water/">Trapping Rain Water</a>
 */
public class TrappingRainWater {
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
     * <p>
     * Example 1:
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
     * <p>
     * Example 2:
     * Input: height = [4,2,0,3,2,5]
     * Output: 9
     * <p>
     * Constraints:
     * n == height.length
     * 0 <= n <= 3 * 104
     * 0 <= height[i] <= 105
     */
    public static int trap(int[] height) {
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        int res = 0;
        // 每个位置的储水量和他左边最高的位置和右边的最高位置有关
        // dp[i] 表示当前到位置的储水量
        // leftMax = height[0, i] 最大值；rightMax = height[i, n - 1] 最大值
        // dp[i] = min{leftMax, rightMax} - height[i];
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i <  n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
    // TODO 能否用双指针解决，空间压缩

    public static void main(String[] args) {
        System.out.println(6 == trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(9 == trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
