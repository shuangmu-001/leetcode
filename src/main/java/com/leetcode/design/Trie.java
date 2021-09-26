package com.leetcode.design;

import java.util.*;

/**
 * @author zms
 * @date 4:55 PM 2020/5/14
 * <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">
 *  Implement Trie (Prefix Tree)</a>
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * TODO 字典树
 */
public class Trie {

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode dummy = root;
        for(char c : word.toCharArray()) {
            TrieNode[] trieNodes = dummy.trieNodes;
            if(trieNodes[c - 'a'] == null) {
                trieNodes[c - 'a'] = new TrieNode(c);
            }
            dummy = trieNodes[c - 'a'];
        }
        dummy.world = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode dummy = root;
        for(char c : word.toCharArray()) {
            TrieNode[] trieNodes = dummy.trieNodes;
            if(trieNodes[c - 'a'] == null) {
                return false;
            }
            dummy = trieNodes[c - 'a'];
        }
        return dummy.world;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode dummy = root;
        for(char c : prefix.toCharArray()) {
            TrieNode[] trieNodes = dummy.trieNodes;
            if(trieNodes[c - 'a'] == null) {
                return false;
            }
            dummy = trieNodes[c - 'a'];
        }
        return true;
    }

    static class TrieNode {

        char c;

        TrieNode[] trieNodes = new TrieNode[26];

        boolean world;

        public TrieNode(char c) {
            this.c = c;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

