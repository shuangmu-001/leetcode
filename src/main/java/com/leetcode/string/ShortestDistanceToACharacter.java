package com.leetcode.string;

/**
 * @author zms
 * @date 9:39 上午 2021/2/8
 * <a href="https://leetcode.com/problems/shortest-distance-to-a-character/">
 * Shortest Distance to a Character </a>
 */
public class ShortestDistanceToACharacter {
    /**
     * Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.
     * <p>
     * Example 1:
     * <p>
     * Input: s = "loveleetcode", c = "e"
     * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
     * Example 2:
     * <p>
     * Input: s = "aaab", c = "b"
     * Output: [3,2,1,0]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 104
     * s[i] and c are lowercase English letters.
     * c occurs at least once in s.
     */
    public int[] shortestToChar1(String s, char c) {
        if (s == null || s.length() == 0) {
            return new int[]{};
        }
        int len = s.length();
        int[] res = new int[len];
        char[] chars = s.toCharArray();
        int beforeIndex = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == c) {
                for (int j = beforeIndex + 1; j <= i; j++) {
                    if (beforeIndex == -1) {
                        res[j] = i - j;
                    } else {
                        res[j] = Math.min(i - j, j - beforeIndex);
                    }
                }
                beforeIndex = i;
            }
        }
        for (int i = beforeIndex + 1; i < len; i++) {
            res[i] = i - beforeIndex;
        }
        return res;
    }

    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        int dist = len;
        int[] res = new int[len];
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (chars[i] == c) {
                int pos = i - 1;
                // i - pos < res[pos]
                // 如果前面有 c 则 beforeIndex到i 是res是从0开始++ 所以i - pos < res[pos] 只执行一半
                // 如果前没有 c 则 一直知道到开始
                while (pos >= 0 && i - pos < res[pos]) {
                    res[pos] = i - pos;
                    pos--;
                }
                dist = 0;
            }
            res[i] = dist++;
        }
        return res;
    }
}
