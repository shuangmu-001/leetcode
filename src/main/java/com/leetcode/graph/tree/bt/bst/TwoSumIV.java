package com.leetcode.graph.tree.bt.bst;

import com.leetcode.graph.tree.bt.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.leetcode.graph.tree.bt.bst.ConvertSortedArrayToBST.sortedArrayToBST;

/**
 * @author wcl
 * @date 8:12 PM 2020/3/12
 * {@link "https://leetcode.com/problems/two-sum-iv-input-is-a-bst/"}
 * @see com.leetcode.sum.TwoSum
 */
public class TwoSumIV {
    /**
     * Given a Binary Search Tree and a target number,
     * return true if there exist two elements in the BST such that their sum is equal to the given target.
     *
     * Example 1:
     * Input:
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     * Target = 9
     * Output: True
     *
     * Example 2:
     *
     * Input:
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     * Target = 28
     * Output: False
     */
    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        traversal(root, nums);
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if(sum < k) {
                left++;
            } else if(sum > k) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void traversal(TreeNode root, List<Integer> nums) {
        if(root != null) {
            traversal(root.left, nums);
            nums.add(root.val);
            traversal(root.right, nums);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = sortedArrayToBST(new int[]{2, 3, 4, 5, 6, 7});
        System.out.println(findTarget(treeNode, 9));
        System.out.println(!findTarget(treeNode, 28));
    }
}
