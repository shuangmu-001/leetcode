package com.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 6:24 PM 2020/5/12
 * <a href="https://leetcode.com/problems/build-an-array-with-stack-operations/">
 * Build an Array With Stack Operations</a>
 */
public class BuildAnArray {
    /**
     * Given an array target and an integer n. In each iteration, you will read a number from  list = {1,2,3..., n}.
     *
     * Build the target array using the following operations:
     *
     * Push: Read a new element from the beginning list, and push it in the array.
     * Pop: delete the last element of the array.
     * If the target array is already built, stop reading more elements.
     * You are guaranteed that the target array is strictly increasing, only containing numbers between 1 to n inclusive.
     *
     * Return the operations to build the target array.
     *
     * You are guaranteed that the answer is unique.
     *
     *
     *
     * Example 1:
     *
     * Input: target = [1,3], n = 3
     * Output: ["Push","Push","Pop","Push"]
     * Explanation:
     * Read number 1 and automatically push in the array -> [1]
     * Read number 2 and automatically push in the array then Pop it -> [1]
     * Read number 3 and automatically push in the array -> [1,3]
     * Example 2:
     *
     * Input: target = [1,2,3], n = 3
     * Output: ["Push","Push","Push"]
     * Example 3:
     *
     * Input: target = [1,2], n = 4
     * Output: ["Push","Push"]
     * Explanation: You only need to read the first 2 numbers and stop.
     * Example 4:
     *
     * Input: target = [2,3,4], n = 4
     * Output: ["Push","Pop","Push","Push","Push"]
     *
     *
     * Constraints:
     *
     * 1 <= target.length <= 100
     * 1 <= target[i] <= 100
     * 1 <= n <= 100
     * target is strictly increasing.
     */
    static final String PUSH = "Push";
    static final String POP = "Pop";
    public static List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        if(target.length > n) {
            return res;
        }
        int index = 1;
        for(int num : target) {
            while(num > index) {
                res.add(PUSH);
                res.add(POP);
                index++;
            }
            index++;
            res.add(PUSH);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(buildArray(new int[]{2,3,4},4));
        System.out.println(buildArray(new int[]{1,2},4));
        System.out.println(buildArray(new int[]{1,2},4));
    }
}
