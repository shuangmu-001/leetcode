package com.leetcode.graph.tree;

import java.util.List;

/**
 * Definition for a tree node.
 * @author zms
 * @date 6:00 PM 2020/3/8
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
