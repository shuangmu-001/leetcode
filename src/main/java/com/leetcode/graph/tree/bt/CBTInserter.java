package com.leetcode.graph.tree.bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wcl
 * @date 5:45 PM 2020/3/19
 * <a href="https://leetcode.com/problems/complete-binary-tree-inserter/">
 *     Complete Binary Tree Inserter</a>
 */
public class CBTInserter {
    /**
     * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
     *
     * Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
     *
     * CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
     * CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
     * CBTInserter.get_root() will return the head node of the tree.
     *
     *
     * Example 1:
     *
     * Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
     * Output: [null,1,[1,2]]
     * Example 2:
     *
     * Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
     * Output: [null,3,4,[1,2,3,4,5,6,7,8]]
     *
     *
     * Note:
     *
     * The initial given tree is complete and contains between 1 and 1000 nodes.
     * CBTInserter.insert is called at most 10000 times per test case.
     * Every value of a given or inserted node is between 0 and 5000.
     */
    private TreeNode root;
    private boolean lFlag = true;
    private Queue<TreeNode> queue = new LinkedList<>();
    private TreeNode before;
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(poll.left != null) {
                queue.add(poll.left);
            } else if(before == null) {
                before = poll;
            }
            if(poll.right != null) {
                queue.add(poll.right);
            } else if(before == null) {
                before = poll;
                lFlag = false;
            }
        }
    }

    public int insert(int v) {
        TreeNode treeNode = new TreeNode(v);
        if(lFlag) {
            before.left = treeNode;
        } else {
            before.right = treeNode;
            before = queue.poll();
            lFlag = true;
        }
        queue.add(treeNode);
        return before.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
