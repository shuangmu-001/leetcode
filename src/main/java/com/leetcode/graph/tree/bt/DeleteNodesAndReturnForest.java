package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 * @date 10:19 AM 2020/3/19
 * <a href="https://leetcode.com/problems/delete-nodes-and-return-forest/">
 *     Delete Nodes And Return Forest</a>
 */
public class DeleteNodesAndReturnForest {
    /**
     * Given the root of a binary tree, each node in the tree has a distinct value.
     * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
     * Return the roots of the trees in the remaining forest.  You may return the result in any order.
     *
     * Example 1:
     *      Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
     *      Output: [[1,2,null,4],[6],[7]]
     *
     * Constraints:
     *      The number of nodes in the given tree is at most 1000.
     *      Each node has a distinct value between 1 and 1000.
     *      to_delete.length <= 1000
     *      to_delete contains distinct values between 1 and 1000.
     */
    private int deCount;
    public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {

        List<TreeNode> results = new ArrayList<>();
        TreeNode treeNode = postOrder(root, to_delete, results);
        if(treeNode != null) {
            results.add(treeNode);
        }
        return results;
    }

    public TreeNode postOrder(TreeNode root, int[] to_delete, List<TreeNode> results) {
        if(root == null) {
            return null;
        }
        root.left = postOrder(root.left, to_delete, results);
        root.right = postOrder(root.right, to_delete, results);
        boolean toDel = exist(to_delete, root.val);
        if(toDel) {
            deCount++;
            if(root.left != null) {
                results.add(root.left);
            }
            if(root.right != null) {
                results.add(root.right);
            }
            return null;
        }
        return root;
    }
    // 如何快速定位数据 map 或者 target 为数组下标
    public boolean exist(int[] to_delete, int target) {

        if(deCount >= to_delete.length) {
            return false;
        }
        for (int i = deCount; i < to_delete.length; i++) {
            if(to_delete[i] == target) {
                to_delete[i] = to_delete[deCount];
                return true;
            }
        }
        return false;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        boolean[] delMap = new boolean[1001];
        for (int v : to_delete) {
            delMap[v] = true;
        }
        List<TreeNode> results = new ArrayList<>();
        TreeNode treeNode = postOrder(root, delMap, results);
        if(treeNode != null) {
            results.add(treeNode);
        }
        return results;
    }

    public TreeNode postOrder(TreeNode root, boolean[] to_delete, List<TreeNode> results) {
        if(root == null) {
            return null;
        }
        root.left = postOrder(root.left, to_delete, results);
        root.right = postOrder(root.right, to_delete, results);
        if(to_delete[root.val]) {
            if(root.left != null) {
                results.add(root.left);
            }
            if(root.right != null) {
                results.add(root.right);
            }
            return null;
        }
        return root;
    }


}
