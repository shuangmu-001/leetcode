package com.data.structure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zms
 * @date 10:53 上午 2021/10/8
 */
public class GraphImpl implements Graph {

    public Map<Integer, Node> nodes;

    public Set<Edge> edges;

    public GraphImpl() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    @Override
    public int vertexes() {
        return nodes.size();
    }

    @Override
    public int edges() {
        return edges.size();
    }

    @Override
    public void addEdge(int v, int w) {

    }

    @Override
    public Iterable<Integer> adj(int v) {
        return null;
    }
}
