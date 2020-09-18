package com.leetcode.heap;

import java.util.*;

/**
 * @author wcl
 * @date 5:26 下午 2020/9/18
 * <a href="https://leetcode.com/problems/top-k-frequent-words/">Top K Frequent Words</a>
 */
public class TopKFrequentWords {
    /**
     * Given a non-empty list of words, return the k most frequent elements.
     *
     * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
     *
     * Example 1:
     * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * Output: ["i", "love"]
     * Explanation: "i" and "love" are the two most frequent words.
     *     Note that "i" comes before "love" due to a lower alphabetical order.
     * Example 2:
     * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * Output: ["the", "is", "sunny", "day"]
     * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
     *     with the number of occurrence being 4, 3, 2 and 1 respectively.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Input words contain only lowercase letters.
     * Follow up:
     * Try to solve it in O(n log k) time and O(n) extra space.
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // step 1 计算每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            Integer count = map.getOrDefault(word, 0);
            count++;
            map.put(word, count);
        }
        // step 2 构建最小堆 保留最后的数据
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k + 1, (entry1, entry2) -> {
            if(entry1.getValue().equals(entry2.getValue()) ){
                return entry2.getKey().compareTo(entry1.getKey());
            }
            return entry1.getValue().compareTo(entry2.getValue());
        });

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // step 3 添加结果
        List<String> res = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}
