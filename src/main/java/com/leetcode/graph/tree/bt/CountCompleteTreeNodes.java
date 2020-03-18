package com.leetcode.graph.tree.bt;

import java.util.Stack;

/**
 * @author wcl
 * @date 5:57 PM 2020/3/18
 * <a href="https://leetcode.com/problems/count-complete-tree-nodes/">
 *     Count Complete Tree Nodes</a>
 */
public class CountCompleteTreeNodes {
    /**
     * Given a complete binary tree, count the number of nodes.
     * Note:
     *      Definition of a complete binary tree from Wikipedia:
     *      In a complete binary tree every level,
     *      except possibly the last, is completely filled,
     *      and all nodes in the last level are as far left as possible.
     *      It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Example:
     *
     * Input:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     *
     * Output: 6
     */
    public int countNodes1(TreeNode root) {
        int count= 0;
        if(root == null) {
            return count;
        }
        TreeNode curr = root;
        TreeNode pre;
        while(curr != null) {
            if(curr.left == null) {
                count++;
                curr = curr.right;
            } else {
                pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
            }
        }
        return count;
    }
    public int countNodes(TreeNode root) {
        int count= 0;
        if(root == null) {
            return count;
        }
        int maxDepth = 0;
        TreeNode curr = root;
        while(curr != null) {
            curr = curr.left;
            count += (1 << maxDepth);
            maxDepth++;
        }

        return count + countLastLevel(root, maxDepth);
    }

    /**
     * @see com.leetcode.graph.tree.bt.bst.ConvertSortedArrayToBST
     */
    private int countLastLevel(TreeNode root, int depth) {
        if(depth == 1) {
            if(root.right == null) {
                return 1;
            } else {
                return 2;
            }
        }
        int level = 1;
        TreeNode midNode = root.left;
        while(level < depth) {
            level++;
            midNode = midNode.right;
        }
        if(midNode == null) {
            return countLastLevel(root.left, depth - 1);
        }
        return (1<< (depth - 1)) + countLastLevel(root.right, depth - 1);
    }
}
