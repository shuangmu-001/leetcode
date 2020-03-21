package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:21 AM 2020/3/20
 * <a href="https://leetcode.com/problems/all-elements-in-two-binary-search-trees/">
 *     All Elements in Two Binary Search Trees</a>
 * @see com.leetcode.graph.tree.bt.MorrisTraversal
 */
public class AllElementsInTwoBST {
    /**
     * Given two binary search trees root1 and root2.
     * Return a list containing all the integers from both trees sorted in ascending order.
     *
     * Example 1:
     *      Input: root1 = [2,1,4], root2 = [1,0,3]
     *      Output: [0,1,1,2,3,4]
     *
     * Example 2:
     *      Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
     *      Output: [-10,0,0,1,2,5,7,10]
     *
     * Example 3:
     *      Input: root1 = [], root2 = [5,1,7,0,2]
     *      Output: [0,1,2,5,7]
     *
     * Example 4:
     *      Input: root1 = [0,-10,10], root2 = []
     *      Output: [-10,0,10]
     *
     * Example 5:
     *      Input: root1 = [1,null,8], root2 = [8,1]
     *      Output: [1,1,8,8]
     *
     * Constraints:
     *      Each tree has at most 5000 nodes.
     *      Each node's value is between [-10^5, 10^5].
     *
     */

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> results = new ArrayList<>();
        TreeNode cur1 = root1;
        TreeNode cur2 = root2;
        while (cur1 != null && cur2 != null) {
            if(cur1.left == null && cur2.left == null) {
                if(cur1.val < cur2.val) {
                    results.add(cur1.val);
                    cur1 = cur1.right;
                } else {
                    results.add(cur2.val);
                    cur2 = cur2.right;
                }
            }else {
                cur1 = getCur(cur1);
                cur2 = getCur(cur2);
            }

        }
        addElements(cur1, results);
        addElements(cur2, results);
        return results;
    }

    public void addElements(TreeNode root, List<Integer> result) {
        TreeNode cur = root;
        TreeNode before;
        while (cur != null) {
            if(cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                before = cur.left;
                while(before.right != null) {
                    before = before.right;
                }
                before.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }
        }
    }

    public TreeNode getCur(TreeNode root) {
        if(root == null ) {
            return null;
        }
        if(root.left == null) {
            return root;
        }
        TreeNode before = root.left;
        while (before.right != null) {
            before = before.right;
        }
        before.right = root;
        TreeNode temp = root;
        root = root.left;
        temp.left = null;
        return root;
    }
}
