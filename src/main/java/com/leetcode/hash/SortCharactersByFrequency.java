package com.leetcode.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zms
 * @date 6:38 下午 2020/5/22
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">
 * Sort Characters By Frequency</a>
 */
public class SortCharactersByFrequency {
    /**
     * Given a string, sort it in decreasing order based on the frequency of characters.
     *
     * Example 1:
     *
     * Input:
     * "tree"
     *
     * Output:
     * "eert"
     *
     * Explanation:
     * 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     * Example 2:
     *
     * Input:
     * "cccaaa"
     *
     * Output:
     * "cccaaa"
     *
     * Explanation:
     * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     * Example 3:
     *
     * Input:
     * "Aabb"
     *
     * Output:
     * "bbAa"
     *
     * Explanation:
     * "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     */
    public static String frequencySort1(String s) {
        Map<Character, StringBuilder> map = new HashMap<>();
        List<Set<StringBuilder>> list = new ArrayList<>();
        list.add(new HashSet<>());
        char[] chars = s.toCharArray();
        for(char c : chars) {
            StringBuilder builder = map.getOrDefault(c, new StringBuilder());
            int length = builder.length();
            if(list.size() > length) {
                Set<StringBuilder> set = list.get(length);
                set.remove(builder);
            }
            length++;
            if(list.size() <= length) {
                Set<StringBuilder> set = new HashSet<>();
                set.add(builder);
                list.add(set);
            } else {
                Set<StringBuilder> set = list.get(length);
                set.add(builder);
            }
            builder.append(c);
            map.put(c, builder);
        }
        int size = list.size();
        StringBuilder res = new StringBuilder();
        for (int i = size - 1; i >=0; i--) {
            Set<StringBuilder> set = list.get(i);
            for (StringBuilder sb : set) {
                res.append(sb);
            }
        }
        return res.toString();
    }
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c : chars) {
            map.merge(c, 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = map.entrySet()
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getValue(), a1.getValue()))
                .collect(Collectors.toList());

        StringBuilder res = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                res.append(entry.getKey());
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("cccaaa"));
        System.out.println(frequencySort("Aabbfasdkjefjjfj"));
    }
}
