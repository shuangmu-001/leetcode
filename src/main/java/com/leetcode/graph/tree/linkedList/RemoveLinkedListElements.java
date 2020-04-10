package com.leetcode.graph.tree.linkedList;

/**
 * @author wcl
 * @date 11:34 AM 2020/4/9
 * <a href="https://leetcode.com/problems/remove-linked-list-elements/">
 *     Remove Linked List Elements</a>
 */
public class RemoveLinkedListElements {
    /**
     * Remove all elements from a linked list of integers that have value val.
     *
     * Example:
     * Input:  1->2->6->3->4->5->6, val = 6
     * Output: 1->2->3->4->5
     */
    public ListNode removeElements(ListNode head, int val) {

        while( head != null && head.val == val ){
            head = head.next;
        }

        if(head == null){
            return null;
        }
        ListNode itr = head.next,prev = head;

        while( itr != null ){
            if(itr.val == val){
                prev.next = itr.next;
            }else{
                prev = prev.next;
            }
            itr = itr.next;
        }


        return head;
    }
}
