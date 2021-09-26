package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zms
 * @date 2:11 PM 2020/5/11
 * <a href="https://leetcode.com/problems/permutations-ii/">
 *  Permutations II</a>
 * @see Permutations
 * TODO 耗时 优化
 */
public class PermutationsII {
    /**
     * Given a collection of numbers that might contain duplicates,
     * return all possible unique permutations.
     *
     * Example:
     *
     * Input: [1,1,2]
     * Output:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    static Set<String> set = new HashSet<>();
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> permuteUnique(int[] nums) {
        dfs(nums, 0, new boolean[nums.length], new ArrayList<>());
        return res;
    }
    public static void dfs(int[] nums, int depth, boolean[] flag, List<Integer> list) {
        if(depth == nums.length) {
            set.add(list.toString());
            res.add(list);
            return;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            List<Integer> newList = new ArrayList<>(list);
            if(!flag[i]) {
                newList.add(nums[i]);
                if(!set.contains(newList.toString())) {
                    flag[i] = true;
                    dfs(nums, depth + 1, flag, newList);
                    flag[i] = false;
                } else {
                    newList.remove(newList.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }
}
