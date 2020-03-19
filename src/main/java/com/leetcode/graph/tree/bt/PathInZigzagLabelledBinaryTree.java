package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wcl
 * @date 11:32 AM 2020/3/19
 * <a href="https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/">
 *      Path In Zigzag Labelled Binary Tree</a>
 */
public class PathInZigzagLabelledBinaryTree {
    /**
     * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
     * In the odd numbered rows (ie., the first, third, fifth,...),
     * the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...),
     * the labelling is right to left.
     * Given the label of a node in this tree,
     * return the labels in the path from the root of the tree to the node with that label.
     *
     * Example 1:
     *      Input: label = 14
     *      Output: [1,3,4,14]
     *
     * Example 2:
     * Input: label = 26
     * Output: [1,2,6,10,26]
     *
     * Constraints:1 <= label <= 10^6
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new LinkedList<>();
        while(label >= 1 ) {
            result.add(0, label);
            label = label>>1;
        }
        int i = result.size();
        if((i & 1) != 0) {
            i = 1;
        } else {
            i = 2;
        }
        while (i < result.size()) {
            int integer = result.get(i);
            result.set(i, (1 << i) + (1<<(i + 1)) - 1 - integer);
            i += 2;
        }

        return result;
    }

}
