package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/combinations/">Combinations</a>
 *
 * @author zms
 * @date 6:55 PM 2020/5/11
 */
public class Combinations {
    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * <p>
     * Example:
     * <p>
     * Input: n = 4, k = 2
     * Output:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
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
        if (depth == len) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < n; i++) {
            if (index + len - depth > n) {
                return;
            }
            List<Integer> newList = new ArrayList<>(list);
            newList.add(i + 1);
            dfs(n, depth + 1, i + 1, newList);
        }
    }

    public static List<List<Integer>> combine01(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        process(n, 1, k, res, new LinkedList<>());
        return res;
    }

    public static void process(int n, int index, int rest, List<List<Integer>> res, LinkedList<Integer> ans) {

        if (rest == 0) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = index; i <= n; i++) {
            if (n - i + 1 < rest) {
                return;
            }
            ans.add(i);
            process(n, i + 1, rest - 1, res, ans);
            ans.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(combine01(4, 2));
        System.out.println(combine01(10, 5));
    }
}
