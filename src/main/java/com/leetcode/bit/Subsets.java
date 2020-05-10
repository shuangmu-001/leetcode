package com.leetcode.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 11:08 AM 2020/5/8
 * <a href="https://leetcode.com/problems/subsets/">
 *  Subsets</a>
 */
public class Subsets {
    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * Example:
     *
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        int length = nums.length;
        int newLen = 1 << length;
        List<List<Integer>> res = new ArrayList<>(newLen);
        for (int i = 0; i < newLen; i++) {
            List<Integer> set = new ArrayList<>();
            int dummy = i;
            int index = 0;
            System.out.println(i);
            while(dummy != 0) {
                if((dummy & 1) == 1) {
                    set.add(nums[index++]);
                } else {
                    index++;
                }
                dummy >>= 1;
            }
            res.add(set);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}
