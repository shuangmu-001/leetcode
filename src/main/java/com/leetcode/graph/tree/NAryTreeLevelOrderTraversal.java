package com.leetcode.graph.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 8:08 PM 2020/3/21
 * <a href="https://leetcode.com/problems/n-ary-tree-level-order-traversal/">
 *      N-ary Tree Level Order Traversal</a>
 */
public class NAryTreeLevelOrderTraversal {
    /**
     * Given an n-ary tree, return the level order traversal of its nodes' values.
     * Nary-Tree input serialization is represented in their level order traversal,
     * each group of children is separated by the null value (See examples).
     *
     * Example 1:
     *      Input: root = [1,null,3,2,4,null,5,6]
     *      Output: [[1],[3,2,4],[5,6]]
     *
     * Example 2:
     *      Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     *      Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
     *
     * Constraints:
     *      The height of the n-ary tree is less than or equal to 1000
     *      The total number of nodes is between [0, 10^4]
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> results = new ArrayList<>();
        levelOrder(root, 1, results);
        return results;
    }

    public void levelOrder(Node root, int level, List<List<Integer>> results) {
        if(root == null) {
            return;
        }
        if(results.size() < level) {
            results.add(new ArrayList<>());
        }
        results.get(level - 1).add(root.val);
        List<Node> children = root.children;
        for (Node node : children) {
            levelOrder(node, level + 1, results);
        }
    }
}
