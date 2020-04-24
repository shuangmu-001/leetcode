package com.leetcode.graph.tree.linkedList;

import com.leetcode.Utils;

/**
 * @author wcl
 * @date 3:37 PM 2020/4/20
 * <a href="https://leetcode.com/problems/split-linked-list-in-parts/">
 * Split Linked List in Parts</a>
 */
public class SplitLinkedListInParts {
    /**
     * Given a (singly) linked list with head node root,
     * write a function to split the linked list into k consecutive linked list "parts".
     * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1.
     * This may lead to some parts being null.
     * The parts should be in order of occurrence in the input list,
     * and parts occurring earlier should always have a size greater than or equal parts occurring later.
     * Return a List of ListNode's representing the linked list parts that are formed.
     * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
     * <p>
     * Example 1:
     * Input:
     * root = [1, 2, 3], k = 5
     * Output: [[1],[2],[3],[],[]]
     * Explanation:
     * The input and each element of the output are ListNodes, not arrays.
     * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
     * The first element output[0] has output[0].val = 1, output[0].next = null.
     * The last element output[4] is null, but it's string representation as a ListNode is [].
     * Example 2:
     * Input:
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     * Explanation:
     * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
     * Note:
     * <p>
     * The length of root will be in the range [0, 1000].
     * Each value of a node in the input will be an integer in the range [0, 999].
     * k will be an integer in the range [1, 50].
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) {
            return res;
        }
        ListNode dummy = root;
        int size = 0;
        while (dummy != null) {
            size++;
            dummy = dummy.next;
        }

        int len = size / k;
        int count = size % k;
        int index = 0;
        int lenIndex = 0;
        int countIndex = 0;
        while (root != null) {
            lenIndex++;
            ListNode temp = root;
            root = root.next;
            if (lenIndex == 1) {
                res[index++] = temp;
            }
            if (lenIndex == len + 1 && count != countIndex) {
                temp.next = null;
                lenIndex = 0;
                countIndex++;
            }
            if (lenIndex == len && count == countIndex) {
                temp.next = null;
                lenIndex = 0;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4}), 5));

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 3));

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 7));

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 9));

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 10));

        print(splitListToParts(Utils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 1));

    }

    public static void print(ListNode[] listNodes) {
        System.out.println("--------数组的大小" + listNodes.length + "----------");
        for (ListNode node : listNodes) {
            Utils.printLinkedList(node);
        }
    }

}
