package com.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/word-break/">Word Break</a>
 *
 * @author wcl
 * @date 10:34 下午 2020/9/29
 */
public class WordBreak {
    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * <p>
     * Note:
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * <p>
     * Example 1:
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * <p>
     * Example 2:
     * Input: s = "applepenapple", wordDict = ["apple", "pen"]
     * Output: true
     * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     * Note that you are allowed to reuse a dictionary word.
     * <p>
     * Example 3:
     * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output: false
     * <p>
     * Constraints:
     * 1 <= s.length <= 300
     * 1 <= wordDict.length <= 1000
     * 1 <= wordDict[i].length <= 20
     * s and wordDict[i] consist of only lowercase English letters.
     * All the strings of wordDict are unique.
     */
    public static boolean wordBreak01(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String str : wordDict) {
            trie.add(str);
        }
        return process(s, 0, trie.root);
    }

    private static boolean process(String str, int index, Node root) {
        int length = str.length();
        if (index >= length) {
            return true;
        }
        boolean ans = false;
        Node node = root;
        for (int i = index; i < length; i++) {
            char c = str.charAt(i);
            if (node.next[c - 'a'] == null) {
                break;
            }
            node = node.next[c - 'a'];
            if (node.end) {
                ans |= process(str, i + 1, root);
            }
        }
        return ans;
    }

    private static class Node {
        private boolean end;
        private final Node[] next = new Node[26];
    }

    private static class Trie {

        private final Node root = new Node();

        public void add(String str) {
            Node node = this.root;
            for (char c : str.toCharArray()) {
                int index = c - 'a';
                Node[] next = node.next;
                next[index] = next[index] != null ? next[index] : new Node();
                node = next[index];
            }
            node.end = true;
        }
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String str : wordDict) {
            trie.add(str);
        }
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[length] = true;
        for (int i = length - 1; i >= 0; i--) {
            Node node = trie.root;
            for (int j = i; j < length; j++) {
                int index = s.charAt(j) - 'a';
                if (node.next[index] == null) {
                    break;
                }
                node = node.next[index];
                if (node.end) {
                    dp[i] |= dp[j + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(!wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
