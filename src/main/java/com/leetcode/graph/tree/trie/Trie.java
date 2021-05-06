package com.leetcode.graph.tree.trie;

/**
 * Definition for a trie tree node.
 * @author wcl
 * @date 2:59 下午 2020/9/17
 */
public class Trie {
    private static final int ALPHABET_SIZE = 256;

    static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        boolean isEndOfWord = false;

        TrieNode() {
            isEndOfWord = false;
        }
    }
}
