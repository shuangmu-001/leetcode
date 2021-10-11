package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/permutations-ii/">Permutations II</a>
 *
 * @author zms
 * @date 2:11 PM 2020/5/11
 * @see Permutations
 */
public class PermutationsII {
    /**
     * Given a collection of numbers that might contain duplicates,
     * return all possible unique permutations.
     * <p>
     * Example:
     * <p>
     * Input: [1,1,2]
     * Output:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     * <p>
     * Constraints:
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     */
    static Set<String> set = new HashSet<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        dfs(nums, 0, new boolean[nums.length], new ArrayList<>());
        return res;
    }

    public static void dfs(int[] nums, int depth, boolean[] flag, List<Integer> list) {
        if (depth == nums.length) {
            set.add(list.toString());
            res.add(list);
            return;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            List<Integer> newList = new ArrayList<>(list);
            if (!flag[i]) {
                newList.add(nums[i]);
                if (!set.contains(newList.toString())) {
                    flag[i] = true;
                    dfs(nums, depth + 1, flag, newList);
                    flag[i] = false;
                } else {
                    newList.remove(newList.size() - 1);
                }
            }
        }
    }

    public static List<List<Integer>> permuteUnique01(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        process01(arr, 0, res);
        return res;
    }

    // 剪枝
    public static void process01(int[] arr, int index, List<List<Integer>> res) {
        int length = arr.length;
        if (index == length) {
            List<Integer> ans = new ArrayList<>();
            for (int j : arr) {
                ans.add(j);
            }
            res.add(ans);
            return;
        }
        // 判断当前位置是否使用过某一个数
//        boolean[] visit = new boolean[21];
        for (int i = index; i < length; i++) {
//            if (!visit[arr[i] + 10]) {
            if (isVisit(arr, index, i)) {
//                visit[arr[i] + 10] = true;
                swap(arr, index, i);
                process01(arr, index + 1, res);
                swap(arr, index, i);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 判断当前位置是否使用过某一个数
    private static boolean isVisit(int[] arr, int cur, int index) {
        for (int i = cur; i < index; i++) {
            if (arr[i] == arr[index]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique01(new int[]{1, 1, 2}));
    }
}
