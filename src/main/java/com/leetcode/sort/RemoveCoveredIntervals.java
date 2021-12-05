package com.leetcode.sort;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/remove-covered-intervals/">Remove Covered Intervals</a>
 *
 * @author zms
 * @date 9:06 PM 2021/12/5
 */
public class RemoveCoveredIntervals {
    /**
     * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
     * remove all intervals that are covered by another interval in the list.
     * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
     * Return the number of remaining intervals.
     * <p>
     * Example 1:
     * Input: intervals = [[1,4],[3,6],[2,8]]
     * Output: 2
     * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
     * <p>
     * Example 2:
     * Input: intervals = [[1,4],[2,3]]
     * Output: 1
     * <p>
     * Example 3:
     * Input: intervals = [[0,10],[5,12]]
     * Output: 2
     * <p>
     * Example 4:
     * Input: intervals = [[3,10],[4,10],[5,11]]
     * Output: 2
     * <p>
     * Example 5:
     * Input: intervals = [[1,2],[1,4],[3,4]]
     * Output: 1
     * <p>
     * Constraints:
     * 1 <= intervals.length <= 1000
     * intervals[i].length == 2
     * 0 <= li <= ri <= 10^5
     * All the given intervals are unique.
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        int n = intervals.length, ans = n, max = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][1] <= max) {
                ans--;
            } else {
                max = intervals[i][1];
            }
        }
        return ans;
    }
}
