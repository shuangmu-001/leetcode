package com.leetcode.graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/find-the-town-judge/">Find the Town Judge</a>
 *
 * @author zms
 * @date 6:13 PM 2020/5/10
 */
public class FindTheTownJudge {
    /**
     * In a town, there are N people labelled from 1 to N.
     * There is a rumor that one of these people is secretly the town judge.
     * <p>
     * If the town judge exists, then:
     * <p>
     * The town judge trusts nobody.
     * Everybody (except for the town judge) trusts the town judge.
     * There is exactly one person that satisfies properties 1 and 2.
     * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
     * <p>
     * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
     * <p>
     * Example 1:
     * Input: N = 2, trust = [[1,2]]
     * Output: 2
     * <p>
     * Example 2:
     * Input: N = 3, trust = [[1,3],[2,3]]
     * Output: 3
     * <p>
     * Example 3:
     * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
     * Output: -1
     * <p>
     * Example 4:
     * Input: N = 3, trust = [[1,2],[2,3]]
     * Output: -1
     * <p>
     * Example 5:
     * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
     * Output: 3
     * <p>
     * Note:
     * 1 <= N <= 1000
     * trust.length <= 10000
     * trust[i] are all different
     * trust[i][0] != trust[i][1]
     * 1 <= trust[i][0], trust[i][1] <= N
     */
    public static int findJudge01(int N, int[][] trust) {
        if (N == 1 && (trust == null || trust.length == 0)) {
            return 1;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int length = trust.length;
        for (int[] ints : trust) {
            Set<Integer> set;
            if (!map.containsKey(ints[0])) {
                set = new HashSet<>();
                map.put(ints[0], set);
            } else {
                set = map.get(ints[0]);
            }
            set.add(ints[1]);

        }
        if (map.size() == N || map.size() < N - 1) {
            return -1;
        }
        Set<Integer> keys = map.keySet();
        int sum = 0;
        for (Integer key : keys) {
            sum += key;
        }

        int res = (1 + N) * N / 2 - sum;
        for (Integer key : keys) {
            Set<Integer> integers = map.get(key);
            if (!integers.contains(res)) {
                return -1;
            }
        }
        return res;
    }

    public static int findJudge(int n, int[][] trust) {
        if (n == 1 && (trust == null || trust.length == 0)) {
            return 1;
        }
        // 信任人数
        int[] trustsCount = new int[n + 1];
        // 被信任人数
        int[] trustedCount = new int[n + 1];
        for (int[] ints : trust) {
            trustsCount[ints[0]]++;
            trustedCount[ints[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if(trustedCount[i] == n - 1 && trustsCount[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findJudge(2, new int[][]{
                {1, 2}
        }));
    }
}
