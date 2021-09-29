package com.question.day07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zms
 * @date 10:54 上午 2021/9/26
 */
public class Code05WorldBreak {

    // 假设所有字符都是小写字母. 大字符串是str. arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
    // 使用arr中的单词有多少种拼接str的方式. 返回方法数.
    public int ways01(String str, String[] arr) {
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        return process01(str, 0, set);
    }

    private int process01(String str, int index, Set<String> set) {
        int length = str.length();
        if (index >= length) {
            return 1;
        }
        int ans = 0;
        for (int i = index; i < length; i++) {
            String s = str.substring(index, i + 1);
            if (set.contains(s)) {
                ans += process01(str, i + 1, set);
            }
        }
        return ans;
    }

    public int ways(String str, String[] arr) {
        Trie trie = new Trie();
        for (String s : arr) {
            trie.add(s);
        }
        Node root = trie.root;
        return process(str, 0, root);
    }

    private int process(String str, int index, Node root) {
        int length = str.length();
        if (index >= length) {
            return 1;
        }
        int ans = 0;
        Node node = root;
        for (int i = index; i < length; i++) {
            char c = str.charAt(i);
            if (node.next[c - 'a'] == null) {
                break;
            }
            node = node.next[c - 'a'];
            if (node.end) {
                ans += process(str, i + 1, root);
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

    public int ways02(String str, String[] arr) {
        Trie trie = new Trie();
        for (String s : arr) {
            trie.add(s);
        }
        Node root = trie.root;
        // i ~ len
        int length = arr.length;
        int[] dp = new int[length + 1];
        dp[length] = 1;
        for (int i = length - 1; i >= 0; i--) {
            Node node = root;
            for (int j = i; j < length; j++) {
                char c = str.charAt(j);
                if (node.next[c - 'a'] == null) {
                    break;
                }
                node = node.next[c - 'a'];
                if (node.end) {
                    dp[i] += dp[j + 1];
                }
            }
        }
        return dp[0];
    }
}
