package com.leetcode.backtracking;

import java.util.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wcl
 * @date 5:48 PM 2020/2/25
 * {@link "https://leetcode.com/problems/permutations/"}
 * TODO 实现排列
 */
public class Permutations {
    /**
     * Given a collection of distinct integers, return all possible permutations.
     * Example:
     *      Input: [1,2,3]
     *      Output:
     *          [
     *              [1,2,3],
     *              [1,3,2],
     *              [2,1,3],
     *              [2,3,1],
     *              [3,1,2],
     *              [3,2,1]
     *          ]
     */
    static List<List<Integer>> res;
    public static List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        dfs(nums, 0, null, new ArrayList<>());
        return res;
    }

    public static void dfs(int[] nums, int length, Set<Integer> set, List<Integer> list) {
        if(length == nums.length) {
            res.add(list);
            return;
        }
        for (int num : nums) {
            if (length == 0) {
                set = new HashSet<>();
            }
            if (!set.contains(num)) {
                set.add(num);
                List<Integer> newList = new ArrayList<>(list);
                newList.add(num);
                dfs(nums, length + 1, set, newList);
                set.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }
}
