package com.leetcode.graph.tree.linkedList;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 11:38 AM 2020/4/15
 * TODO <a href="https://leetcode.com/problems/sort-list/">
 *     Sort List</a>
 */
public class SortList {
    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     *
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     */
    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        sort(null, dummy, null);
        return null;
    }

    public static void sort(ListNode pre, ListNode first,ListNode second) {
        if(first == null || first == second || first.next == second) {
            return;
        }
        ListNode dummy = first;
        ListNode mid = first;
        ListNode prev = first;
        while(dummy != second && dummy.next != null ) {
            dummy = dummy.next.next;
            prev = mid;
            mid = mid.next;
        }

        sort(pre, first, mid);
        sort(prev, mid, second);
        System.out.println(first);
        merge(pre, first, mid, second);
    }
    public static void merge(ListNode pre, ListNode first, ListNode mid, ListNode second) {
        ListNode l = first;
        ListNode m = mid;
        while(l != mid && m != second) {
            if(l.val > m.val) {
                ListNode temp = m.next;
                m.next = l;
                pre.next = m;
                pre = pre.next;
                m = temp;
            } else {
                pre = l;
                l = l.next;
            }
        }
        mid.next = m;
    }

    public static void main(String[] args) {
        ListNode head = Utils.arrayToListNode(new int[]{Integer.MIN_VALUE, 4, 3, 2, 1});
        sort(head);
//        ListNode dummy = node;
//        ListNode mid = node;
//        while(dummy != null && dummy.next != null) {
//            dummy = dummy.next.next;
//            mid = mid.next;
//        }
//        System.out.println(dummy);
//        System.out.println(mid);
        ListNode parent = new ListNode(0);
        ListNode dummy = parent;
        parent.next = head;
        while(head != null && head.next != null) {
            if(head.val > head.next.val) {
                ListNode temp = head.next.next;
                ListNode next = head.next;
                next.next = head;
                head.next = temp;
                parent.next = next;
                parent = head;
                head = temp;
            } else {
                parent = head.next;
                head = head.next.next;
            }

        }
        head = dummy.next;
        parent = dummy;
        int index = 1;
        ListNode second = dummy.next;
        while(second != null && index <= 2) {
            second = second.next;
            index ++;
        }
        if(second == null) {
            return;
        }
        int firstIndex = 1;
        int secondIndex = 1;
        while(firstIndex <= 2 && secondIndex <= 2) {
            if(head.val > second.val) {
                parent.next = second;
                second = second.next;
                parent.next.next = head;
                parent = parent.next;
                secondIndex++;
            } else {
                firstIndex++;
                head = head.next;
                parent = parent.next;
            }
        }
        if(secondIndex > 2) {
            while(firstIndex < 2) {
                head = head.next;
                firstIndex++;
            }
            head.next = second;
        }
        System.out.println(dummy.next);

        ListNode head1 = Utils.arrayToListNode(new int[]{4, 3, 2, 1});


    }

    public static ListNode sort(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;
        while(fast != null && fast.next != null ) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
//        sort(dummy);
//        sort(slow);
//        System.out.println("开始合并");
//        System.out.println(dummy);
//        System.out.println(slow);
        return merge(sort(head), sort(slow));
    }

    public static ListNode merge(ListNode dummy, ListNode slow) {
        ListNode head = new ListNode(0);
        head.next = dummy;
        ListNode prev = head;
        while(dummy != null && slow != null) {
            if(dummy.val > slow.val) {
                prev.next = slow;
                prev = prev.next;
                prev.next = dummy;
                slow = slow.next;
            } else {
                prev = dummy;
                dummy = dummy.next;
            }
        }
        while(dummy != null) {
            prev = dummy;
            dummy = dummy.next;
        }
        prev.next = slow;
        return head.next;
    }
}
