package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.*;

/**
 * @author wcl
 * @date 11:28 PM 2020/3/15
 * <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree/">
 *     Find Mode in Binary Search Tree</a>
 */
public class FindMode {
    /**
     * Given a binary search tree (BST) with duplicates,
     * find all the mode(s) (the most frequently occurred element) in the given BST.
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * For example:
     * Given BST [1,null,2,2],
     *    1
     *     \
     *      2
     *     /
     *    2
     * return [2].
     *
     * Note: If a tree has more than one mode, you can return them in any order.
     * Follow up: Could you do that without using any extra space?
     * (Assume that the implicit stack space incurred due to recursion does not count).
     */
    public int[] findMode1(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int len = 0;
        TreeNode cur = root;
        TreeNode before;
        while (cur != null) {
            if(cur.left == null) {
                map.merge(cur.val, 1, Integer::sum);
                if(max == map.get(cur.val)) {
                    len++;
                } else if(max < map.get(cur.val)) {
                    max = map.get(cur.val);
                    len = 1;
                }
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
        int[] nums = new int[len];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max) {
                nums[index++] = entry.getKey();
            }
        }
        return nums;
    }

    Integer prev = null;
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root.val;
        traverse(root.right, list);
    }

}
