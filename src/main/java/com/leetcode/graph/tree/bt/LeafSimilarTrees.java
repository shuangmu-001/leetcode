package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 1:50 PM 2020/3/9
 * {@link "https://leetcode.com/problems/leaf-similar-trees/"}
 */
public class LeafSimilarTrees {
    /**
     * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8). (最下面的元素)
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     * Note:Both of the given trees will have between 1 and 100 nodes.
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<>();
        postOrder(root1, seq1);
        List<Integer> seq2 = new ArrayList<>();
        postOrder(root2, seq2);
        if(seq1.size() != seq2.size()) {
            return false;
        }
        for (int i = 0; i < seq1.size(); i++) {
            if(seq1.get(i).compareTo(seq2.get(i)) != 0) {
               return false;
            }
        }
        return true;
    }

    public void postOrder(TreeNode root, List<Integer> sequences) {

        if(root.left != null) {
            postOrder(root.left, sequences);
        }
        if(root.right != null) {
            postOrder(root.right, sequences);
        } else if(root.left == null) {
            sequences.add(root.val);
        }
    }

}
