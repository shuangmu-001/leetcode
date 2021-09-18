package com.leetcode.arrays;

import com.Utils;

import java.util.*;

/**
 * @author wcl
 * @date 8:36 下午 2020/9/13
 * <a href="">Insert Interval</a>
 */
public class InsertInterval {
    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     *
     * Example 1:
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     *
     * Example 2:
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     * NOTE: input types have been changed on April 15, 2019.
     * Please reset to default code definition to get new method signature.
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        if(newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        List<int[]> res = new ArrayList<>();
        int index = 0;
        while(index < intervals.length && intervals[index][1] < newInterval[0]) {
            res.add(intervals[index++]);
        }
        if(index >= intervals.length) {
            res.add(newInterval);
            return listToArray(res);
        }
        int[] ints = new int[2];
        ints[0] = Math.min(intervals[index][0], newInterval[0]);
        while(index < intervals.length &&
                intervals[index][1] >= newInterval[0] &&
                intervals[index][0] <= newInterval[1]) {
            index++;
        }
        if(index >= intervals.length) {
            ints[1] = Math.max(intervals[intervals.length - 1][1], newInterval[1]);
            res.add(ints);
            return listToArray(res);
        }
        if(index >= 1) {
            ints[1] = Math.max(intervals[index - 1][1], newInterval[1]);
        } else {
            ints[1] = newInterval[1];
        }

        res.add(ints);
        while(index < intervals.length) {
            res.add(intervals[index++]);
        }
        return listToArray(res);
    }

    private static int[][] listToArray(List<int[]> res) {
        int[][] result = new int[res.size()][2];
        int index = 0;
        for (int[] ints : res) {
            result[index++] = ints;
        }
        return  result;
    }

    public static void main(String[] args) {
        Utils.printTwoArrays(insert(new int[][]{{1,2}}, new int[0]));
        Utils.printTwoArrays(insert(new int[][]{{1,5}}, new int[]{6,8}));
        Utils.printTwoArrays(insert(new int[][]{{5,8}}, new int[]{1,5}));
        Utils.printTwoArrays(insert(new int[][]{{5,8}}, new int[]{1,2}));
        Utils.printTwoArrays(insert(new int[][]{{1,2}}, new int[]{1,2}));
        Utils.printTwoArrays(insert(new int[][]{{1,2}, {4,5}}, new int[]{3,3}));
    }

}
