package com.leetcode.twopoints;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 10:45 上午 2020/9/5
 * <a href="https://leetcode.com/problems/partition-labels/">Partition Labels</a>
 */
public class PartitionLabels {
    /**
     * A string s of lowercase English letters is given.
     * We want to partition this string into as many parts as possible
     * so that each letter appears in at most one part,
     * and return a list of integers representing the size of these parts.
     *
     * Example 1:
     * Input: s = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
     *
     *
     * Note:
     *
     * s will have length in range [1, 500].
     * s will consist of lowercase English letters ('a' to 'z') only.
     */
    public static List<Integer> partitionLabels01(String s) {
        List<Integer> result = new ArrayList<>();
        if(s == null) {
            return result;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lastIndex = s.lastIndexOf(s.charAt(i));
            if(lastIndex > end) {
                end = lastIndex;
            }
            if(i == end) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        if(s == null) {
            return result;
        }
        int[] ends = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ends[s.charAt(i) - 'a'] = i;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lastIndex = ends[s.charAt(i) - 'a'];
            if(lastIndex > end) {
                end = lastIndex;
            }
            if(i == end) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
