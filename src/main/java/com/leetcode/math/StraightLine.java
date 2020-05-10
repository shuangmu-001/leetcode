package com.leetcode.math;

/**
 * @author wcl
 * @date 3:18 PM 2020/5/8
 * <a href="https://leetcode.com/problems/check-if-it-is-a-straight-line/">
 * Check If It Is a Straight Line</a>
 */
public class StraightLine {
    /**
     * You are given an array coordinates,
     * coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
     * Check if these points make a straight line in the XY plane.
     *
     *
     * Example 1:
     * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * Output: true
     *
     * Example 2:
     * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
     * Output: false
     *
     * Constraints:
     * 2 <= coordinates.length <= 1000
     * coordinates[i].length == 2
     * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
     * coordinates contains no duplicate point.
     */
    // 移项 (y - y1) / (x - x1) = (y1 - y0) / (x1 - x0)
    // x * b = y * a;
    public boolean checkStraightLine1(int[][] coordinates) {
        double x = 0;
        double y = 0;
        for (int i = 1; i < coordinates.length; i++) {
            double a = coordinates[i][0] - coordinates[i - 1][0];
            double b = coordinates[i][1] - coordinates[i - 1][1];

            if(i == 1) {
                x = a;
                y = b;
            } else if(y == 0) {
                if(b != 0) {
                    return false;
                }
            } else if(x == 0) {
                if(a != 0) {
                    return false;
                }
            } else if((x / a) != (y / b)){
                return false;
            }
        }
        return true;
    }
    public boolean checkStraightLine(int[][] coordinates) {
        int x = 0;
        int y = 0;
        for (int i = 1; i < coordinates.length; i++) {
            int a = coordinates[i][0] - coordinates[i - 1][0];
            int b = coordinates[i][1] - coordinates[i - 1][1];

            if(i == 1) {
                x = a;
                y = b;
            }  else if((x * b) != (y * a)){
                return false;
            }
        }
        return true;
    }
}
