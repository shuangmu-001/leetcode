package com.leetcode.greedy;

import java.util.Arrays;

/**
 * @author wcl
 * @date 9:52 下午 2020/10/4
 * TODO <a href="https://leetcode-cn.com/problems/remove-covered-intervals/">Remove Covered Intervals</a>
 */
public class RemoveCoveredIntervals {
    /**
     * Given a list of intervals, remove all intervals that are covered by another interval in the list.
     *
     * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
     *
     * After doing so, return the number of remaining intervals.
     *
     *  
     *
     * Example 1:
     *
     * Input: intervals = [[1,4],[3,6],[2,8]]
     * Output: 2
     * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
     * Example 2:
     *
     * Input: intervals = [[1,4],[2,3]]
     * Output: 1
     * Example 3:
     *
     * Input: intervals = [[0,10],[5,12]]
     * Output: 2
     * Example 4:
     *
     * Input: intervals = [[3,10],[4,10],[5,11]]
     * Output: 2
     * Example 5:
     *
     * Input: intervals = [[1,2],[1,4],[3,4]]
     * Output: 1
     *  
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 1000
     * intervals[i].length == 2
     * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
     * All the intervals are unique.
     *
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1]: o1[0] - o2[0]);

        int count = 0;
        int end, prev_end = 0;
        for (int[] curr : intervals) {
            end = curr[1];
            if (prev_end < end) {
                ++count;
                prev_end = end;
            }
        }
        return count;
    }
    // intervals[0] < intervals[1] 和 intervals[0]>=0
    public boolean check(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1]: o1[0] - o2[0]);

        int cur, prev_end = -1;
        int length = intervals.length;
        for (int i = 0; i < length; i++) {
            cur = intervals[i][0];
            if(cur <= prev_end) {
                throw new RuntimeException("区间 " + intervals[i - 1][0] + "-"
                        + intervals[i - 1][1] + "与区间"
                        + intervals[i - 1][0] + "-" + intervals[i - 1][1] + "重叠");
            }
            prev_end = intervals[i][1];
        }
        return true;
    }
}
