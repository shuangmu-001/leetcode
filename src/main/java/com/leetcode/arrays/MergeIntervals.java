package com.leetcode.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/">Merge Intervals</a>
 *
 * @author zms
 * @date 8:54 下午 2021/11/27
 */
public class MergeIntervals {

    /**
     * Given an array of intervals where intervals[i] = [starti, endi],merge all overlapping intervals,
     * and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * <p>
     * Example 1:
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     * <p>
     * Example 2:
     * Input: intervals = [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     * <p>
     * Constraints:
     * 1 <= intervals.length <= 10^4
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 10^4
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int m = intervals.length;
        int[][] res = new int[m][2];
        int index = 0;
        res[index][0] = intervals[0][0];
        res[index][1] = intervals[0][1];
        for (int i = 1; i < m; i++) {
            if (res[index][1] < intervals[i][0]) {
                res[++index][0] = intervals[i][0];
            }
            res[index][1] = Math.max(res[index][1], intervals[i][1]);
        }
        if (index == m - 1) {
            return res;
        }
        int[][] ans = new int[index + 1][2];
        for (int i = 0; i <= index; i++) {
            ans[i][0] = res[i][0];
            ans[i][1] = res[i][1];
        }
        return ans;
    }
}
