package com.leetcode.design;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wcl
 * @date 3:46 PM 2020/4/28
 * <a href="https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/531/week-4/3313/">
 *     First Unique Number</a>
 */
public class FirstUnique {
    /**
     * You have a queue of integers, you need to retrieve the first unique integer in the queue.
     *
     * Implement the FirstUnique class:
     *
     * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
     * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
     * void add(int value) insert value to the queue.
     *
     *
     * Example 1:
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
     * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
     * Output:
     * [null,2,null,2,null,3,null,-1]
     *
     * Explanation:
     * FirstUnique firstUnique = new FirstUnique([2,3,5]);
     * firstUnique.showFirstUnique(); // return 2
     * firstUnique.add(5);            // the queue is now [2,3,5,5]
     * firstUnique.showFirstUnique(); // return 2
     * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
     * firstUnique.showFirstUnique(); // return 3
     * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
     * firstUnique.showFirstUnique(); // return -1
     *
     * Example 2:
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
     * [[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
     * Output:
     * [null,-1,null,null,null,null,null,17]
     *
     * Explanation:
     * FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
     * firstUnique.showFirstUnique(); // return -1
     * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
     * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
     * firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
     * firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
     * firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
     * firstUnique.showFirstUnique(); // return 17
     *
     * Example 3:
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","showFirstUnique"]
     * [[[809]],[],[809],[]]
     * Output:
     * [null,809,null,-1]
     *
     * Explanation:
     * FirstUnique firstUnique = new FirstUnique([809]);
     * firstUnique.showFirstUnique(); // return 809
     * firstUnique.add(809);          // the queue is now [809,809]
     * firstUnique.showFirstUnique(); // return -1
     *
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^8
     * 1 <= value <= 10^8
     * At most 50000 calls will be made to showFirstUnique and add.
     */
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Node> nodes = new HashMap<>();
    Node head;
    Node end;
    public FirstUnique(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        Node parent = null;
        for (int num : nums) {
            int count = map.get(num);
            if(count == 1) {
                Node node = new Node(num);
                if(head == null) {
                    head = node;
                    parent = head;
                } else if(parent != null) {
                    parent.next = node;
                    node.prev = parent;
                    parent = node;
                }
                nodes.put(num, node);
            }
        }
        end = parent;

    }

    public int showFirstUnique() {
        if(head != null) {
            return head.val;
        }
        return -1;
    }

    public void add(int value) {
        if(!map.containsKey(value)) {
            Node node = new Node(value);
            if(end == null) {
                head = node;
            } else {
                end.next = node;
                node.prev = end;
            }
            end = node;
            nodes.put(value, node);
        } else if(nodes.containsKey(value)) {
            Node node = nodes.get(value);
            if(node.prev == null && node.next == null) {
                head = null;
                end = null;
            } else if(node.prev == null) {
                node.next.prev = null;
                head = node.next;
            } else if(node.next == null) {
                end = node.prev;
                end.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            nodes.remove(value);
        }
        map.merge(value, 1, Integer::sum);
    }

    static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */