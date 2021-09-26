package com.leetcode.dp;

import com.Utils;

import java.util.*;

/**
 * @author zms
 * @date 3:05 下午 2020/5/25
 * <a href="https://leetcode.com/problems/uncrossed-lines/">
 * Uncrossed Lines</a>
 * TODO dp
 * @see MakeArrayStrictlyIncreasing 是否相同呢？
 */
public class UncrossedLines {
    /**
     * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
     * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
     * A[i] == B[j];
     * The line we draw does not intersect any other connecting (non-horizontal) line.
     * Note that a connecting lines cannot intersect even at the endpoints:
     * each number can only belong to one connecting line.
     *
     * Return the maximum number of connecting lines we can draw in this way.
     *
     *
     *
     * Example 1:
     * Input: A = [1,4,2], B = [1,2,4]
     * Output: 2
     * Explanation: We can draw 2 uncrossed lines as in the diagram.
     * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
     *
     * Example 2:
     * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
     * Output: 3
     *
     * Example 3:
     * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
     * Output: 2
     *
     *
     * Note:
     *
     * 1 <= A.length <= 500
     * 1 <= B.length <= 500
     * 1 <= A[i], B[i] <= 2000
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int bLen = B.length;
        int aLen = A.length;
        if(aLen > bLen) {
            int[] temp = A;
            A = B;
            bLen = aLen;
            B = temp;
        }
        res = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bLen; i++) {
            if(map.containsKey(B[i])) {
                List<Integer> integers = map.get(B[i]);
                integers.add(i);
            } else {
                List<Integer> integers = new ArrayList<>();
                integers.add(i);
                map.put(B[i], integers);
            }
        }
        int maxLen = 0;
        List<List<Integer>> lines = new ArrayList<>();
        for (int value : A) {
            if (map.containsKey(value)) {
                List<Integer> integers = map.get(value);
                maxLen = Math.max(integers.size(), maxLen);
                lines.add(integers);
            }
        }

//        indexs = new int[lines.size()][maxLen];
//        dfs(lines, 0, -1, 0);
//        return res;
        return lengthOfLIS(lines, maxLen);
    }
    int[][] indexs;
    int res = 0;
    // TLE
    public void dfs(List<List<Integer>> lines, int depth, int before, int len) {
        if(depth == lines.size() || res == lines.size()) {
            res = Math.max(len, res);
            return;
        }

        if(res > (len + lines.size() - depth)) {
            return;
        }

        List<Integer> integers = lines.get(depth);
        int index = 0;
        while (index < integers.size() && before >= integers.get(index)) {
            index++;
        }
        if (index < integers.size()) {
            int num = integers.get(index);
            if(indexs[depth][index] != 0) {
                res = Math.max(len + indexs[depth][index], res);
                return;
            }

            dfs(lines, depth + 1, num, len + 1);
        }
        if(res > (len + lines.size() - depth)) {
            return;
        }
        dfs(lines, depth + 1, before, len);
    }

    // 给定一个二维数组 每一行(递增)取一个元素，求最长的上升子序列
    public int lengthOfLIS(List<List<Integer>> lines, int maxLen) {
        int size = lines.size();
        int[][] dp = new int[size][maxLen];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], 1);
        }
        int res = 0;
        for (int i = 0; i < size; i++) {
            List<Integer> cur = lines.get(i);
            int n = cur.size();
            for (int j = 0; j < n; j++) {
                int num = cur.get(j);
                for (int k = 0; k < i; k++) {
                    List<Integer> before = lines.get(k);
                    int m = before.size();
                    int index = m - 1;
                    while(index >= 0 && before.get(index) > num) {
                        index--;
                    }
                    if(index >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][index] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new UncrossedLines().maxUncrossedLines(new int[]{1,4,2}, new int[]{1,2,4}));
        System.out.println(new UncrossedLines().maxUncrossedLines(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}));
        System.out.println(new UncrossedLines().maxUncrossedLines(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1}));
        // 9
        System.out.println(new UncrossedLines().maxUncrossedLines(new int[]{3,1,4,1,1,3,5,1,2,2},
                new int[]{4,1,5,2,1,1,1,5,3,1,1,1,2,3,1,4,3,5,5,3,1,2,3,2,4,1,1,1,5,3}));
        //12
        System.out.println("消耗的时间为 : " +
                Utils.consumeTime(t -> System.out.println(((UncrossedLines)t).maxUncrossedLines(new int[]{1,5,3,5,3,5,5,4,4,3,2,3,5,4,5,4,5,2,5,3,3,1,4,4,3,1,1,1,4,4},
                        new int[]{1,3,2,2,5,2,3,1,1,3,5,4,5,5,3,5,4,1,2,5})), new UncrossedLines()));
        // 16
        System.out.println("消耗的时间为 : " +
                Utils.consumeTime(t -> System.out.println(((UncrossedLines)t).maxUncrossedLines(new int[]{19,5,19,19,2,9,5,19,20,17,3,1,7,10,19,16,8,3,13,13,16,3,16,7,14,11,18,5,8,12,8,15,18,10,8,8,12,8,9,17,17,14,14,1,8,19,8,1,5,4},
                        new int[]{18,20,18,18,4,7,17,17,1,18,6,4,11,14,19,15,12,20,3,5,12,2,13,14,9,16,6,4,16,8,10,19,15,18,12,11,9,14,7,9,14,15,6,18,12,8,20,11,2,17})), new UncrossedLines()));
        // 23
        System.out.println("消耗的时间为 : " +
                Utils.consumeTime(t -> System.out.println(((UncrossedLines)t).maxUncrossedLines(new int[]{15,14,1,7,15,1,12,18,9,15,1,20,18,15,16,18,11,8,11,18,11,11,17,20,16,20,15,15,9,18,16,4,16,1,13,10,10,20,4,18,17,3,8,1,8,19,14,10,10,12},
                        new int[]{12,8,17,4,2,18,16,10,11,12,7,1,8,16,4,14,12,18,18,19,19,1,11,18,1,6,12,17,6,19,10,5,11,16,6,17,12,1,9,3,19,2,18,18,2,4,11,11,14,9,20,19,2,20,9,15,8,7,8,6,19,12,4,11,18,18,1,6,9,17,13,19,5,4,14,9,11,15,2,5,4,1,10,11,6,4,9,7,11,7,3,8,11,12,4,19,12,17,14,18})), new UncrossedLines()));
    }
}
