package com.question.day06;

/**
 * <a href="https://leetcode.com/problems/maximum-xor-with-an-element-from-array/">
 * Maximum XOR With an Element From Array</a>
 *
 * @author zms
 * @date 4:35 下午 2021/9/24
 */
public class Code03MaximumXORWithAnElementFromArray {

    /**
     * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，
     * 其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     * <p>
     * 示例 1：
     * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
     * 输出：[3,3,7]
     * 解释：
     * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
     * 2) 1 XOR 2 = 3.
     * 3) 5 XOR 2 = 7.
     * <p>
     * 示例 2：
     * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
     * 输出：[15,-1,5]
     * <p>
     * 提示：
     * 1 <= nums.length, queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= nums[j], xi, mi <= 10^9
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie trie = new Trie();
        for (int num : nums) {
            trie.add(num);
        }
        int length = queries.length;
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = trie.maxXor(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    public static class Node {
        int min = Integer.MAX_VALUE;
        Node[] next = new Node[2];
    }

    public static class Trie {

        Node root = new Node();

        public void add(int num) {
            Node[] next = root.next;
            root.min = Math.min(root.min, num);
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                next[path] = next[path] == null ? new Node() : next[path];
                next[path].min = Math.min(num, next[path].min);
                next = next[path].next;
            }
        }

        public int maxXor(int num, int max) {
            if (root.min > max) {
                return -1;
            }
            Node[] next = root.next;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = path ^ 1;
                Node node = next[best];
                if (node == null || node.min > max) {
                    best ^= 1;
                }
                ans |= (path ^ best) << i;
                next = next[best].next;
            }
            return ans;
        }
    }
}
