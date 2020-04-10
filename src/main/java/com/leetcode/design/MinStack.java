package com.leetcode.design;

import java.util.*;

/**
 * @author wcl
 * @date 6:40 PM 2020/4/10
 * <a href="https://leetcode.com/problems/min-stack/">
 *     Min Stack</a>
 */
public class MinStack {
    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     *
     *
     * Example:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     */

    /** initialize your data structure here. */
    List<Integer> list = new ArrayList<>();
    TreeMap<Integer, Integer> set = new TreeMap<>();
    int index = -1;
    public MinStack() {

    }

    public void push(int x) {
        if(index == list.size() - 1) {
            list.add(x);
        } else {
            list.set(index + 1, x);
        }
        index++;
        set.merge(x, 1, Integer::sum);
    }

    public void pop() {
        if(index >= 0) {
            int x = list.get(index--);
            if(set.get(x) > 1) {
                set.merge(x, -1, Integer::sum);
            } else {
                set.remove(x);
            }
        }
    }

    public int top() {
        return index < 0 ? 0 : list.get(index);
    }

    public int getMin() {
        return index < 0 ? 0 : set.firstKey();
    }
    class MinStack1 {
        private Node head;

        public void push(int x) {
            if(head == null) {
                head = new Node(x, x);
            } else {
                head = new Node(x, Math.min(x, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            Node next;
            int val;
            int min;

            private Node(int val, int min) {
                this(val, min, null);
            }

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }

        }
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */