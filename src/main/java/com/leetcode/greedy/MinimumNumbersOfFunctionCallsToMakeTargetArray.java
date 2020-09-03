package com.leetcode.greedy;

/**
 * @author wcl
 * @date 3:41 下午 2020/8/28
 * <a href="https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/">
 * Minimum Numbers of Function Calls to Make Target Array</a>
 */
public class MinimumNumbersOfFunctionCallsToMakeTargetArray {
    /**
     * func modify(arr, op, idx) {
     * // add by 1 index idx
     * if (op == 0) {
     * arr[idx] = arr[idx] + 1;
     * }
     * // multiply by 2 all elements
     * if (op == 1) {
     * for (int i = 0; i < arr.length; i++) {
     * arr[i] = arr[i] * 2;
     * }
     * }
     * }
     * Your task is to form an integer array nums from an initial array of zeros arr that is the same size as nums.
     * <p>
     * Return the minimum number of function calls to make nums from arr.
     * <p>
     * The answer is guaranteed to fit in a 32-bit signed integer.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,5]
     * Output: 5
     * Explanation: Increment by 1 (second element): [0, 0] to get [0, 1] (1 operation).
     * Double all the elements: [0, 1] -> [0, 2] -> [0, 4] (2 operations).
     * Increment by 1 (both elements)  [0, 4] -> [1, 4] -> [1, 5] (2 operations).
     * Total of operations: 1 + 2 + 2 = 5.
     * Example 2:
     * <p>
     * Input: nums = [2,2]
     * Output: 3
     * Explanation: Increment by 1 (both elements) [0, 0] -> [0, 1] -> [1, 1] (2 operations).
     * Double all the elements: [1, 1] -> [2, 2] (1 operation).
     * Total of operations: 2 + 1 = 3.
     * Example 3:
     * <p>
     * Input: nums = [4,2,5]
     * Output: 6
     * Explanation: (initial)[0,0,0] -> [1,0,0] -> [1,0,1] -> [2,0,2] -> [2,1,2] -> [4,2,4] -> [4,2,5](nums).
     * Example 4:
     * <p>
     * Input: nums = [3,2,2,4]
     * Output: 7
     * Example 5:
     * <p>
     * Input: nums = [2,4,8,16]
     * Output: 8
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     */
    public static int minOperations1(int[] nums) {
        // odd even
        int odd = 0;
        int even = 0;
        for (int num : nums) {
            int currEven = 0;
            while (num > 0) {
                if ((num & 1) != 0) {
                    // odd必须单独 +1
                    odd++;
                    num--;
                } else {
                    currEven++;
                    num /= 2;
                }
            }
            even = Math.max(currEven, even);
        }
        return odd + even;
    }

    // 统计1的个数
    public static int minOperations(int[] nums) {
        int result = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            result += Integer.bitCount(num);
        }
        while (max > 1) {
            result++;
            max >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 4, 5};
        System.out.println(minOperations(nums) == minOperations1(nums));
        nums = new int[]{2, 4, 8, 16};
        System.out.println(minOperations(nums) == minOperations1(nums));
        nums = new int[]{0, 3, 4, 0};
        System.out.println(minOperations(nums) == minOperations1(nums));
        nums = new int[]{0, 0};
        System.out.println(minOperations(nums) == minOperations1(nums));
        nums = new int[]{0,0,0,0,2,3,4,5,62,3,45,234,423};
        System.out.println(minOperations(nums) == minOperations1(nums));
        nums = new int[]{45,234,423};
        System.out.println(minOperations(nums) == minOperations1(nums));
    }


}

