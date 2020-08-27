package com.leetcode.graph;

/**
 * @author wcl
 * @date 9:38 下午 2020/7/25
 * TODO 如何表示一张图
 */
public interface Graph {

    /**
     * 顶点数
     */
    int vertexes();

    /**
     * 边数
     */
    int edges();

    /**
     * 添加一条边v-w
     */
    void addEdge(int v, int w);

    /**
     * 和v相邻的所有顶点
     */
    Iterable<Integer> adj(int v);
}
