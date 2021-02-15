package com.leetcode.graph;

/**
 * @author zms
 * @date 9:41 下午 2021/2/14
 * TODO <a href="https://leetcode.com/problems/is-graph-bipartite/">
 * Is Graph Bipartite?</a>
 */
public class IsGraphBipartite {
    /**
     * Given an undirected graph, return true if and only if it is bipartite.
     * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B,
     * such that every edge in the graph has one node in A and another node in B.
     * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
     * Each node is an integer between 0 and graph.length - 1.
     * There are no self edges or parallel edges: graph[i] does not contain i,
     * and it doesn't contain any element twice.
     *
     * Example 1:
     * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
     * Output: true
     * Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
     *
     * Example 2:
     * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
     * Output: false
     * Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
     *
     * Constraints:
     * 1 <= graph.length <= 100
     * 0 <= graph[i].length < 100
     * 0 <= graph[i][j] <= graph.length - 1
     * graph[i][j] != i
     * All the values of graph[i] are unique.
     * The graph is guaranteed to be undirected.
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visitedG1 = new boolean[n];
        boolean[] visitedG2 = new boolean[n];
        boolean isBipartite = true;
        for (int v = 0; v < n && isBipartite; v++) {
            if (!visitedG1[v] && !visitedG2[v]) {
                isBipartite = dfs(graph, visitedG1, visitedG2, v);
            }
        }
        return isBipartite;
    }

    private boolean dfs(int[][] graph, boolean[] visitedG1, boolean[] visitedG2, int v) {
        visitedG1[v] = true;
        for (int w : graph[v]) {
            if (visitedG1[w]) {
                return false;
            }
            if (!visitedG2[w] && !dfs(graph, visitedG2, visitedG1, w)) {
                return false;
            }
        }
        return true;
    }
}
