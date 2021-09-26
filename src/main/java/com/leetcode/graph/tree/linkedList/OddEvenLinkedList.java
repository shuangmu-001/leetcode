package com.leetcode.graph.tree.linkedList;

/**
 * @author zms
 * @date 4:52 PM 2020/4/9
 * <a href="https://leetcode.com/problems/odd-even-linked-list/">
 *     Odd Even Linked List</a>
 */
public class OddEvenLinkedList {
    /**
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
     *
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     *
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     * Example 2:
     *
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     * Note:
     *
     * The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ...
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode oddNode = new ListNode(0);
        ListNode odd = oddNode;
        ListNode evenNode = new ListNode(0);
        ListNode even = evenNode;
        int len = 1;
        while(head != null) {
            if((len & 1) == 0) {
                even.next = head;
                even = even.next;
            } else {
                odd.next = head;
                odd = odd.next;
            }
            head = head.next;
            len++;
        }
        even.next = null;
        odd.next = evenNode.next;
        return oddNode.next;
    }
}
