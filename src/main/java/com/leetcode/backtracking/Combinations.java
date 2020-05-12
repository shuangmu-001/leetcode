package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 6:55 PM 2020/5/11
 * <a href="https://leetcode.com/problems/combinations/">
 * Combinations</a>
 */
public class Combinations {
    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * Example:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    static List<List<Integer>> res;
    static int len;
    public static List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        len = k;
        dfs(n, 0, 0, new ArrayList<>());
        return res;
    }

    public static void dfs(int n, int depth, int index, List<Integer> list) {
        if(depth == len) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < n; i++) {
            if(index + len - depth > n) {
                return;
            }
            List<Integer> newList = new ArrayList<>(list);
            newList.add(i + 1);
            dfs(n, depth + 1, i + 1, newList);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
        System.out.println(combine(10, 5));
    }
}
