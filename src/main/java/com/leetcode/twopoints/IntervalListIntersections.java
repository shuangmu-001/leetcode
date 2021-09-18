package com.leetcode.twopoints;

import com.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 9:32 下午 2020/5/23
 * <a href="https://leetcode.com/problems/interval-list-intersections/">
 * Interval List Intersections</a>
 */
public class IntervalListIntersections {
    /**
     * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
     *
     *
     * Note:
     *
     * 0 <= A.length < 1000
     * 0 <= B.length < 1000
     * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
     * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     */
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        int len1 = A.length;
        int len2 = B.length;
        int aIndex = 0;
        int bIndex = 0;
        List<int[]> list = new ArrayList<>();
        while(aIndex < len1 && bIndex < len2) {
            int[] num = new int[2];

            if(A[aIndex][1] < B[bIndex][0]) {
                aIndex++;
            } else if(A[aIndex][0] > B[bIndex][1]) {
                bIndex++;
            } else {
                num[0] = Math.max(A[aIndex][0], B[bIndex][0]);
                num[1] = Math.min(A[aIndex][1], B[bIndex][1]);
                list.add(num);
                if(A[aIndex][1] < B[bIndex][1]) {
                    aIndex++;
                } else if(A[aIndex][1] > B[bIndex][1]) {
                    bIndex++;
                } else {
                    aIndex++;
                    bIndex++;
                }
            }
        }
        int[][] res = new int[list.size()][2];
        int index = 0;
        for (int[] num : list) {
            res[index++] = num;
        }
        Utils.printTwoArrays(res);
        return res;
    }

    public static void main(String[] args) {
        intervalIntersection(new int[][] {
                {0,2},{5,10},{13,23},{24,25}
        }, new int[][] {
                {1,5},{8,12},{15,24},{25,26}
        });
    }
}
