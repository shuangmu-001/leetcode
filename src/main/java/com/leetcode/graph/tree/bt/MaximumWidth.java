package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author zms
 * @date 11:01 AM 2020/3/22
 * <a href="https://leetcode.com/problems/maximum-width-of-binary-tree/">
 *     Maximum Width of Binary Tree</a>
 */
public class MaximumWidth {
    /**
     * Given a binary tree, write a function to get the maximum width of the given tree.
     * The width of a tree is the maximum width among all levels.
     * The binary tree has the same structure as a full binary tree, but some nodes are null.
     * The width of one level is defined as the length between the end-nodes
     * (the leftmost and right most non-null nodes in the level,
     * where the null nodes between the end-nodes are also counted into the length calculation.
     *
     * Example 1:
     * Input:
     *
     *            1
     *          /   \
     *         3     2
     *        / \     \
     *       5   3     9
     *
     * Output: 4
     * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
     *
     * Example 2:
     * Input:
     *
     *           1
     *          /
     *         3
     *        / \
     *       5   3
     *
     * Output: 2
     * Explanation: The maximum width existing in the third level with the length 2 (5,3).
     *
     * Example 3:
     * Input:
     *
     *           1
     *          / \
     *         3   2
     *        /
     *       5
     *
     * Output: 2
     * Explanation: The maximum width existing in the second level with the length 2 (3,2).
     *
     * Example 4:
     * Input:
     *
     *           1
     *          / \
     *         3   2
     *        /     \
     *       5       9
     *      /         \
     *     6           7
     * Output: 8
     * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
     *
     * Note: Answer will in the range of 32-bit signed integer.
     */
    /**
     * Runtime: 69 ms, faster than 5.34% of Java online submissions for Maximum Width of Binary Tree.
     * Memory Usage: 57.4 MB, less than 11.11% of Java online submissions for Maximum Width of Binary Tree.
     */
    public int widthOfBinaryTree1(TreeNode root) {
        int max = 0;
        if(root == null) {
            return max;
        }
        Queue<List<TreeNode>> nodes = new LinkedList<>();
        nodes.add(Collections.singletonList(root));
        while(!nodes.isEmpty()) {
            List<TreeNode> polls = nodes.poll();
            int nums = 0;
            List<TreeNode> nextLevel =  new ArrayList<>();
            for (TreeNode treeNode : polls) {
                if (treeNode != null) {
                    nums++;
                    if (treeNode.left == null && !nextLevel.isEmpty()) {
                        nextLevel.add(null);
                    } else if (treeNode.left != null) {
                        nextLevel.add(treeNode.left);
                    }
                    if (treeNode.right == null && !nextLevel.isEmpty()) {
                        nextLevel.add(null);
                    } else if (treeNode.right != null) {
                        nextLevel.add(treeNode.right);
                    }
                } else {
                    if(!nextLevel.isEmpty()) {
                        nextLevel.add(null);
                        nextLevel.add(null);
                    }

                    if (nums != 0) {
                        nums++;
                    }
                }

            }
            while(!nextLevel.isEmpty() && nextLevel.get(nextLevel.size() - 1) == null) {
                nextLevel.remove(nextLevel.size() - 1);
            }
            if(!nextLevel.isEmpty()) {
                nodes.add(nextLevel);
            }
            max = Math.max(nums, max);
        }
        return max;
    }

    /**
     * Runtime: 2 ms, faster than 42.48% of Java online submissions for Maximum Width of Binary Tree.
     * Memory Usage: 40.4 MB, less than 11.11% of Java online submissions for Maximum Width of Binary Tree.
     */
    private int max;
    public int widthOfBinaryTree(TreeNode root) {
        List<Width> list = new ArrayList<>();
        traversal(root, 0, 0, list);
        return max;
    }

    public void traversal(TreeNode root, int x, int y, List<Width> list) {
        if(root == null) {
            return;
        }
        if(list.size() <= y) {
            list.add(new Width(x, x));
        }
        list.get(y).setStart(x);
        list.get(y).setEnd(x);
        int width = list.get(y).end - list.get(y).start;
        width = width == 0 ? 1 : width;
        max = Math.max(max, width);
        traversal(root.left, x << 1, y + 1, list);
        traversal(root.right, (x << 1) + 1, y + 1, list);
    }

    class Width {
        int start;
        int end;

        public Width(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Width setStart(int start) {
            this.start = Math.min(start, this.start);
            return this;
        }

        public Width setEnd(int end) {
            this.end = Math.max(end, this.end);
            return this;
        }
    }
}
