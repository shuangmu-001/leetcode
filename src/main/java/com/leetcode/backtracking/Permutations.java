package com.leetcode.backtracking;

import java.util.*;

/**
 * 实现排列 (不用添加，用交换数据完成)
 * {@link "https://leetcode.com/problems/permutations/"}
 *
 * @author zms
 * @date 5:48 PM 2020/2/25
 */
public class Permutations {
    /**
     * Given a collection of distinct integers, return all possible permutations.
     * Example:
     * Input: [1,2,3]
     * Output:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    static List<List<Integer>> res;

    public static List<List<Integer>> permute(int[] arr) {
        res = new ArrayList<>();
        dfs(arr, 0, null, new ArrayList<>());
        return res;
    }

    public static void dfs(int[] arr, int length, Set<Integer> set, List<Integer> list) {
        if (length == arr.length) {
            res.add(list);
            return;
        }
        for (int num : arr) {
            if (length == 0) {
                set = new HashSet<>();
            }
            if (!set.contains(num)) {
                set.add(num);
                List<Integer> newList = new ArrayList<>(list);
                newList.add(num);
                dfs(arr, length + 1, set, newList);
                set.remove(num);
            }
        }
    }

    public static List<List<Integer>> permute01(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        process01(arr, 0, res);
        return res;
    }

    /**
     * @param arr   需要排列的数组
     * @param index 排列数组的当前索引
     * @param res   结果集合
     */
    private static void process01(int[] arr, int index, List<List<Integer>> res) {
        if (index >= arr.length) {
            List<Integer> ans = new ArrayList<>();
            for (int i : arr) {
                ans.add(i);
            }
            res.add(ans);
            return;
        }
        for (int i = index; i < arr.length; i++) {
            // index == 0 第一个数可以是index到arr.length-1中的任意一个数
            // 如果一个数是i，那么之后的数就不能有i，就将i放到index位置，那么后续的遍历就不用从头进行，并且不用判断
            swap(arr, index, i);
            process01(arr, index + 1, res);
            swap(arr, index, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

//        System.out.println(permute(new int[]{1}));
//        System.out.println(permute(new int[]{1,2}));
        System.out.println(permute01(new int[]{1, 2, 3}));
//        System.out.println(permute(new int[]{1,2,3,4}));
//        System.out.println(permute(new int[]{1,2,3,4,5}));
//        System.out.println(permute(new int[]{1,2,3,4,5,6}));
//        System.out.println(permute(new int[]{1,2,3,4,5,6,7}));
//        System.out.println(permute(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(permute(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
