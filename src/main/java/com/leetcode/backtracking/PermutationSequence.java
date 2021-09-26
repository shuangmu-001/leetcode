package com.leetcode.backtracking;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 5:37 PM 2020/5/11
 * <a href="https://leetcode.com/problems/permutation-sequence/">
 * Permutation Sequence</a>
 */
public class PermutationSequence {
    /**
     * The set [1,2,3,...,n] contains a total of n! unique permutations.
     *
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     *
     * Note:
     *
     * Given n will be between 1 and 9 inclusive.
     * Given k will be between 1 and n! inclusive.
     * Example 1:
     *
     * Input: n = 3, k = 3
     * Output: "213"
     * Example 2:
     *
     * Input: n = 4, k = 9
     * Output: "2314"
     */
    static List<List<Integer>> all;
    static int len;
    public static String getPermutation1(int n, int k) {
        all = new ArrayList<>();
        int[] nums = new int[n];
        len = k;
        for (int i = 0; i < n; i ++) {
            nums[i] = i + 1;
        }
        dfs(nums, 0, new boolean[n], new ArrayList<>());
        System.out.println(all);
        List<Integer> integers = all.get(k - 1);
        StringBuilder builder = new StringBuilder();
        for (int num : integers) {
            builder.append(num);
        }
        return builder.toString();
    }

    public static void dfs(int[] nums, int depth, boolean[] flag, List<Integer> list) {
        if(depth == nums.length || all.size() == len) {
            all.add(list);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!flag[i]) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(nums[i]);
                flag[i] = true;
                dfs(nums, depth + 1, flag, newList);
                flag[i] = false;
            }
        }
    }

    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        int index = 1;
        nums[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            nums[i] = nums[i + 1] * index++;
        }

        boolean[] flag = new boolean[n];
        StringBuilder sb = new StringBuilder();
        index = 0;
        while(k < nums[index]) {
            int a = 0;
            while(flag[a]) {
                a++;
            }
            sb.append(a + 1);
            flag[a] = true;
            index++;
        }
        for (int i = index; i < n - 1; i++) {
            int a = k / nums[i];
            if(k % nums[i] == 0) {
                a = a - 1;
            }
            k =  k < nums[i] ? k : k - nums[i] * a;
            index = 0;
            int count = -1;
            while(count != a) {
                if(!flag[index++]){
                    count++;
                }

            }
            sb.append(index);
            flag[index - 1] = true;
        }
        index = 0;
        while(flag[index]) {
            index++;
        }
        sb.append(index + 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation1(3,3).equals(getPermutation(3, 3)));
        System.out.println(getPermutation1(4,9).equals(getPermutation(4, 9)));
        System.out.println(getPermutation1(9,300).equals(getPermutation(9, 300)));
        System.out.println(getPermutation1(9,116907).equals(getPermutation(9, 116907)));
    }
}
