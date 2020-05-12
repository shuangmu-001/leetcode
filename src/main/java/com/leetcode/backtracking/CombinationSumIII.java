package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 11:07 AM 2020/5/12
 * <a href="https://leetcode.com/problems/combination-sum-iii/">
 * Combination Sum III</a>
 */
public class CombinationSumIII {
    /**
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     *
     * Note:
     * All numbers will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * Example 1:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     *
     * Example 2:
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     */
    static int target;
    static int len;
    static List<List<Integer>> res;
    public static List<List<Integer>> combinationSum31(int k, int n) {
        res = new ArrayList<>();
        target = n;
        len = k;
        dfs(0,0,1, new ArrayList<>());
        return res;
    }

    public static void dfs(int sum, int depth, int index, List<Integer> list) {
        for (int i = index; i <= 9; i++) {
            sum += i;
            list.add(i);
            // 可以用len - 1来表示深度 == 0
            if(depth < len - 1) {
                // 用 target == 0 来表示目标值
                if(sum >= target) {
                    list.remove(list.size() - 1);
                    return;
                }
                dfs(sum, depth + 1, i + 1, list);
                sum -= i;
            } else {
                if(sum < target) {
                    sum -= i;
                } else {
                    if(sum == target) {
                        res.add(new ArrayList<>(list));
                    }
                    list.remove(list.size() - 1);
                    return;
                }
            }
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k,1, new ArrayList<>(), res);
        return res;
    }

    public static void dfs(int sum, int depth, int index, List<Integer> list, List<List<Integer>> res) {
        if(sum == 0 && depth == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(sum == 0 || depth == 0) {
            return;
        }
        for (int i = index; i <= 9; i++) {
            if(sum >= i) {
                sum -= i;
                list.add(i);
                dfs(sum, depth - 1, i + 1, list, res);
                list.remove(list.size() - 1);
                sum += i;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
        System.out.println(combinationSum3(3, 9));
    }
}
