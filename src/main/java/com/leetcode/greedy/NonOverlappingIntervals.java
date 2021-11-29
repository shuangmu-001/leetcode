package com.leetcode.greedy;

import com.Utils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.com/problems/non-overlapping-intervals/">Non-overlapping Intervals</a>
 *
 * @author zms
 * @date 4:17 下午 2021/11/29
 */
public class NonOverlappingIntervals {
    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi],
     * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     * <p>
     * Example 1:
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
     * <p>
     * Example 2:
     * Input: intervals = [[1,2],[1,2],[1,2]]
     * Output: 2
     * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
     * <p>
     * Example 3:
     * Input: intervals = [[1,2],[2,3]]
     * Output: 0
     * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     * <p>
     * Constraints:
     * 1 <= intervals.length <= 10^5
     * intervals[i].length == 2
     * -5 * 10^4 <= starti < endi <= 5 * 10^4
     */
    public static int eraseOverlapIntervals01(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int ans = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (pre[1] > intervals[i][1]) {
                pre = intervals[i];
                ans++;
            } else if (pre[1] > intervals[i][0]) {
                ans++;
            } else {
                pre = intervals[i];
            }
        }
        return ans;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (pre[1] > intervals[i][0]) {
                ans++;
            } else {
                pre = intervals[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        System.out.println(eraseOverlapIntervals(intervals));
        Utils.printTwoArrays(intervals);
    }
}
