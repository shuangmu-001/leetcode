package com.leetcode.design;


import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek()
 * operation -- it essentially peek() at the element that will
 * be returned by the next call to next().
 *
 * Example:
 *
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 *
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 * @author zms
 * @date 7:56 上午 2021/2/9
 * <a href="https://leetcode.com/problems/peeking-iterator/">
 *     Peeking Iterator</a>
 */
public class PeekingIterator implements Iterator<Integer> {

    private final Iterator<Integer> iterator;

    private Integer cur;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(cur != null) {
            return cur;
        }
        if(iterator.hasNext()) {
            cur = iterator.next();
            return cur;
        }
        return null;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(cur != null) {
            Integer temp = cur;
            cur = null;
            return temp;
        }
        if(iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return cur != null || iterator.hasNext();
    }
}
