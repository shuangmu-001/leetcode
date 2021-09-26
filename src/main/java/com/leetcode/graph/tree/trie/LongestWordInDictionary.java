package com.leetcode.graph.tree.trie;


/**
 * @author zms
 * @date 5:30 下午 2020/9/17
 * <a href="https://leetcode.com/problems/longest-word-in-dictionary/">
 *     Longest Word in Dictionary</a>
 */
public class LongestWordInDictionary {
    /**
     * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
     *
     * If there is no answer, return the empty string.
     * Example 1:
     * Input:
     * words = ["w","wo","wor","worl", "world"]
     * Output: "world"
     * Explanation:
     * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
     * Example 2:
     * Input:
     * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     * Output: "apple"
     * Explanation:
     * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
     * Note:
     *
     * All the strings in the input will only contain lowercase letters.
     * The length of words will be in the range [1, 1000].
     * The length of words[i] will be in the range [1, 30].
     */
    String res = "";
    public String longestWord(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }
        TrieNode root = new TrieNode(' ');
        for(String str : words) {
            insert(root, str);
        }
        dfs(root);
        return res;
    }

    public void dfs(TrieNode root) {
        if(root == null) {
            return;
        }
        TrieNode[] trieNodes = root.trieNodes;
        for (TrieNode node : trieNodes) {
            if(node != null && node.world) {
                if(res.length() < node.str.length()) {
                    res = node.str;
                }
                dfs(node);
            }
        }
    }

    public void insert(TrieNode root, String word) {
        TrieNode dummy = root;
        for(char c : word.toCharArray()) {
            TrieNode[] trieNodes = dummy.trieNodes;
            if(trieNodes[c - 'a'] == null) {
                trieNodes[c - 'a'] = new TrieNode(c);
            }
            dummy = trieNodes[c - 'a'];
        }
        dummy.world = true;
        dummy.str = word;
    }

    static class TrieNode {

        char c;

        TrieNode[] trieNodes = new TrieNode[26];

        boolean world;

        String str;

        public TrieNode(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        System.out.println("acd".equals(new LongestWordInDictionary().longestWord(new String[]{"a","ab","ac","acd"})));
        System.out.println("".equals(new LongestWordInDictionary().longestWord(new String[]{"aa","ab","ac","acd"})));
        System.out.println("wc".equals(new LongestWordInDictionary().longestWord(new String[]{"w","wc","wbr","wcrl","world"})));
        System.out.println("wb".equals(new LongestWordInDictionary().longestWord(new String[]{"w","wc","wb","wcrl","world"})));
    }
}
