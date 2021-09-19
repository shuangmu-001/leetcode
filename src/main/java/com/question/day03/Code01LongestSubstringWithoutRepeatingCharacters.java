package com.question.day03;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">
 * Longest Substring Without Repeating Characters</a>
 *
 * @author wcl
 * @date 3:07 下午 2021/9/19
 */
public class Code01LongestSubstringWithoutRepeatingCharacters {

    // 求一个字符串中，最长无重复字符子串的长度
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len <= 1) {
            return len;
        }
        // 当前字符最近的节点
        // 前一个节点为最终节点的最长字串
        int res = 1;
        Map<Character, Integer> preIndex = new HashMap<>();
        int[] dp = new int[len];
        dp[0] = 1;
        preIndex.put(s.charAt(0), 0);
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if(!preIndex.containsKey(c)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int index = preIndex.get(c);
                // 判断index是否在前一个节点的最长字串里面
                // dbacd
                if(i - 1 - index < dp[i - 1]) {
                    dp[i] = i - index;
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            res = Math.max(res, dp[i]);
            preIndex.put(c, i);
        }
        return res;
    }
    // TODO 对数器 暴力破解
//    public int lengthOfLongestSubstring1(String s) {
//
//    }
}
