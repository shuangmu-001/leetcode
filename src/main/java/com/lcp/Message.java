package com.lcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wcl
 * @date 6:31 PM 2020/4/28
 * <a href="https://leetcode-cn.com/problems/chuan-di-xin-xi/">
 *     传递信息</a>
 * TODO 问题 ：传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息） 为什么示例1中 0 -> 2 2 -> 0
 */
public class Message {
    /**
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     *
     * 示例 1：
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     * 输出：3
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     * 示例 2：
     * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
     * 输出：0
     * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
     *
     * 限制：
     * 2 <= n <= 10
     * 1 <= k <= 5
     * 1 <= relation.length <= 90, 且 relation[i].length == 2
     * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
     *
     */
    static int ways = 0;
    static Map<Integer, List<Integer>>  relations;
    public static int numWays(int n, int[][] relation, int k) {
        ways = 0;
        relations = new HashMap<>();
        for (int[] ints : relation) {

            if (relations.containsKey(ints[0])) {
                List<Integer> integers = relations.get(ints[0]);
                integers.add(ints[1]);
            } else {
                List<Integer> integers = new ArrayList<>();
                integers.add(ints[1]);
                relations.put(ints[0], integers);
            }
        }
        System.out.println(relations);
        if(k == 1) {
            return relations.get(0).contains(n - 1) ? 1 : 0;
        }
        dfs(0, k, relations.get(0), n - 1);
        return ways;
    }

    public static void dfs(int len, int k, List<Integer> nums, int n) {
        for (int num : nums) {
            if(len + 1 == k) {
                if(num == n) {
                    ways++;
                    return;
                }
            } else if(relations.containsKey(num)) {
                dfs(len + 1, k, relations.get(num), n);
            }
        }
    }

    // dp[i][j] i表示第几个人，j不是传递几次
    // TODO 问题 : 如何确定一定是从 0 开始的呢？
    public static int numWays1(int n, int[][] relation, int k) {
        int[][] dp = new int[n][k + 1];
        for (int i = 1; i < k + 1; i++) {
            for (int[] rel : relation) {
                dp[rel[1]][i] += dp[rel[0]][i - 1];
            }
        }
        return dp[n - 1][k];
    }

    public static void main(String[] args) {
        System.out.println(numWays(3, new int[][]{
                {0, 2},{2, 1}
        },2));
        System.out.println(numWays(5, new int[][]{
                {0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}
        },3));
    }

}
