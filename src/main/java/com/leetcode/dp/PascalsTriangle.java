package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle/">Pascal's Triangle</a>
 *
 * @author zms
 * @date 10:33 上午 2021/11/1
 */
public class PascalsTriangle {
    /**
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     * Example 1:
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     *
     * Example 2:
     * Input: numRows = 1
     * Output: [[1]]
     *
     * Constraints:
     * 1 <= numRows <= 30
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        ans.add(pre);
        for (int i = 1; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add(pre.get(j - 1) + pre.get(j));
            }
            cur.add(1);
            ans.add(cur);
            pre = cur;
        }
        return ans;
    }

}
