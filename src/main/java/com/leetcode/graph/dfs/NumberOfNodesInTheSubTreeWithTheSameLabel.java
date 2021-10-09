package com.leetcode.graph.dfs;


import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/">
 * Number of Nodes in the Sub-Tree With the Same Label</a>
 * graph(无向，连通图)的遍历 DFS
 *
 * @author zms
 * @date 4:43 下午 2020/7/24
 */
public class NumberOfNodesInTheSubTreeWithTheSameLabel {
    /**
     * Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
     * <p>
     * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
     * <p>
     * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
     * <p>
     * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
     * Output: [2,1,1,1,1,1,1]
     * Explanation: TreeNode 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that any node is part of its sub-tree.
     * TreeNode 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
     * Example 2:
     * <p>
     * <p>
     * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
     * Output: [4,2,1,1]
     * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
     * The sub-tree of node 3 contains only node 3, so the answer is 1.
     * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
     * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
     * Example 3:
     * <p>
     * <p>
     * Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
     * Output: [3,2,1,1,1]
     * Example 4:
     * <p>
     * Input: n = 6, edges = [[0,1],[0,2],[1,3],[3,4],[4,5]], labels = "cbabaa"
     * Output: [1,2,1,1,2,1]
     * Example 5:
     * <p>
     * Input: n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[4,5],[5,6]], labels = "aaabaaa"
     * Output: [6,5,4,1,3,2,1]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * labels.length == n
     * labels is consisting of only of lower-case English letters.
     */
    static int[] result;
    static boolean[] flags;
    // graph的定义 (无向，无环，连通图)
    static List<Integer>[] graph;

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        graph = new ArrayList[n];
        result = new int[n];
        flags = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        dfs(0, labels);
        return result;
    }

    public static int[] dfs(int point, String labels) {
        int[] count = new int[26];

        flags[point] = true;
        List<Integer> integers = graph[point];
        for (int i : integers) {
            if (!flags[i]) {
                int[] before = dfs(i, labels);
                int index = 0;
                for (int j : before) {
                    count[index++] += j;
                }
            }
        }
        char c = labels.charAt(point);
        count[c - 'a']++;
        result[point] = count[c - 'a'];
        return count;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}};
        String labels = "aaabaaa";
        System.out.println("目标 : [6, 5, 4, 1, 3, 2, 1]; 实际 : " + (get(n, edges, labels)));

        edges = new int[][]{{0, 1}, {0, 2}, {2, 3}, {1, 4}, {1, 5}, {2, 6}};
        labels = "abaedcd";
        System.out.println("目标 : [2, 1, 1, 1, 1, 1, 1]; 实际 : " + (get(n, edges, labels)));

        n = 4;
        edges = new int[][]{{0, 1}, {1, 2}, {0, 3}};
        labels = "bbbb";
        System.out.println("目标 : [4, 2, 1, 1]; 实际 : " + (get(n, edges, labels)));

        n = 5;
        edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {0, 4}};
        labels = "aabab";
        System.out.println("目标 : [3, 2, 1, 1, 1]; 实际 : " + (get(n, edges, labels)));

        n = 6;
        edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}};
        labels = "cbabaa";
        System.out.println("目标 : [1, 2, 1, 1, 2, 1]; 实际 : " + (get(n, edges, labels)));

        n = 4;
        edges = new int[][]{{0, 2}, {0, 3}, {1, 2}};
        labels = "aeed";
        System.out.println("目标 : [1, 1, 2, 1]; 实际 : " + get(n, edges, labels));

    }

    public static String get(int n, int[][] edges, String labels) {
        return Arrays.toString(countSubTrees(n, edges, labels));
    }
}
