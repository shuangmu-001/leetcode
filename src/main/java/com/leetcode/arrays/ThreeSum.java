package com.leetcode.arrays;

import com.Utils;

import java.util.*;

/**
 * {@link "https://leetcode.com/problems/3sum/"}
 * @author wcl
 * @date 9:17 PM 2020/2/14
 * @see TwoSum
 */
public class ThreeSum {
    /**
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * <p>
     * Note:
     * The solution set must not contain duplicate triplets.
     * <p>
     * Example:
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * A solution set is:
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3) {
            return res;
        }
        // Sort input array 1st
        Arrays.sort(nums);
        // Allocate enough space to avoid check in if statement
        int max = Math.max(nums[len - 1], Math.abs(nums[0]));
        byte[] hash = new byte[(max << 1) + 1];
        // Hash and count appearing times of every num
        for (int v : nums) {
            hash[v + max]++;
        }
        Utils.printArrays(hash);
        // Search the position of 0.
        // It also represents the position of the last negative number in the array
        int lastNeg = Arrays.binarySearch(nums, 0);
        // The pos. of the 1st pos. number in the array
        int firstPos = lastNeg;
        // 0 not found
        if (lastNeg < 0) {
            // 正数开始位置
            firstPos = ~lastNeg;
            // 为什么不用firstPos - 1呢？相同的
//            lastNeg = -lastNeg - 2;
            lastNeg = firstPos - 1;
            // see Java API
        } else {
            // found
            // skip all 0
            while (lastNeg >= 0 && nums[lastNeg] == 0) {
                --lastNeg;
            }
            while (firstPos < len && nums[firstPos] == 0) {
                ++firstPos;
            }
            int zeroCount = firstPos - lastNeg - 1;
            // 0 appears 3 times at least
            if (zeroCount >= 3) {
                res.add(Arrays.asList(0, 0, 0));
            }
            // 0 appears at least 1 time
            // 肯定有一个0
//            if (zeroCount > 0) {
                // traverse all the pos. numbers to see whether or not there's a neg. number whose abs. val.
                // equals the pos. number
                for (int i = firstPos; i < len; ++i) {
                    // skip duplicate (same) elements
                    if (i > firstPos && nums[i] == nums[i - 1]) {
                        continue;
                    }
                    if (hash[-nums[i] + max] > 0) {
                        res.add(Arrays.asList(0, nums[i], -nums[i]));
                    }
                }
//            }
        }
        // one positive number and two negetive numbers
        // traverse all the pos. numbers to find whether there are 2 neg. numbers to make the 3 numbers
        // add up to 0
        for (int i = firstPos; i < len; ++i) {
            // skip dups. (same elements)
            if (i > firstPos && nums[i] == nums[i - 1]) {
                continue;
            }
            // we can only traverse half of the pos. numbers
            int half;
            if (nums[i] % 2 != 0) {
                half = -((nums[i] >> 1) + 1);
            }
            else {
                half = -(nums[i] >> 1);
                if (hash[half + max] > 1) {
                    res.add(Arrays.asList(nums[i], half, half));
                }
            }
            for (int j = lastNeg; j >= 0 && nums[j] > half; --j) {
                if (j < lastNeg && nums[j] == nums[j + 1]) {
                    continue;
                }
                if (hash[(-nums[i] - nums[j]) + max] > 0) {
                    res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
                }
            }
        }
        // one negative number and two positive numbers
        // traverse all the negative numbers to find whether there are two positive numbers to make the
        // 3 numbers add up to 0
        for (int i = lastNeg; i >= 0; --i) {
            // skip dups. (same elements)
            if (i < lastNeg && nums[i] == nums[i + 1]) {
                continue;
            }
            // we can only traverse half of the negative numbers
            int half;
            if (nums[i] % 2 != 0) {
                half = -(nums[i] / 2 - 1);
            } else {
                half = -(nums[i] >> 1);
                if (hash[half + max] > 1) {
                    res.add(Arrays.asList(nums[i], half, half));
                }
            }
            for (int j = firstPos; j < len && nums[j] < half; ++j) {
                if (j > firstPos && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (hash[(-nums[i] - nums[j]) + max] > 0) {
                    res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
                }

            }
        }
        return res;

    }

    /**
     * Runtime: 243 ms, faster than 21.09% of Java online submissions for 3Sum.
     * Memory Usage: 47.7 MB, less than 86.22% of Java online submissions for 3Sum.
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3) {
            return res;
        }
        //
        Map<Integer, Integer> negativeNums = new TreeMap<>();
        Map<Integer, Integer> positiveNums = new TreeMap<>();
        int zeroCount = 0;
        for (int v : nums) {
            if(v > 0) {
                positiveNums.merge(v, 1, Integer::sum);
            } else if(v < 0) {
                negativeNums.merge(v, 1, Integer::sum);
            } else {
                zeroCount++;
            }
        }

        if(zeroCount >= 3) {
            res.add(Arrays.asList(0, 0, 0));
        }
        Set<Integer> positives = positiveNums.keySet();
        Set<Integer> negatives = negativeNums.keySet();
        if(zeroCount > 0) {
            for(int v : positives) {
                if(negativeNums.containsKey(-v)) {
                    res.add(Arrays.asList(0, v, -v));
                }
            }
        }
        Map<String, Integer> mh = new HashMap<>();
        for (int positive : positives) {

            Map<Integer, Integer> m = new HashMap<>();
            for (int negative : negatives) {
                if(m.containsKey(negative)) {
                    continue;
                }
                int sum = positive + negative;
                if(sum > 0) {
                    Integer count = negativeNums.get(-sum);
                    if(-sum == negative) {
                        if(count != null && count >= 2) {
                            res.add(Arrays.asList(positive, negative, negative));
                        }
                    } else if(count != null && count >= 1) {
                        res.add(Arrays.asList(positive, negative, -sum));
                        m.put(-sum,1);
                    }
                } else if(sum < 0) {
                    Integer count = positiveNums.get(-sum);
                    if(-sum == positive) {
                        if(count != null && count >= 2) {
                            res.add(Arrays.asList(positive, positive, negative));
                        }
                    } else if(count != null && count >= 1) {
                        if(!mh.containsKey(positive + "" + negative)) {
                            res.add(Arrays.asList(positive, negative, -sum));
                            mh.put(-sum + "" + negative, 1);
                        }

                    }
                }
            }
        }

        return res;

    }

    /**
     * Runtime: 380 ms, faster than 15.08% of Java online submissions for 3Sum.
     * Memory Usage: 45.9 MB, less than 94.70% of Java online submissions for 3Sum.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> sets = new HashSet<>();
        for (int i = 0; i < len - 2; i++) {

            int left = i + 1;
            int right = len - 1;
            if(nums[i] + nums[right] * 2 < 0) {
                continue;
            }

            if(nums[i] + nums[left] * 2 > 0) {
                continue;
            }

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    sets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
                if(sum < 0) {
                    left++;
                }
                if(sum > 0) {
                    right--;
                }
            }

        }

        return new ArrayList<>(sets);
    }



    public static void main(String[] args) {
//        int[] nums1 = {-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums1));
//        // [[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4],[-2,1,1],[0,0,0]]
//        int[] nums2 = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
//        System.out.println(threeSum(nums2));
//        System.out.println(~Arrays.binarySearch(new int[]{-1,1,3}, 0));
        List<Interval> a = new ArrayList<>();
        a.add(new Interval(1,5));
        a.add(new Interval(3,8));
        a.add(new Interval(0,4));
        a.add(new Interval(5,12));
        a.add(new Interval(5,10));
        a.add(new Interval(6,10));
        a.add(new Interval(7,10));
        System.out.println(getAns(a));
    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    public static int getAns(List<Interval> a) {
        // write your code here
        if( a == null || a.size() == 0) {
            return 0;
        }
        List<Interval> res = new ArrayList<>();
        a.sort(Comparator.comparingInt(a2 -> a2.start));
        System.out.println(a);
        res.add(a.get(0));
        for(Interval source : a) {
            boolean flag = true;
            for(Interval target : res) {
                if(source.start == target.end || (source.start > target.start && source.start <= target.end)) {
                    target.start = source.start;
                    flag = false;
                    break;
                }
                if(source.start >= target.start && source.end <= target.end) {
                    target.start = source.start;
                    target.end = source.end;
                    flag = false;
                    break;
                }
                if(source.start <= target.start && source.end >= target.end) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                res.add(source);
            }
            System.out.println(res);
        }

        return res.size();
    }


}