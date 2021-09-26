package com.leetcode.dp.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 3:47 PM 2020/5/6
 * <a href="https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/">
 *     Number of Ways to Wear Different Hats to Each Other</a>
 * TODO 状态压缩dp
 */
public class NumberOfWaysToWearDifferentHatsToEachOther {
    /**
     * There are n people and 40 types of hats labeled from 1 to 40.
     * Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.
     * Return the number of ways that the n people wear different hats to each other.
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     *
     * Example 1:
     * Input: hats = [[3,4],[4,5],[5]]
     * Output: 1
     * Explanation: There is only one way to choose hats given the conditions.
     * First person choose hat 3, Second person choose hat 4 and last one hat 5.
     *
     * Example 2:
     * Input: hats = [[3,5,1],[3,5]]
     * Output: 4
     * Explanation: There are 4 ways to choose hats
     * (3,5), (5,3), (1,3) and (1,5)
     *
     * Example 3:
     * Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
     * Output: 24
     * Explanation: Each person can choose hats labeled from 1 to 4.
     * Number of Permutations of (1,2,3,4) = 24.
     *
     * Example 4:
     * Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
     * Output: 111
     *
     *
     * Constraints:
     * n == hats.length
     * 1 <= n <= 10
     * 1 <= hats[i].length <= 40
     * 1 <= hats[i][j] <= 40
     * hats[i] contains a list of unique integers.
     */
    private static final int MAX = 1000_000_000 + 7;
    private static long num = 0;
    public static int numberWays1(List<List<Integer>> hats) {
        dfs1(hats, 0, null);
        return (int)(num % MAX);
    }
    // 每个人分配帽子
    public static void dfs1(List<List<Integer>> hats, int index, int[] set) {
        if(index >= hats.size()) {
            num++;
            return;
        }
        List<Integer> integers = hats.get(index);
        for (Integer integer : integers) {
            if (index == 0) {
                set = new int[41];
            }
            int n = integer;
            if (set[n] == 0) {
                set[n]++;
                dfs1(hats, index + 1, set);
                set[n]--;
            }
        }
    }
    // 每个帽子分配到人， 人比帽子少
    public static void dfs(List<List<Integer>> hats, int index, int[] set) {
        if(index >= hats.size()) {
            num++;
            return;
        }
        List<Integer> integers = hats.get(index);
        for (Integer integer : integers) {
            if (index == 0) {
                set = new int[41];
            }
            int n = integer;
            if (set[n] == 0) {
                set[n]++;
                dfs(hats, index + 1, set);
                set[n]--;
            }
        }
    }

    public static int numberWays(List<List<Integer>> hats) {
        dfs(hats, 0, null);
        return (int)(num % MAX);
    }

    //[[1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25],[2,5,16],[1,4,5,6,7,8,9,12,15,16,17,19,21,22,24,25],[1,3,6,8,11,12,13,16,17,19,20,22,24,25],[11,12,14,16,18,24],[2,3,4,5,7,8,13,14,15,17,18,21,24],[1,2,6,7,10,11,13,14,16,18,19,21,23],[1,3,6,7,8,9,10,11,12,14,15,16,18,20,21,22,23,24,25],[2,3,4,6,7,10,12,14,15,16,17,21,22,23,24,25]]
    public static void main(String[] args) {
        List<List<Integer>> hats = new ArrayList<>();
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));
        hats.add(Arrays.asList(1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25));

        long before = System.currentTimeMillis();
        System.out.println(numberWays(hats));
        System.out.println(System.currentTimeMillis() - before);
    }


}
