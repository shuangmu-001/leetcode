package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zms
 * @date 12:42 PM 2020/3/26
 * <a href="https://leetcode.com/problems/positions-of-large-groups/">
 *     Positions of Large Groups</a>
 */
public class PositionsOfLargeGroups {
    /**
     * In a string S of lowercase letters, these letters form consecutive groups of the same character.
     * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
     * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
     * The final answer should be in lexicographic order.
     *
     * Example 1:
     *      Input: "abbxxxxzzy"
     *      Output: [[3,6]]
     *      Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
     *
     * Example 2:
     *      Input: "abc"
     *      Output: []
     *      Explanation: We have "a","b" and "c" but no large group.
     *
     * Example 3:
     *      Input: "abcdddeeeeaabbbcd"
     *      Output: [[3,5],[6,9],[12,14]]
     *  
     *
     * Note:  1 <= S.length <= 1000
     */
    public static List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> results = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < S.length(); i++) {
            if(i == S.length() - 1 || S.charAt(i + 1) != S.charAt(start)) {
                if((i - start + 1) >= 3) {
                    results.add(Arrays.asList(start, i));
                }
                start = i + 1;
            }

        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(largeGroupPositions("abcdddeeeeaabbbcd"));
        System.out.println(largeGroupPositions("abc"));
        System.out.println(largeGroupPositions("abbxxxxzzy"));
        System.out.println(largeGroupPositions("aaa"));
    }
}
