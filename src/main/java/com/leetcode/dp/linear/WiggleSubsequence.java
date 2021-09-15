package com.leetcode.dp.linear;


/**
 * @author zms
 * @date 11:57 上午 2021/4/27
 * <a href="https://leetcode.com/problems/wiggle-subsequence/">Wiggle Subsequence</a>
 * @see LongestIncreasingSubsequence O(nlogn)
 */
public class WiggleSubsequence {
    /**
     * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
     * <p>
     * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
     * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
     * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
     * <p>
     * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
     * <p>
     * Example 1:
     * Input: nums = [1,7,4,9,2,5]
     * Output: 6
     * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
     * <p>
     * Example 2:
     * Input: nums = [1,17,5,10,13,15,10,5,16,8]
     * Output: 7
     * Explanation: There are several subsequences that achieve this length.
     * One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
     * <p>
     * Example 3:
     * Input: nums = [1,2,3,4,5,6,7,8,9]
     * Output: 2
     * <p>
     * Constraints:
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * Follow up: Could you solve this in O(n) time?
     */
    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int res = 1, end = nums[0], flag = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == end) {
                continue;
            }
            if (flag == 0) {
                flag = nums[i] > end ? -1 : 1;
                res++;
            } else if (flag == -1 && nums[i] < end) {
                flag = 1;
                res++;
            } else if (flag == 1 && nums[i] > end) {
                flag = -1;
                res++;
            }
            end = nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(6 == wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(7 == wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(2 == wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
