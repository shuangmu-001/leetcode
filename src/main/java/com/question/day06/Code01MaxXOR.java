package com.question.day06;

import com.Test;

import java.util.Arrays;

/**
 * @author zms
 * @date 3:23 下午 2021/9/24
 */
public class Code01MaxXOR implements Test {

    // 数组种所有数都异或起来的结果，叫做异或和，
    // 给定一个数组arr，返回arr的最大子数组异或和
    public int maxXorSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int pre = 0;
            for (int j = i; j < length; j++) {
                pre ^= arr[j];
                ans = Math.max(pre, ans);
            }
        }
        return ans;
    }

    public int maxXorSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Trie trie = new Trie();
        int ans = 0;
        int xor = 0;
        for (int j : arr) {
            trie.add(xor);
            xor ^= j;
            // trie.maxXor 获取当前位置为底的子数组 中的最大异或和
            ans = Math.max(ans, trie.maxXor(xor));
        }
        return ans;
    }

    public static class Node {
        public Node[] next = new Node[2];
    }

    public static class Trie {

        private final Node root = new Node();

        // 构建前缀树
        public void add(int xor) {
            Node[] next = root.next;
            for (int i = 31; i >= 0; i--) {
                int path = ((xor >> i) & 1);
                next[path] = next[path] == null ? new Node() : next[path];
                next = next[path].next;
            }
        }

        public int maxXor(int xor) {
            int ans = 0;
            Node[] next = root.next;
            for (int i = 31; i >= 0; i--) {
                int path = ((xor >> i) & 1);
                int best = i == 31 ? path : (path ^ 1);
                best = next[best] == null ? (best ^ 1) : best;
                ans |= ((path ^ best) << i);
                next = next[best].next;
            }
            return ans;
        }
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int ans01 = maxXorSubArray1(arr);
        int ans02 = maxXorSubArray(arr);
        if (ans01 != ans02) {
            System.out.println(Arrays.toString(arr));
            System.out.println(ans01);
            System.out.println(ans02);
        }
    }
}
