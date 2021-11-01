package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/pascals-triangle-ii/">Pascal's Triangle II</a>
 *
 * @author zms
 * @date 10:33 上午 2021/11/1
 */
public class PascalsTriangleII {
    /**
     * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     * <p>
     * Example 1:
     * Input: rowIndex = 3
     * Output: [1,3,3,1]
     * <p>
     * Example 2:
     * Input: rowIndex = 0
     * Output: [1]
     * <p>
     * Example 3:
     * Input: rowIndex = 1
     * Output: [1,1]
     * <p>
     * <p>
     * Constraints:
     * 0 <= rowIndex <= 33
     * <p>
     * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        ans.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            int pre = ans.get(0);
            for (int j = 1; j < i; j++) {
                int old = ans.get(j);
                ans.set(j, pre + old);
                pre = old;
            }
            ans.add(1);
        }
        return ans;
    }

}
