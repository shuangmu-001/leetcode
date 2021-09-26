package com.data.structure.linkedList;

/**
 * 给定一个由Node节点类型组成的无环单链表的头节点head，
 * 请实现一个函数完成这个链表得到复制，并返回复制的新链表的头节点。
 * 要求时间复杂度O(N),额外空间复杂度O(1)
 *
 * @author zms
 * @date 1:43 下午 2021/5/8
 */
public class CopyListWithRandom {

    static class Node {

        int val;
        Node next;
        // rand 指针是但链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，可能指向null；
        Node rand;

        public Node(int x) {
            this.val = x;
        }
    }

    public static Node CopyListWithRandom(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        Node temp;
        while (node != null) {
            temp = node.next;
            Node newNode = new Node(node.val);
            node.next = newNode;
            newNode.next = temp;
            node = temp;
        }
        // 给新链表的 rand 赋值
        node = head;
        while(node != null && node.next != null) {
            if(node.rand != null) {
                node.next.rand = node.rand.next;
            }
            node = node.next.next;
        }
        // 恢复链表
        node = head;
        Node res = new Node(0);
        Node cur = res;
        while(node != null && node.next != null) {
            temp = node.next;
            node.next = node.next.next;
            node = node.next;
            cur.next = temp;
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
    }



}
