package com.data.structure.graph;

/**
 * @author zms
 * @date 10:53 上午 2021/10/8
 */
public class Edge {

    public int weight;

    public Node from;

    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
