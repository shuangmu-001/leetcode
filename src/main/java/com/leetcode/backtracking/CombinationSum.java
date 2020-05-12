package com.leetcode.backtracking;

import java.util.*;

/**
 * @author wcl
 * @date 10:26 AM 2020/5/12
 * <a href="https://leetcode.com/problems/combination-sum/">
 * Combination Sum</a>
 * TODO 优化
 */
public class CombinationSum {
    /**
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     * The same repeated number may be chosen from candidates unlimited number of times.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * Example 1:
     * Input: candidates = [2,3,6,7], target = 7,
     * A solution set is:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     *
     * Example 2:
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, new ArrayList<>(), res, 0, 0);
        return res;
    }
    public static void dfs(int [] candidates, int target, List<Integer> list, List<List<Integer>> res, int sum, int index) {
        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            if(sum > target) {
                return;
            }
            list.add(candidates[i]);
            if(sum == target) {
                res.add(new ArrayList<>(list));
            } else if(sum < target) {
                dfs(candidates, target, list, res, sum, i);
            }
            sum -= candidates[i];
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(combinationSum(new int[]{3, 5, 2}, 8));
        System.out.println(combinationSum(new int[]{3, 6, 7, 2}, 7));
    }
}
