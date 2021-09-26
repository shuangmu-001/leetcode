package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 10:54 AM 2020/5/12
 * <a href="https://leetcode.com/problems/combination-sum-ii/">
 * Combination Sum II</a>
 */
public class CombinationSumII {
    /**
     * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
     *
     * Each number in candidates may only be used once in the combination.
     *
     * Note:
     *
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     *
     * Input: candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * Example 2:
     *
     * Input: candidates = [2,5,2,1,2], target = 5,
     * A solution set is:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, new ArrayList<>(), res, target, 0);
        return res;
    }

    public static void dfs(int[] candidates, int sum, List<Integer> list, List<List<Integer>> res, int target, int index) {
        for (int i = index; i < candidates.length; i++) {
            /**
             * 去重思路
             * @see com.leetcode.arrays.ThreeSum
             */
            if(i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            if(sum > target) {
                return;
            }
            list.add(candidates[i]);
            if(sum == target) {
                res.add(new ArrayList<>(list));
            } else if(sum < target) {
                dfs(candidates, sum, list, res, target, i + 1);
            }
            sum -= candidates[i];
            list.remove(list.size() - 1);
        }
    }
}
