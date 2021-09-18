package com.leetcode.dp;

import com.Utils;

import java.util.*;

/**
 * @author wcl
 * @date 5:36 PM 2020/3/31
 * <a href="https://leetcode.com/problems/longest-arithmetic-sequence/">
 *     Longest Arithmetic Sequence</a>
 */
public class LongestArithmeticSequence {
    /**
     * Given an array A of integers,
     * return the length of the longest arithmetic subsequence in A.
     * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k]
     * with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
     * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
     *
     * Example 1:
     *      Input: [3,6,9,12]
     *      Output: 4
     *      Explanation:The whole array is an arithmetic sequence with steps of length = 3.
     *
     * Example 2:
     *      Input: [9,4,7,2,10]
     *      Output: 3
     *      Explanation: The longest arithmetic subsequence is [4,7,10].
     *
     * Example 3:
     *      Input: [20,1,15,3,10,5,8]
     *      Output: 4
     *      Explanation:The longest arithmetic subsequence is [20,15,10,5].
     *
     *
     * Note:
     *
     * 2 <= A.length <= 2000
     * 0 <= A[i] <= 10000
     */
    public static int longestArithSeqLength1(int[] A) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int max = 2;
        for (int i = 0; i < A.length; i++) {
            map.put(i, new HashMap<>());
            for (int j = 0; j < i; j++) {
                int arith = A[i] - A[j];
                Map<Integer, Integer> curSubMap = map.get(i);
                if(map.containsKey(j)) {
                    Map<Integer, Integer> subMap = map.get(j);
                    int count = subMap.getOrDefault(arith,1) + 1;
                    curSubMap.put(arith, count);
                } else {
                    curSubMap.put(arith, 2);
                }
                max = Math.max(curSubMap.get(arith), max);
            }
        }
        return max;
    }

    public static int longestArithSeqLength(int[] A) {
        int n = A.length, res = 0;
        int[][] dp = new int[n][n];
        int[] index = new int[20001];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 前一个值
                int prev = A[i] - (A[j] - A[i]);
                if (prev < 0 || index[prev] == 0) {
                    continue;
                }
                dp[i][j] = dp[index[prev] - 1][i] + 1;
                res = Math.max(res, dp[i][j]);
            }
            index[A[i]] = i + 1;
        }
        Utils.printTwoArrays(dp);
        return res + 2;
    }

    public static void main(String[] args) {
        System.out.println(longestArithSeqLength(new int[]{3,6,9,12}) == 4);
        System.out.println(longestArithSeqLength(new int[]{9,4,7,2,10}) == 3);
        System.out.println(longestArithSeqLength(new int[]{20,1,15,3,10,5,8}) == 4);
        System.out.println(longestArithSeqLength(new int[]{20,1,15,3,10,5,8,0}) == 5);
    }
}
