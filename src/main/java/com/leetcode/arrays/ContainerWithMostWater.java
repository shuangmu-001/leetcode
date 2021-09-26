package com.leetcode.arrays;

/**
 * @author zms
 * @date 2:32 PM 2019/12/26
 */
public class ContainerWithMostWater {

    /**
     * Given n non-negative integers a1, a2, ..., an ,
     * where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of
     * line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     */
    public static int maxArea(int[] height) {
        int max = 0;
        int startIndex = 0;
        int endIndex = height.length - 1;
        while (startIndex < endIndex) {
            int result = Math.min(height[startIndex], height[endIndex]) * (endIndex - startIndex);
            max = Math.max(max, result);
            if(height[startIndex] < height[endIndex]) {
                startIndex++;
            } else {
                endIndex--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] ints1 = {1,8,6,50,51,4,8,3,7};
//        System.out.println(maxArea(ints));
        System.out.println(maxArea(ints));
        System.out.println(maxArea(ints) == 49);
    }

}
