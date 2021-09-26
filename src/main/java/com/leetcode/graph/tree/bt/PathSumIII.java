package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author zms
 * @date 7:22 PM 2020/3/15
 * TODO <a href="https://leetcode.com/problems/path-sum-iii/">
 *     Path Sum III</a>
 * @see Balanced
 */
public class PathSumIII {
    /**
     * You are given a binary tree in which each node contains an integer value.
     * Find the number of paths that sum to a given value.
     * The path does not need to start or end at the root or a leaf,
     * but it must go downwards (traveling only from parent nodes to child nodes).
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     *
     * Example:
     *      root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *      Return 3. The paths that sum to 8 are:
     *      1.  5 -> 3
     *      2.  5 -> 2 -> 1
     *      3. -3 -> 11
     */
    private int result;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            pathSumHelper(poll, 0, sum);
            if(poll.left != null) {
                queue.add(poll.left);
            }
            if(poll.right != null) {
                queue.add(poll.right);
            }
        }
        return result;
    }

    public void pathSumHelper(TreeNode root, int before, int sum) {
        if(root == null ) {
            return;
        }
        before += root.val;
        if(before == sum) {
            result++;
        }
        if(root.left != null) {
            pathSumHelper(root.left, before, sum);
        }

        if(root.right != null) {
            pathSumHelper(root.right, before, sum);
        }
    }
    // other
    private int pathSum(TreeNode node, int target, int sum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }
        sum += node.val;
        int result = map.getOrDefault(sum - target, 0);

        map.merge(sum, 1, Integer::sum);
        result += pathSum(node.left, target, sum, map);
        result += pathSum(node.right, target, sum, map);
        map.merge(sum, -1, Integer::sum);
        if (map.get(sum) == 0) { // Remove when 0 to reduce space usage
            map.remove(sum);
        }

        return result;
    }
}
