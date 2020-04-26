package com.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcl
 * @date 3:08 PM 2020/4/24
 * TODO <a href="https://leetcode.com/problems/lru-cache/">
 *     LRU Cache</a>
 */
public class LRUCache {
    /**
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.
     *      get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     *      put(key, value) - Set or insert the value if the key is not already present.
     *                  When the cache reached its capacity,
     *                  it should invalidate the least recently used item before inserting a new item.
     *
     * The cache is initialized with a positive capacity.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     *
     * Example:
     *
     * LRUCache cache = new LRUCache( 2 capacity );
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // returns 1
     * cache.put(3,3);    // evicts key 2
     * cache.get(2);       // returns -1 (not found)
     * cache.put(4,4);    // evicts key 1
     * cache.get(1);       // returns -1 (not found)
     * cache.get(3);       // returns 3
     * cache.get(4);       // returns 4
     */
    int size = 0;
    int capacity;
    Node head = null;
    Node end = null;
    Map<Integer, Node> map = new HashMap<>();
    Node target = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(capacity == 0) {
            return -1;
        }
        if(capacity == 1 ) {
            if(target == null || target.key != key) {
                return -1;
            } else {
                return target.val;
            }
        }
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        if(node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            end.next = node;
            node.prev = end;
            node.next = null;
        } else if(node.next != null) {
            head = node.next;
            head.prev = null;
            end.next = node;
            node.prev = end;
            node.next = null;
        }
        end = node;
        return node.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) {
            return;
        }
        Node node = new Node(key, value);
        if(capacity == 1) {
            target = node;
            return;
        }
        if(map.containsKey(key)) {
            Node old = map.get(key);
            if(old.prev != null && old.next != null) {
                old.prev.next = old.next;
                old.next.prev = old.prev;
            } else if(old.next != null) {
                head = old.next;
                head.prev = null;
            } else if(old.prev != null){
                end = end.prev;
                end.next = null;
            }
            size--;
        }
        if(size == 0) {
            head = node;
            end = node;
        } else  {
            end.next = node;
            node.prev = end;
            end = node;
            if(size == capacity) {
                Node temp = head.next;
                head.next = null;
                temp.prev = null;
                map.remove(head.key);
                head = temp;
                size--;

            }
        }
        map.put(key, node);
        size++;
    }
    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(2,2);
//        System.out.println(cache.get(1));
        cache.put(2,3);
//        System.out.println(cache.get(2));
        cache.put(4,1);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

