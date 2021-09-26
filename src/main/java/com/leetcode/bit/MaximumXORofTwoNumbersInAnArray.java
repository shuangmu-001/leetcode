package com.leetcode.bit;

/**
 * @author zms
 * @date 3:21 下午 2020/9/16
 * <a href="https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/">
 *     Maximum XOR of Two Numbers in an Array</a>
 * TODO O(n)
 */
public class MaximumXORofTwoNumbersInAnArray {
    /**
     * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
     *
     * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
     *
     * Could you do this in O(n) runtime?
     *
     * Example:
     *
     * Input: [3, 10, 5, 25, 2, 8]
     *
     * Output: 28
     *
     * Explanation: The maximum result is 5 ^ 25 = 28.
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }
}
