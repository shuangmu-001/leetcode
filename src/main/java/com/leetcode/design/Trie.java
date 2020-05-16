package com.leetcode.design;

import java.util.*;

/**
 * @author wcl
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

    Set<String> set = new HashSet<>();
    Map<Character, Node> nodes = new HashMap<>();
    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Set<Character> childs = nodes.keySet();
        Map<Character, Node> prefixNodes = nodes;
        int index = 0;
        while(index < word.length() && childs.contains(chars[index])) {
            Node node = prefixNodes.get(chars[index]);
            prefixNodes = node.nodes;
            childs = prefixNodes.keySet();
            index++;
        }
        for (int i = index; i < chars.length; i++) {
            Node node = new Node(chars[i]);
            prefixNodes.put(chars[i], node);
            prefixNodes = node.nodes;
        }

        set.add(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return set.contains(word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(set.contains(prefix)) {
            return true;
        }
        char[] chars = prefix.toCharArray();
        Set<Character> childs = nodes.keySet();
        Map<Character, Node> prefixNodes = nodes;
        int index = 0;
        while(index < prefix.length() && childs.contains(chars[index])) {
            Node node = prefixNodes.get(chars[index]);
            prefixNodes = node.nodes;
            childs = prefixNodes.keySet();
            index++;
        }
        return index >= prefix.length();
    }

    static class Node {
        char c;
        Map<Character, Node> nodes;

        public Node(char c) {
            this.c = c;
            nodes = new HashMap<>();
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

