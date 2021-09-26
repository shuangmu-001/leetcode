package com.leetcode.arrays;


import java.util.Arrays;

/**
 * {@link "https://leetcode.com/problems/3sum-closest/"}
 * @author zms
 * @date 11:11 AM 2020/2/14
 * @see ThreeSum
 */
public class ThreeSumClosest {
    /**
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * <p>
     * Example:
     * Given array nums = [-1, 2, 1, -4], and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * <p>
     * 暴力破解
     * Runtime: 118 ms, faster than 5.02% of Java online submissions for 3Sum Closest.
     * Memory Usage: 38.1 MB, less than 6.67% of Java online submissions for 3Sum Closest.
     */
    public static int threeSumClosest1(int[] nums, int target) {
        int len = nums.length;
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < diff) {
                        result = sum;
                        diff = Math.abs(sum - target);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 排序 + 二分查找
     * Runtime: 7 ms, faster than 30.18% of Java online submissions for 3Sum Closest.
     * Memory Usage: 38.5 MB, less than 6.67% of Java online submissions for 3Sum Closest.
     */
    public static int threeSumClosest2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int result = 0;
        int diff = Integer.MAX_VALUE;

        int min = nums[0] + nums[1] + nums[2];

        int max = nums[len - 1] + nums[len - 2] + nums[len - 3];
        if (target < min) {
            return min;
        }
        if (target > max) {
            return max;
        }

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                int twoSum = nums[i] + nums[j];
                int threeIndex = Arrays.binarySearch(nums,j + 1, len,target - twoSum);

                if(threeIndex < 0) {
                    threeIndex = ~threeIndex;
                } else {
                    return target;
                }

                if(threeIndex < len) {
                    int sum1 = twoSum + nums[threeIndex];
                    if (Math.abs(sum1 - target) < diff) {
                        result = sum1;
                        diff = Math.abs(sum1 - target);
                    }
                }

                if(threeIndex != j + 1) {
                    int sum = twoSum + nums[threeIndex - 1];
                    if (Math.abs(sum - target) < diff) {
                        result = sum;
                        diff = Math.abs(sum - target);
                    }
                }

            }
        }


        return result;
    }

    /**
     * 排序 + 双指针
     * Runtime: 3 ms, faster than 98.36% of Java online submissions for 3Sum Closest.
     * Memory Usage: 38.6 MB, less than 6.67% of Java online submissions for 3Sum Closest.
     */
    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int result = 0;
        int diff = Integer.MAX_VALUE;

        int min = nums[0] + nums[1] + nums[2];

        int max = nums[len - 1] + nums[len - 2] + nums[len - 3];
        if (target < min) {
            return min;
        }
        if (target > max) {
            return max;
        }

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target) {
                    return target;
                } else if(sum > target) {
                    if(sum - target < diff) {
                        diff = sum - target;
                        result = sum;
                    }
                    right--;
                } else {
                    if( target - sum < diff) {
                        diff = target - sum;
                        result = sum;
                    }
                    left++;
                }

            }

        }


        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 2, 1, -4};
        System.out.println(threeSumClosest(nums1, 1) == 2);

        int[] nums2 = {0, 2, 1, -3};
        System.out.println(threeSumClosest(nums2, 1) == 0);

        int[] nums3 = {-43, 61, -62, 24, 73, 64, -23, 94, -65, -27, 24, -56, 8, 54, 0, 19, -100, -64, -53, 6, -22, 13, -18, 55, -41, 37, 34, -43, 0, -8, -95, 76, 73, 21, -93, 16, 98, 60, 70, -32, 30, -7, -27, -73, 79, -26, 41, 32, 41, -5, 82, -14, 50, -94, 22, 62, 60, -48, 51, 12, -34, 68, -40, -20, -20, 46, 46, -79, 1, 82, -98, 41, -79, -43, -76, -25, -94, -16, -25, 46, -95, -79, 53, -1, -30, 43, 93, 17, 72, 66, 83, -89, -16, 42, 40, 87, -46, 40, 22, 85, -91, 46, -77, 19, -56, -28, 8, 47, -20, 65, 8, 60, -49, -86, -74, 56, 30, 79, 97, -89, 14, -90, 66, -12, -57, 46, -61, 87, 72, 13, 75, 75, 36, 79, -16, 84, -49, -86, 76, 68, -8, -65, -86, -11, 55, -69, -59, 34, 63, 59, -11, 43, 16, 7, 93, 57, -55, 2, 38, 64, 3, 22, -9, -22, -34, -84, 90, -71, -88, 64, -19, 13, -8, -81, -95, -38, -9, -25, 82, 57, 60, -26, 66, 21, 8, 65, -38, -68, -59, 24, 91};
        System.out.println(threeSumClosest(nums3, -231) == -231);

//        System.out.println(Arrays.binarySearch(new int[]{-4, -1, 1, 2}, 2, 3, 0));
    }
}
