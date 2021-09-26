package com.leetcode.greedy;

/**
 * @author zms
 * @date 6:24 下午 2020/9/8
 * <a href="https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/">
 * Minimum Deletion Cost to Avoid Repeating Letters</a>
 */
public class MinimumDeletionCostToAvoidRepeatingLetters {
    /**
     * Given a string s and an array of integers cost where cost[i] is the cost of deleting the character i in s.
     * Return the minimum cost of deletions such that there are no two identical letters next to each other.
     * Notice that you will delete the chosen characters at the same time,
     * in other words, after deleting a character, the costs of deleting other characters will not change.
     * <p>
     * Example 1:
     * Input: s = "abaac", cost = [1,2,3,4,5]
     * Output: 3
     * Explanation: Delete the letter "a" with cost 3 to get "abac" (String without two identical letters next to each other).
     * <p>
     * Example 2:
     * Input: s = "abc", cost = [1,2,3]
     * Output: 0
     * Explanation: You don't need to delete any character because there are no identical letters next to each other.
     * <p>
     * Example 3:
     * Input: s = "aabaa", cost = [1,2,3,4,1]
     * Output: 2
     * Explanation: Delete the first and the last character, getting the string ("aba").
     * <p>
     * Constraints:
     * <p>
     * s.length == cost.length
     * 1 <= s.length, cost.length <= 10^5
     * 1 <= cost[i] <= 10^4
     * s contains only lowercase English letters.
     */
    public static int minCost1(String s, int[] cost) {
        int res = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("start : " + start);
            if (s.charAt(start) != s.charAt(i) || i == s.length() - 1) {
                i = i == s.length() - 1 && s.charAt(start) == s.charAt(i)? i + 1 : i;
                int cur = cost[start];
                for (int j = start + 1; j < i; j++) {
                    System.out.println(cur);
                    res += Math.min(cur, cost[j]);
                    cur = Math.max(cur, cost[j]);
                }
                start = i;
            }
        }
        return res;
    }

    public static int minCost(String s, int[] cost) {
        int res = 0;
        int cur = cost[0];
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                res += Math.min(cur, cost[i + 1]);
                System.out.println(res + " : res + cur :" + cur);
                cur = Math.max(cur, cost[i + 1]);
            } else {
                cur = cost[i + 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(minCost("aaabbbabbbb", new int[]{3,5,10,7,5,3,5,5,4,8,1}));
        System.out.println(minCost("aaaaaaaaaaaaaa", new int[]{1,3,6,5,4,5,4,4,2,8,3,10,6,6}));
    }
}
