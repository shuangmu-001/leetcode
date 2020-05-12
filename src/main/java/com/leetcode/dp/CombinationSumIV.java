package com.leetcode.dp;

import java.util.Arrays;

/**
 * @author wcl
 * @date 5:32 PM 2020/5/12
 * <a href="https://leetcode.com/problems/combination-sum-iv/">
 * Combination Sum IV</a>
 * @see com.leetcode.backtracking.CombinationSum
 * @see com.leetcode.backtracking.CombinationSumII
 * @see com.leetcode.backtracking.CombinationSumIII
 * TODO dp
 */
public class CombinationSumIV {
    /**
     * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
     *
     * Example:
     *
     * nums = [1, 2, 3]
     * target = 4
     *
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     *
     * Note that different sequences are counted as different combinations.
     *
     * Therefore the output is 7.
     *
     *
     * Follow up:
     * What if negative numbers are allowed in the given array?
     * How does it change the problem?
     * What limitation we need to add to the question to allow negative numbers?
     */
    // TLE
    static int count = 0;
    public static int combinationSum41(int[] nums, int target) {
        dfs(nums, target);
        return count;
    }

    public static void dfs(int[] nums, int target) {
        if(target == 0) {
            count++;
            return;
        }
        for (int n : nums) {
            if(target >= n) {
                target -= n;
                dfs(nums, target);
                target += n;
            }
        }
    }
//    public static int combinationSum4(int[] nums, int target) {
//        dfs(nums, target);
//        Arrays.sort(nums);
//        return 0;
//    }
    public static void main(String[] args) {
        System.out.println(combinationSum41(new int[]{1,2,3}, 35));
    }
}
