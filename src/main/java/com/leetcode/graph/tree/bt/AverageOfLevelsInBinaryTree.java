package com.leetcode.graph.tree.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 3:46 PM 2020/3/10
 */
public class AverageOfLevelsInBinaryTree {

    /**
     * Given a non-empty binary tree,
     * return the average value of the nodes on each level in the form of an array.
     * Example 1:
     * Input:
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Output: [3, 14.5, 11]
     * Explanation:
     * The average value of nodes on level 0 is 3,
     * on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
     *
     * Note: The range of node's value is in the range of 32-bit signed integer.
     */
    private static List<Integer> nums;
    public static List<Double> averageOfLevels(TreeNode root) {
        nums = new ArrayList<>();
        List<Double> result = new ArrayList<>();
        traversal(root, 1, result);
        for (int i = 1; i < result.size(); i++) {
            result.set(i, result.get(i) / nums.get(i));
        }
        return result;
    }
    public static void traversal(TreeNode root, int level, List<Double> averages) {
        if(root != null) {
            if(averages.size() < level) {
                averages.add(((double)root.val));
                nums.add(1);
            } else {
                double average = averages.get(level - 1);
                averages.set(level - 1, average + root.val);
                int num = nums.get(level - 1);
                nums.set(level - 1, num + 1);
            }
            int targetLevel = level + 1;
            traversal(root.left, targetLevel, averages);
            traversal(root.right, targetLevel, averages);
        }
    }

    public static void main(String[] args) {
        TreeNode root01 = new TreeNode(1);
        TreeNode left11 = new TreeNode(0);
        TreeNode right11 = new TreeNode(2);
        root01.left = left11;
        root01.right = right11;
        System.out.println(averageOfLevels(root01));

        TreeNode root02 = new TreeNode(3);
        TreeNode left21 = new TreeNode(0);
        TreeNode right21 = new TreeNode(4);
        root02.left = left21;
        root02.right = right21;
        TreeNode left23 = new TreeNode(1);
        TreeNode right22 = new TreeNode(2);
        left21.right = right22;
        right22.left = left23;
        System.out.println(averageOfLevels(root02));
    }

}
