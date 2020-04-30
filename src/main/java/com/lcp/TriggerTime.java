package com.lcp;

import com.leetcode.Utils;
import com.leetcode.search.FindFirstAndLastPositionOfElementInSortedArray;

/**
 * @author wcl
 * @date 10:32 AM 2020/4/29
 * <a href="https://leetcode-cn.com/problems/ju-qing-hong-fa-shi-jian/">
 *      剧情触发时间</a>
 * @see FindFirstAndLastPositionOfElementInSortedArray
 */
public class TriggerTime {
    /**
     * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。
     * 一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。
     * 在游戏开始时（第 0 天），三种属性的值均为 0。
     * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。
     * 这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
     * 所有剧情的触发条件也用一个二维数组 requirements 表示。
     * 这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，
     * 如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
     * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
     *
     * 示例 1：
     *
     * 输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements = [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]
     * 输出: [2,-1,3,-1]
     * 解释：
     * 初始时，C = 0，R = 0，H = 0
     * 第 1 天，C = 2，R = 8，H = 4
     * 第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0
     * 第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2
     * 剧情 1 和 3 无法触发。
     *
     * 示例 2：
     * 输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
     * 输出: [-1,4,3,3,3]
     *
     * 示例 3：
     * 输入： increase = [[1,1,1]] requirements = [[0,0,0]]
     * 输出: [0]
     *
     * 限制：
     * 1 <= increase.length <= 10000
     * 1 <= requirements.length <= 100000
     * 0 <= increase[i] <= 10
     * 0 <= requirements[i] <= 100000
     *
     */
    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int length = requirements.length;
        int[] res = new int[length];
        int days = increase.length;
        for (int i = 1; i < days; i++) {
            increase[i][0] += increase[i - 1][0];
            increase[i][1] += increase[i - 1][1];
            increase[i][2] += increase[i - 1][2];
        }
        Utils.printTwoArrays(increase);
        for (int i = 0; i < length; i++) {
            int maxIndex = 0;
            for (int j = 0; j < 3; j++) {
                if(requirements[i][j] != 0) {
                    int index = binarySearch(requirements[i][j], increase, j);
                    maxIndex = Math.max(maxIndex, index);
                }
            }
            if(maxIndex > days) {
                res[i] = -1;
            } else {
                res[i] = maxIndex;
            }
        }
        return res;
    }

    public static int binarySearch(int target, int[][] increase, int n) {
        int left = 0;
        int right = increase.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(increase[mid][n] == target) {

                for (int i = mid; i >= 0 ; i--) {
                    if(increase[i][n] != target) {
                        return i + 2;
                    }
                }
                return 1;
            } else if(increase[mid][n] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left + 1;
    }

    public static void main(String[] args) {
        Utils.printArrays(getTriggerTime(new int[][]{{2,8,4},{2,5,0},{10,9,8}}, new int[][]{{2,11,3},{15,10,7},{9,17,12},{8,1,14}}));
        Utils.printArrays(getTriggerTime(new int[][]{{0,4,5},{4,8,8},{8,6,1},{10,10,0}}, new int[][]{{12,11,16},{20,2,6},{9,2,6},{10,18,3},{8,14,9}}));
        Utils.printArrays(getTriggerTime(new int[][]{{1,1,1}}, new int[][]{{0,0,0}}));
        Utils.printArrays(getTriggerTime(new int[][]{{8,7,3},{7,2,0},{9,8,10},{5,1,4}}, new int[][]{{10,1,13},{8,4,13}}));
        Utils.printArrays(getTriggerTime(new int[][]{
                {4,0,8},{7,7,10},{7,9,8},{3,10,1},{8,6,8},{2,2,0},{4,10,8},{1,5,4},{4,4,10},
                {7,9,8},{3,8,6},{0,0,6},{3,2,9},{4,0,9},{4,8,9},{0,8,2},{5,7,10},{7,10,6},
                {1,4,6},{3,10,0},{8,2,5},{3,1,0},{7,0,7},{9,10,3},{2,4,2},{2,3,4},{0,7,7},
                {0,9,4},{9,3,7},{1,10,7},{2,7,2},{3,9,2},{0,9,7},{1,9,10},{3,2,8},{9,9,5},
                {5,9,7},{1,2,9},{10,5,7},{10,2,6},{8,0,10},{7,9,5},{6,10,3},{1,7,0},{8,2,2},
                {3,3,6},{0,4,8},{8,0,1},{7,0,6},{6,3,6},{6,0,1},{3,10,6},{9,3,0},{9,4,3},
                {8,1,6},{10,9,0},{4,5,10},{2,9,6},{8,2,5},{6,9,3},{1,2,1},{3,8,2},{7,9,4},
                {8,6,7},{10,5,8},{2,6,2},{3,6,9},{4,1,6},{8,10,6},{3,2,1},{0,8,7},{4,6,4},
                {3,4,8},{3,4,9},{2,8,3},{6,8,9},{8,5,0},{9,9,7},{1,7,4},{8,7,3},{0,9,3},{3,8,10},
                {4,7,9},{8,8,7},{9,6,9},{8,0,5},{0,4,3},{5,10,3},{9,6,4}},
                new int[][]{
                        {99,92,57},{53,76,42},{81,18,36},{34,76,33},{55,97,77},{19,41,94},
                        {82,49,99},{97,58,24},{66,47,50},{15,86,67},{43,71,80},{83,29,16},
                        {21,101,57},{19,34,22},{12,30,35},{96,89,94},{42,89,89},{87,98,43},
                        {82,95,68},{97,17,83},{100,87,19},{99,74,73},{87,47,54},{66,11,16},
                        {86,96,21},{63,42,25},{65,35,27},{98,32,88},{54,32,91},{24,15,34},
                        {19,44,16},{40,95,81},{42,52,92},{64,88,58},{33,51,39},{40,26,66},
                        {74,88,96},{46,98,83},{42,60,31},{39,72,85},{65,90,34},{31,24,44},
                        {81,11,27},{34,97,60},{98,54,10},{13,33,42},{58,95,10},{46,50,59},
                        {45,71,40},{11,52,11},{56,27,24},{85,41,72},{10,34,56},{99,38,77},
                        {21,80,100},{50,52,100},{61,55,57},{100,83,36},{71,84,65},{29,86,35},
                        {77,61,49},{76,41,98},{37,99,79},{99,73,86}
        }));

    }
}
