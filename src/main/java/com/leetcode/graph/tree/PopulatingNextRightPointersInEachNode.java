package com.leetcode.graph.tree;

/**
 * @author wcl
 * @date 3:00 PM 2020/3/22
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node/">
 *     Populating Next Right Pointers in Each Node</a>
 */
public class PopulatingNextRightPointersInEachNode {
    /**
     * You are given a perfect binary tree where all leaves are on the same level,
     * and every parent has two children. The binary tree has the following definition:
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * Populate each next pointer to point to its next right node.
     * If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     *
     * Follow up:
     *      You may only use constant extra space.
     *      Recursive approach is fine,
     *          you may assume implicit stack space does not count as extra space for this problem.
     *
     * Example 1:
     *      Input: root = [1,2,3,4,5,6,7]
     *      Output: [1,#,2,3,#,4,5,6,7,#]
     *      Explanation: Given the above perfect binary tree (Figure A),
     *      your function should populate each next pointer to point to its next right node,
     *      just like in Figure B. The serialized output is in level order as connected by the next pointers,
     *      with '#' signifying the end of each level.
     *
     * Constraints:
     *      The number of nodes in the given tree is less than 4096.
     *      -1000 <= node.val <= 1000
     */
    private Node beforeNode;
    private int beforeX;
    private int beforeY;
    public Node connect1(Node root) {
        if(root == null) {
            return null;
        }
        connectHelper(root, 1, 0, true);
        return root;
    }
    public void connectHelper(Node root, int x, int y, boolean flag) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            root.left.next = root.right;
        }

        if(beforeY == y && beforeX + 1 == x) {
            if(beforeNode != null) {
                beforeNode.next = root;
                if(beforeNode.right != null) {
                    beforeNode = beforeNode.right;
                    beforeX = (x << 1) - 1;
                    beforeY = y + 1;
                }
            }
        }
        System.out.println("pre : " + root.val);
        connectHelper(root.left,x << 1, y + 1, true);
        connectHelper(root.right,(x << 1) + 1, y + 1, false);
        System.out.println("post : " + root.val);
        if(!flag ) {
            beforeNode = root;
            beforeX = x;
            beforeY = y;
        }
    }
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }

    public Node connect(Node root) {
        if(root == null) {
            return null;
        }

        Node leftMost = root;
        while(leftMost.left != null) {
            Node head = leftMost;

            while (head != null) {
                head.left.next = head.right;

                if(head.next != null) {
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            leftMost = leftMost.left;
        }

        return root;
    }

}
