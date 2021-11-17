package com.leetcode.arrays;

import com.Utils;

/**
 * <a href="https://leetcode.com/problems/rotate-array/">Rotate Array</a>
 *
 * @author zms
 * @date 6:30 PM 2020/4/22
 */
public class RotateArray {
    /**
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     * <p>
     * Example 1:
     * <p>
     * Input: [1,2,3,4,5,6,7] and k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * Example 2:
     * <p>
     * Input: [-1,-100,3,99] and k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     * Note:
     * <p>
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     * Could you do it in-place with O(1) extra space?
     */
    public static void rotate01(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        int index = 0;
        int next = (index + k) % len;
        int firstNext = next;
        int num = nums[index];
        int count = 0;
        while (count < len) {
            int temp = nums[next];
            nums[next] = num;
            index = next;
            next = (index + k) % len;
            num = temp;
            if (next == firstNext) {
                index = next + 1;
                next = (index + k) % len;
                firstNext = next;
                if (index < len) {
                    num = nums[index];
                }
            }
            count++;
        }
        Utils.printArrays(nums);
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        k = k % len;
        if (k == 0) {
            return;
        }
        int num = nums[0];
        for (int i = 0, index = 0, start = 0; i < len; i++) {
            int next = (index + k) % len;
            int temp = nums[next];
            nums[next] = num;
            index = next;
            num = temp;
            if (index == start) {
                index = next + 1;
                start = index;
                if (index < len) {
                    num = nums[index];
                }
            }
        }
        Utils.printArrays(nums);
    }

    public static void main(String[] args) {
        rotate(new int[]{1, 2, 3, 4, 5, 6}, 2);
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 1);
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 7);
//        List<int[]> read = Utils.read("/Users/wangchenglin/Documents/GitHub/leetcode/src/main/resources/rotate.txt", 0);
//        if (read != null) {
//            rotate(read.get(0), 11939);
//        }
        new RotateArray().rotate(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return;
        }
        int n = matrix.length;
        int length = n >> 1;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int num = matrix[i][j];
                int a = i;
                int b = j;
                for (int k = 0; k < 4; k++) {
                    int temp = a;
                    a = b;
                    b = n - 1 - temp;
                    temp = matrix[a][b];
                    matrix[a][b] = num;
                    num = temp;
                }
            }
        }

    }
}
