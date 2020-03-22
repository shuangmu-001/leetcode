package com.leetcode.graph.tree.bt;

import java.util.*;

/**
 * @author wcl
 * @date 12:07 PM 2020/3/22
 * <a href="https://leetcode.com/problems/check-completeness-of-a-binary-tree/">
 *     Check Completeness of a Binary Tree</a>
 */
public class CheckCompleteness {
    /**
     * Given a binary tree, determine if it is a complete binary tree.
     * Definition of a complete binary tree from Wikipedia:
     *      In a complete binary tree every level, except possibly the last,
     *      is completely filled, and all nodes in the last level are as far left as possible.
     *      It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Example 1:
     *      Input: [1,2,3,4,5,6]
     *      Output: true
     *      Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
     *      and all nodes in the last level ({4, 5, 6}) are as far left as possible.
     *
     * Example 2:
     *      Input: [1,2,3,4,5,null,7]
     *      Output: false
     *      Explanation: The node with value 7 isn't as far left as possible.
     *
     * Note:The tree will have between 1 and 100 nodes.
     */
    List<Integer> list = new ArrayList<>();
    int level = 0;
    public boolean isCompleteTree(TreeNode root) {
        boolean result = isCompleteTreeHelper(root, 0, 1);
//        if(!result) {
//            return false;
//        }
//        for (int i = 0; i < list.size() - 1; i++) {
//            if(list.get(i) != (1 << (i + 1)) - 1) {
//                return false;
//            }
//        }
//        return true;
        return result && ((level == list.size() - 1) || level == list.size());
    }

    public boolean isCompleteTreeHelper(TreeNode root, int y, int x) {
        if(root == null) {
            return true;
        }
        if(list.size() <= y) {
            if((1 << y) != x) {
                return false;
            }
            list.add(x);
        } else if(x - list.get(y) != 1) {
            return false;
        }
        if((1 << (y + 1)) - 1 == x) {
            level = y + 1;
        }
        list.set(y, x);
        return isCompleteTreeHelper(root.left, y + 1, x << 1) && isCompleteTreeHelper(root.right, y + 1, (x << 1) + 1);
    }

}
