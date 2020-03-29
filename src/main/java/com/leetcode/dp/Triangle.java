package com.leetcode.dp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wcl
 * @date 10:47 AM 2020/3/29
 * <a href="https://leetcode.com/problems/triangle/">
 *     Triangle</a>
 */
public class Triangle {
    /**
     * Given a triangle, find the minimum path sum from top to bottom.
     * Each step you may move to adjacent numbers on the row below.
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * Note:
     *      Bonus point if you are able to do this using only O(n) extra space,
     *      where n is the total number of rows in the triangle.
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() < 1) {
            return 0;
        }
        int size = triangle.size();
        int[] dp = new int[size];
        for (int i = size - 1; i >=0 ; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if(i == size - 1) {
                    dp[j] = list.get(j);
                } else {
                    dp[j] = list.get(j) + Math.min(dp[j], dp[j + 1]);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(minimumTotal(triangle) == 11);
        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(Collections.singletonList(-1));
        triangle1.add(Arrays.asList(3,2));
        triangle1.add(Arrays.asList(-3,1,-1));
        System.out.println(minimumTotal(triangle1) == -1);
    }
}
