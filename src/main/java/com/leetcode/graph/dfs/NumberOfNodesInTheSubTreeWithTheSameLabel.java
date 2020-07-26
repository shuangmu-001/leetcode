package com.leetcode.graph.dfs;

import com.leetcode.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author wcl
 * @date 4:43 下午 2020/7/24
 * <a href="https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/">
 * Number of Nodes in the Sub-Tree With the Same Label</a>
 * TODO graph的遍历 DFS BFS
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
     * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that any node is part of its sub-tree.
     * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
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
    public static int[] countSubTrees(int n, int[][] edges, String labels) {


        Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));
        int[][] allCount = new int[n][26];
        int[] result = new int[n];

        for (int i = edges.length - 1; i >= 0; i--) {
            int index = edges[i][1];
            int parent = edges[i][0];
            char c = labels.charAt(index);
            for (int j = 0; j < 26; j++) {
                allCount[parent][j] += allCount[index][j];
                if (c - 'a' == j) {
                    allCount[parent][j]++;
                }
            }
//            result[index] = allCount[index][c - 'a'] + 1;
        }

        for (int i = 0; i < n; i++) {
            char c = labels.charAt(i);
            result[i] = allCount[i][c - 'a'] + 1;
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(countSubTrees(7, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}}, "aaabaaa")));
//        System.out.println(Arrays.toString(countSubTrees(7, new int[][]{{0, 1}, {0, 2}, {2, 3}, {1, 4}, {1, 5}, {2, 6}}, "abaedcd")));
//        System.out.println(Arrays.toString(countSubTrees(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, "bbbb")));
//        System.out.println(Arrays.toString(countSubTrees(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {0, 4}}, "aabab")));
//        System.out.println(Arrays.toString(countSubTrees(6, new int[][]{{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}}, "cbabaa")));


        System.out.println(Arrays.toString(countSubTrees(4, new int[][]{{0, 2}, {0, 3}, {1, 2}}, "aeed")));

    }
}
