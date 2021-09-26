package com.leetcode.arrays;

/**
 * @author zms
 * @date 12:25 PM 2020/2/27
 * {@link "https://leetcode.com/problems/range-sum-query-immutable/"}
 */
public class RangeSumQueryImmutable {

    /**
     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
     *
     * Example:
     *      Given nums = [-2, 0, 3, -5, 2, -1]
     *      sumRange(0, 2) -> 1
     *      sumRange(2, 5) -> -1
     *      sumRange(0, 5) -> -3
     * Note:
     *      You may assume that the array does not change.
     *      There are many calls to sumRange function.
     */
    class NumArray {
        int[] results;
        /**
         * Your NumArray object will be instantiated and called as such:
         * NumArray obj = new NumArray(nums);
         * int param_1 = obj.sumRange(i,j);
         */
        public NumArray(int[] nums) {

            int length = nums.length;
            results = nums;

            for(int i = 1; i < length; i++) {
                results[i] = nums[i] + results[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return i == 0 ? results[j] : results[j]- results[i - 1];
        }
    }
}
