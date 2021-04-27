package com.leetcode.stack;

import java.util.Stack;

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
        int start = 0;
        while (start < n && height[start] == 0) {
            start++;
        }
        if (start >= n) {
            return 0;
        }
        int end = n - 1;
        while (end >= 0 && height[end] == 0) {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        int preIndex, min;
        for (int i = start + 1; i <= end; i++) {
            min = 0;
            if(height[i] <= height[stack.peek()]) {
                if(height[i] == height[stack.peek()]) {
                    stack.pop();
                }
                stack.push(i);
                continue;
            }
            while(!stack.empty() && height[i] >= height[stack.peek()]) {
                preIndex = stack.pop();
                res += (height[preIndex] - min) * (i - preIndex - 1);
                min = height[preIndex];
            }
            if(!stack.empty()) {
                preIndex = stack.peek();
                res += (height[i] - min) * (i - preIndex - 1);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(6 == trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(9 == trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
