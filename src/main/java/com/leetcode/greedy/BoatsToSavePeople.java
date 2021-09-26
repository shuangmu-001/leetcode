package com.leetcode.greedy;

import java.util.Arrays;

/**
 * @author zms
 * @date 10:12 PM 2020/2/26
 * TODO {@link "https://leetcode.com/problems/boats-to-save-people/"}
 * TODO 什么是贪心算法，什么情况下应该想到贪心算法
 */
public class BoatsToSavePeople {
    /**
     * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
     * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
     * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
     *
     * Example 1:
     *      Input: people = [1,2], limit = 3
     *      Output: 1
     *      Explanation: 1 boat (1, 2)
     *
     * Example 2:
     *      Input: people = [3,2,2,1], limit = 3
     *      Output: 3
     *      Explanation: 3 boats (1, 2), (2) and (3)
     *
     * Example 3:
     *      Input: people = [3,5,3,4], limit = 5
     *      Output: 4
     *      Explanation: 4 boats (3), (3), (4), (5)
     * Note:
     *      1 <= people.length <= 50000
     *      1 <= people[i] <= limit <= 30000
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int length = people.length;
        if(length == 1 || (people[0] + people[1] > limit)) {
            return length;
        }
        int result = 0;
        int sum = people[0];
        // 每艘船尽可能载更多的乘客
        // 反例  称重 : 3  成员 : [1, 1, 2, 2] 最优解释两艘而不是三艘
        for (int i = 1; i < length; i++) {
            sum += people[i];
            if(sum > limit) {
                result++;
                sum = people[i];
            }
            if(sum < limit && i == length -1) {
                result++;
            }
            if(sum == limit) {
                result++;
                sum = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{3,5,3,4}, 5) == 4);
        System.out.println(numRescueBoats(new int[]{1,2}, 3) == 1);
        System.out.println(numRescueBoats(new int[]{3,2,2,1}, 3) == 3);
        System.out.println(numRescueBoats(new int[]{1,2,2,1}, 3) == 2);

    }
}
