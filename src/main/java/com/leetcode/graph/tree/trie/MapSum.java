package com.leetcode.graph.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 3:51 下午 2020/9/18
 * <a href="https://leetcode.com/problems/map-sum-pairs/">Map Sum Pairs</a>
 */
public class MapSum {
    Map<String, Integer> map = new HashMap<>();
    TrieNode root = new TrieNode();
    /** Initialize your data structure here. */
    public MapSum() {

    }
    /**
     * Your MapSum object will be instantiated and called as such:
     * MapSum obj = new MapSum();
     * obj.insert(key,val);
     * int param_2 = obj.sum(prefix);
     */
    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode dummy = root;
        dummy.val += delta;
        for (char c : key.toCharArray()) {
            Map<Character, TrieNode> nodes = dummy.nodes;
            if(!nodes.containsKey(c)) {
                nodes.put(c, new TrieNode());
            }
            dummy = nodes.get(c);
            dummy.val += delta;
        }
    }
    public int sum(String prefix) {
        TrieNode dummy = root;
        for (char c : prefix.toCharArray()) {
            Map<Character, TrieNode> nodes = dummy.nodes;
            if(!nodes.containsKey(c)) {
                return 0;
            }
            dummy = nodes.get(c);
        }
        return dummy.val;
    }

    static class TrieNode {
        Map<Character, TrieNode> nodes = new HashMap<>();
        int val;
    }
}

