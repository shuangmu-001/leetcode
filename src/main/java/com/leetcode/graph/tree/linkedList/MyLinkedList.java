package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 4:58 PM 2020/4/20
 * <a href="https://leetcode.com/problems/design-linked-list/">
 * Design Linked List</a>
 */
public class MyLinkedList {

    private ListNode head;
    private ListNode end;
    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        int mid = size >> 1;
        ListNode node;
        if (mid > index) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = end;
            index = size - index - 1;
            for (int i = 0; i < index; i++) {
                node = node.prev;
            }
        }
        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode head = new ListNode(val);
        if (this.head == null) {
            this.head = head;
            this.end = head;
        } else {
            head.next = this.head;
            this.head.prev = head;
            this.head = head;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode end = new ListNode(val);
        if (this.end == null) {
            this.head = end;
            this.end = head;
        } else {
            this.end.next = end;
            end.prev = this.end;
            this.end = end;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index == size) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else if (index > 0 && index < size) {
            int mid = size >> 1;
            ListNode node;
            if (mid > index) {
                node = head;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
            } else {
                node = end;
                index = size - index - 1;
                for (int i = 0; i < index; i++) {
                    node = node.prev;
                }
            }
            ListNode addNode = new ListNode(val);
            ListNode prev = node.prev;
            prev.next = addNode;
            addNode.next = node;
            node.prev = addNode;
            addNode.prev = prev;
            size++;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (head == null && end == null) {
            return;
        }
        if (index >= size) {
            return;
        }
        int mid = size >> 1;
        ListNode node;
        if (mid > index) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = end;
            index = size - index - 1;
            for (int i = 0; i < index; i++) {
                node = node.prev;
            }
        }
        ListNode prev = node.prev;
        ListNode next = node.next;
        if (prev == null && next == null) {
            head = null;
            end = null;
        } else if (prev == null) {
            next.prev = null;
            head = next;
        } else if (next == null) {
            prev.next = null;
            end = prev;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        size--;
    }

    public static class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //["MyLinkedList","addAtHead","addAtTail","addAtHead","addAtTail",
        // "addAtHead","addAtHead","get","addAtHead","get","get","addAtTail"]
        //[[],[7],[7],[9],[8],[6],[0],[5],[0],[2],[5],[4]]
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(38);
        myLinkedList.addAtTail(66);
        myLinkedList.addAtTail(61);
        myLinkedList.addAtTail(76);
        myLinkedList.addAtTail(26);
        myLinkedList.addAtTail(37);
        myLinkedList.addAtTail(8);
        myLinkedList.deleteAtIndex(5);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtHead(45);
        System.out.println(myLinkedList.get(4));
        myLinkedList.addAtTail(85);
        myLinkedList.addAtHead(37);
        System.out.println(myLinkedList.get(5));
        myLinkedList.addAtTail(93);
        myLinkedList.addAtIndex(10, 23);
        myLinkedList.addAtTail(21);
        myLinkedList.addAtHead(52);
        myLinkedList.addAtHead(15);
        myLinkedList.addAtHead(47);
        System.out.println(myLinkedList.get(12));
        //["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex",
        // "deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
        //[[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
        MyLinkedList myLinkedList1 = new MyLinkedList();
        myLinkedList1.addAtHead(7);
        myLinkedList1.addAtHead(2);
        myLinkedList1.addAtHead(1);
        myLinkedList1.addAtIndex(3,0);
        myLinkedList1.deleteAtIndex(2);
        myLinkedList1.addAtHead(6);
        myLinkedList1.addAtTail(4);
        System.out.println(myLinkedList1.get(4));
        myLinkedList1.addAtHead(4);
        myLinkedList1.addAtIndex(5,0);
        myLinkedList1.addAtHead(6);
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
