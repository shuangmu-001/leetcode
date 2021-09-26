package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zms
 * @date 11:09 上午 2020/8/28
 * <a herf="https://leetcode.com/problems/most-visited-sector-in-a-circular-track/">
 * Most Visited Sector in a Circular Track</a>
 */
public class MostVisitedSectorInACircularTrack {
    /**
     * Given an integer n and an integer array rounds. We have a circular track which consists of n sectors labeled from 1 to n. A marathon will be held on this track, the marathon consists of m rounds. The ith round starts at sector rounds[i - 1] and ends at sector rounds[i]. For example, round 1 starts at sector rounds[0] and ends at sector rounds[1]
     * <p>
     * Return an array of the most visited sectors sorted in ascending order.
     * <p>
     * Notice that you circulate the track in ascending order of sector numbers in the counter-clockwise direction (See the first example).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: n = 4, rounds = [1,3,1,2]
     * Output: [1,2]
     * Explanation: The marathon starts at sector 1. The order of the visited sectors is as follows:
     * 1 --> 2 --> 3 (end of round 1) --> 4 --> 1 (end of round 2) --> 2 (end of round 3 and the marathon)
     * We can see that both sectors 1 and 2 are visited twice and they are the most visited sectors. Sectors 3 and 4 are visited only once.
     * Example 2:
     * <p>
     * Input: n = 2, rounds = [2,1,2,1,2,1,2,1,2]
     * Output: [2]
     * Example 3:
     * <p>
     * Input: n = 7, rounds = [1,3,5,7]
     * Output: [1,2,3,4,5,6,7]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 2 <= n <= 100
     * 1 <= m <= 100
     * rounds.length == m + 1
     * 1 <= rounds[i] <= n
     * rounds[i] != rounds[i + 1] for 0 <= i < m
     */
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> results = new ArrayList<>();
        int length = rounds.length;
        int start = rounds[0];
        int end = rounds[length - 1];
        if (end >= start) {
            for (int i = start; i <= end; i++) {
                results.add(i);
            }
        } else {
            for (int i = 1; i <= end; i++) {
                results.add(i);
            }
            for (int i = start; i <= n; i++) {
                results.add(i);
            }
        }
        return results;
    }

}
