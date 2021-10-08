package com.data.structure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 10:52 上午 2021/10/8
 */
public class Node {

    public int value;

    public int in;

    public int out;

    public List<Node> nexts;

    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
