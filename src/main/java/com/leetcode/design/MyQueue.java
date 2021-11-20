package com.leetcode.design;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/implement-queue-using-stacks/">Implement Queue using Stacks</a>
 *
 * @author zms
 * @date 9:31 下午 2021/11/20
 */
public class MyQueue {

    int size = 0;

    Stack<Integer> push = new Stack<>();

    Stack<Integer> pop = new Stack<>();

    public MyQueue() {
    }

    public void push(int x) {
        push.push(x);
        size++;
    }

    public int pop() {
        if(pop.isEmpty()) {
            transfer();
        }
        size--;
        return pop.pop();
    }

    public int peek() {
        if(pop.isEmpty()) {
            transfer();
        }
        return pop.peek();
    }

    public void transfer() {
        while (!push.isEmpty()) {
            pop.push(push.pop());
        }
    }

    public boolean empty() {
        return size == 0;
    }
}
