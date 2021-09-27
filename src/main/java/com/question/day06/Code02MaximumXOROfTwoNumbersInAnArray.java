package com.question.day06;

import com.Test;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/">
 * Maximum XOR of Two Numbers in an Array</a>
 *
 * @author zms
 * @date 4:35 下午 2021/9/24
 */
public class Code02MaximumXOROfTwoNumbersInAnArray implements Test {

    // Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
    public int findMaximumXOR01(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int ans = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int ans = 0;
        int length = nums.length;
        Trie trie = new Trie();
        trie.add(nums[0]);
        for (int i = 1; i < length; i++) {
            ans = Math.max(ans, trie.maxXor(nums[i]));
            trie.add(nums[i]);
        }
        return ans;
    }

    public static class Node {
        private final Node[] next = new Node[2];
    }

    public static class Trie {
        private final Node root = new Node();

        public void add(int num) {
            Node[] next = root.next;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                next[path] = next[path] == null ? new Node() : next[path];
                next = next[path].next;
            }
        }

        public int maxXor(int num) {
            Node[] next = root.next;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = i == 31 ? path : (path ^ 1);
                best = next[best] == null ? (best ^ 1) : best;
                ans |= (best ^ path) << i;
                next = next[best].next;
            }
            return ans;
        }
    }

    @Override
    public void test(int n) {
        int[] arr = genRandomArr(n);
        int ans01 = findMaximumXOR01(arr);
        int ans02 = findMaximumXOR(arr);
        if (ans01 != ans02) {
            System.out.println(Arrays.toString(arr));
            System.out.println(ans01);
            System.out.println(ans02);
        }
    }

}
