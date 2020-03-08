package com.leetcode.graph.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 6:01 PM 2020/3/8
 * {@link "https://leetcode.com/problems/n-ary-tree-preorder-traversal/"}
 * @see TreeTraversal
 */
public class NAryTreePreorderTraversal {
    /**
     * Given an n-ary tree, return the preorder traversal of its nodes' values.
     *
     * Nary-Tree input serialization is represented in their level order traversal,
     * each group of children is separated by the null value (See examples).
     *
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     * Example 1:
     *      Input: root = [1,null,3,2,4,null,5,6]
     *      Output: [1,3,5,6,2,4]
     *
     * Example 2:
     *      Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     *      Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     *
     * Constraints:
     *      The height of the n-ary tree is less than or equal to 1000
     *      The total number of nodes is between [0, 10^4]
     */
    public List<Integer> preorder(Node root) {
        if(root == null) {
            return null;
        }
        List<Integer> results = new ArrayList<>();
        preOrder(root, results);
        return null;
    }

    public void preOrder(Node root, List<Integer> results) {
        results.add(root.val);
        if(root.children != null) {
            List<Node> childrens = root.children;
            for (int i = 0; i < childrens.size(); i++) {
                preOrder(childrens.get(i), results);
            }
        }
    }
}
