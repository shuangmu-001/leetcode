package com.leetcode.graph.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 6:39 下午 2020/9/18
 * TODO <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">
 *     Design Add and Search Words Data Structure</a>
 */
public class WordDictionary {
    TrieNode root = new TrieNode();
    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode dummy = root;
        for(char c : word.toCharArray()) {
            Map<Character, TrieNode> nodes = dummy.nodes;
            nodes.putIfAbsent(c, new TrieNode());
            dummy = nodes.get(c);
        }
        dummy.isWord = true;
    }

    /**
     * Returns if the word is in the data structure.
     * A word could contain the dot character '.' to represent any one letter.
     */
    boolean res;
    public boolean search(String word) {
        res = false;
        dfs(word, 0, root);
        return res;
    }
    public void dfs(String word, int index, TrieNode root) {
        if(res || index >= word.length()) {
            res = res || root.isWord;
            return;
        }
        Map<Character, TrieNode> nodes = root.nodes;
        char c = word.charAt(index);
        if(c == '.') {
            nodes.forEach((key, value) -> dfs(word, index + 1, value));
        } else if(nodes.containsKey(c)) {
            TrieNode node = nodes.get(c);
            dfs(word, index + 1, node);
        }
    }
    static class TrieNode {
        Map<Character, TrieNode> nodes = new HashMap<>();
        boolean isWord = false;
    }
    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     */
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println(!obj.search("pad"));
        System.out.println(obj.search("bad"));
        System.out.println(obj.search(".ad"));
        System.out.println(obj.search("b.."));
    }
}
