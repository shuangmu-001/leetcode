package com.leetcode.heap;

import java.util.Arrays;

/**
 * @author zms
 * @date 4:08 PM 2020/5/7
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">
 *     Kth Largest Element in an Array</a>
 */
public class KthLargestElement {
    /**
     * Find the kth largest element in an unsorted array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * Example 1:
     *
     * Input: [3,2,1,5,6,4] and k = 2
     * Output: 5
     * Example 2:
     *
     * Input: [3,2,3,1,2,4,5,5,6] and k = 4
     * Output: 4
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int length = nums.length;
        return nums[length - k];
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
