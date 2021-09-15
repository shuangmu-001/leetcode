package com.leetcode.dp.tree;

import com.leetcode.dp.fibonacci.HouseRobber;
import com.leetcode.graph.tree.bt.TreeNode;

/**
 * @author wcl
 * @date 10:29 PM 2020/3/22
 * <a href="https://leetcode.com/problems/house-robber-iii/">
 *     House Robber III</a>
 * @see HouseRobber
 */
public class HouseRobberIII {
    /**
     * The thief has found himself a new place for his thievery again.
     * There is only one entrance to this area, called the "root."
     * Besides the root, each house has one and only one parent house.
     * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
     * It will automatically contact the police if two directly-linked houses were broken into on the same night.
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     *
     * Example 1:
     * Input: [3,2,3,null,3,null,1]
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     * Output: 7
     * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     *
     * Example 2:
     * Input: [3,4,5,1,3,null,1]
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     * Output: 9
     * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
     */
    public int rob(TreeNode root) {
        return robHelper(root).second;
    }

    // first + first + root.val, second + second
    public RobInfo robHelper(TreeNode root) {
        if(root == null) {
            return new RobInfo(0, 0);
        }
        if(root.left == null && root.right == null) {
            return new RobInfo(0, root.val);
        }
        RobInfo robInfoLeft = robHelper(root.left);
        RobInfo robInfoRight = robHelper(root.right);
        int first = robInfoLeft.first + robInfoRight.first + root.val;
        int second = robInfoLeft.second + robInfoRight.second;
        int temp = second;
        second = Math.max(first, second);
        first = temp;
        return new RobInfo(first, second);
    }

    static class RobInfo {
        int first;
        int second;

        public RobInfo(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
