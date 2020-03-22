package com.leetcode.graph.tree;

import com.leetcode.graph.tree.PopulatingNextRightPointersInEachNode.Node;
/**
 * @author wcl
 * @date 4:47 PM 2020/3/22
 * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/">
 *     Populating Next Right Pointers in Each Node II</a>
 * @see PopulatingNextRightPointersInEachNode
 */
public class PopulatingNextRightPointersInEachNodeII {
    /**
     * Given a binary tree
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
     *      Input: root = [1,2,3,4,5,null,7]
     *      Output: [1,#,2,3,#,4,5,7,#]
     *      Explanation: Given the above binary tree (Figure A),
     *      your function should populate each next pointer to point to its next right node,
     *      just like in Figure B. The serialized output is in level order as connected by the next pointers,
     *      with '#' signifying the end of each level.
     *
     * Constraints:
     *      The number of nodes in the given tree is less than 6000.
     *      -100 <= node.val <= 100
     */
    public Node connect1(Node root) {
        if(root == null) {
            return null;
        }
        Node dummy = root;
        Node before = null;
        Node nextNode = null;
        while(dummy != null) {

            Node head = dummy;
            boolean flag = true;
            do {
                if (head.left != null) {
                    nextNode = head.left;
                } else if (head.right != null) {
                    nextNode = head.right;
                    head = head.next;
                    flag = false;
                } else {
                    head = head.next;
                }
            } while (nextNode == null && head != null);
            if (nextNode != null) {
                before = nextNode;
                while (head != null) {
                    if(!flag) {
                        if (head.left != null) {
                            before.next = head.left;
                            before = before.next;
                        }
                    }
                    if (head.right != null) {
                        before.next = head.right;
                        before = before.next;
                    }
                    flag = false;
                    head = head.next;
                }
            }
            dummy = nextNode;
            nextNode = null;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        Node right1 = new Node(3);
        root.right = right1;
        right1.left = new Node(6);
        Node right2 = new Node(7);
        right2.left = new Node(9);
        right2.left.left = new Node(11);
        right2.right = new Node(10);
        right1.right = right2;
        right2.left = new Node(8);
        System.out.println(new PopulatingNextRightPointersInEachNodeII().connect(root));

        Node root2 = new Node(1);
        root2.right = new Node(2);
        System.out.println(new PopulatingNextRightPointersInEachNodeII().connect(root2));
    }


    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        Node dummy = root;
        Node before = null;
        Node nextNode = null;
        while(dummy != null) {

            while(dummy != null) {
                if(dummy.left != null) {
                    if(before != null) {
                        before.next = dummy.left;
                    } else {
                        nextNode = dummy.left;
                    }
                    before = dummy.left;
                }
                if(dummy.right != null) {
                    if(before != null) {
                        before.next = dummy.right;
                    } else {
                        nextNode = dummy.right;
                    }
                    before = nextNode;
                }
                dummy = dummy.next;
            }
            dummy = nextNode;
            nextNode = null;
            before = null;
        }
        return root;
    }
}
