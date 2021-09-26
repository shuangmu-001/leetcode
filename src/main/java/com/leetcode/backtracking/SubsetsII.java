package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 2:20 PM 2020/5/12
 * <a href="https://leetcode.com/problems/subsets-ii/">
 * Subsets II</a>
 */
public class SubsetsII {
    /**
     * Given a collection of integers that might contain duplicates, nums,
     * return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: [1,2,2]
     * Output:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++) {
            dfs(i, new ArrayList<>(), res, nums, 0);
        }
        return res;
    }

    public static void dfs(int depth, List<Integer> list, List<List<Integer>> res, int[] nums, int index) {
        if(depth == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if(i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(depth - 1, list, res, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }
}
