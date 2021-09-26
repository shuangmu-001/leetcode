package com.leetcode.graph.tree.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zms
 * @date 11:54 上午 2020/9/18
 * <a href="https://leetcode.com/problems/implement-magic-dictionary/">MagicDictionary</a>
 */
public class MagicDictionary {
    /**
     * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
     *
     * Implement the MagicDictionary class:
     *
     * MagicDictionary() Initializes the object.
     * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
     * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
     *
     *
     * Example 1:
     *
     * Input
     * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
     * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
     * Output
     * [null, null, false, true, false, false]
     *
     * Explanation
     * MagicDictionary magicDictionary = new MagicDictionary();
     * magicDictionary.buildDict(["hello", "leetcode"]);
     * magicDictionary.search("hello"); // return False
     * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
     * magicDictionary.search("hell"); // return False
     * magicDictionary.search("leetcoded"); // return False
     *
     *
     * Constraints:
     *
     * 1 <= dictionary.length <= 100
     * 1 <= dictionary[i].length <= 100
     * dictionary[i] consists of only lower-case English letters.
     * All the strings in dictionary are distinct.
     * 1 <= searchWord.length <= 100
     * searchWord consists of only lower-case English letters.
     * buildDict will be called only once before search.
     * At most 100 calls will be made to search.
     */
    TrieNode root = new TrieNode();
    /** Initialize your data structure here. */
    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode dummy = root;
            for (char c : word.toCharArray()) {
                Map<Character, TrieNode> nodes = dummy.nodes;
                if(!nodes.containsKey(c)) {
                    nodes.put(c, new TrieNode());
                }
                dummy = nodes.get(c);
            }
            dummy.isWord = true;
        }
    }
    boolean res = false;
    public boolean search(String searchWord) {
        res = false;
        dfs(searchWord, 0, 0, root);
        return res;
    }

    public void dfs(String search, int index, int count, TrieNode root) {

        if(res) {
            return;
        }
        char c = search.charAt(index);
        Map<Character, TrieNode> nodes = root.nodes;
        if(index == search.length() - 1) {
            if(count >= 1) {
                if(nodes.containsKey(c)) {
                    TrieNode trieNode = nodes.get(c);
                    res = res || trieNode.isWord;
                }
            } else {
                Set<Map.Entry<Character, TrieNode>> entries = nodes.entrySet();
               for (Map.Entry<Character, TrieNode> entry : entries) {
                   if(entry.getKey() == c) {
                       continue;
                   }
                   TrieNode value = entry.getValue();
                   if(value.isWord) {
                       res = true;
                       return;
                   }
               }
            }
        } else {
            if(count >= 1) {
                TrieNode node = nodes.get(c);
                if(node != null) {
                    dfs(search, index + 1, count, node);
                }
            } else {
                Set<Map.Entry<Character, TrieNode>> entries = nodes.entrySet();
                for(Map.Entry<Character, TrieNode> entry : entries) {
                    if(entry.getKey() == c) {
                        dfs(search, index + 1, count, entry.getValue());
                    } else {
                        dfs(search, index + 1, count + 1, entry.getValue());
                    }
                }
            }
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> nodes = new HashMap<>();
        boolean isWord;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"hhllo","leetcode", "hhelo"});
        System.out.println(md.search("hello"));
        System.out.println(md.search("hhllo"));
        System.out.println(!md.search("hell"));
        System.out.println(!md.search("leetcoded"));
    }

}
/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
