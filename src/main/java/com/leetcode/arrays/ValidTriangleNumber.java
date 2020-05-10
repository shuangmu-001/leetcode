package com.leetcode.arrays;


import java.util.Arrays;

/**
 * @author wcl
 * @date 8:54 PM 2020/5/8
 * <a href="https://leetcode.com/problems/valid-triangle-number/">
 * Valid Triangle Number</a>
 * @see ThreeSum
 */
public class ValidTriangleNumber {

    /**
     * Given an array consists of non-negative integers,
     * your task is to count the number of triplets chosen from the array
     * that can make triangles if we take them as side lengths of a triangle.
     * Example 1:
     * Input: [2,2,3,4]
     * Output: 3
     * Explanation:
     * Valid combinations are:
     * 2,3,4 (using the first 2)
     * 2,3,4 (using the second 2)
     * 2,2,3
     * Note:
     * The length of the given array won't exceed 1000.
     * The integers in the given array are in the range of [0, 1000].
     */

    // 5.0
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int num = 0;
        int length = nums.length;
        int index = 0;
        while(index < length && nums[index] == 0) {
            index++;
        }
        for (int i = length - 1; i > 1; i--) {
            int left = index;
            int right = i - 1;
            while(left < right) {
                if(nums[left] + nums[right] > nums[i]) {
                    num += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return num;
    }
    // 4.0
    public static int triangleNumber4(int[] nums) {
        Arrays.sort(nums);
        int num = 0;
        int length = nums.length;
        int index = 0;
        while(index < length && nums[index] == 0) {
            index++;
        }
        for (int i = index; i < length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < length - 1; j++) {
                while(k < length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                num += (k - j - 1);
            }
        }
        return num;
    }
    // 3.0 o(n^2 * logn) 迭代
    public static int triangleNumber3(int[] nums) {
        Arrays.sort(nums);
        int num = 0;
        int length = nums.length;
       int index = 0;
        while(index < length && nums[index] == 0) {
            index++;
        }
        for (int i = index; i < length; i++) {
            int before = 0;
            int beforeTarget = i + 1;
            for (int j = i + 1; j < length; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) {
                    before--;
                } else if(beforeTarget != length) {
                    int sum = nums[i] + nums[j];
                    int target = searchFirstIndex(nums, beforeTarget, length - 1, sum);
                    before = target - j - 1;
                    beforeTarget = target;
                } else {
                    before = length - j - 1;
                }
                num += before;
            }
        }
        return num;
    }

    public static int searchFirstIndex(int[] nums, int fromIndex, int endIndex, int key) {
        while(fromIndex <= endIndex) {
            int mid = fromIndex + (endIndex - fromIndex) / 2;
            if(nums[mid] >= key) {
                endIndex = mid - 1;
            } else {
                fromIndex = mid + 1;
            }
        }
        return fromIndex;
    }

    // 2.0 o(n^2 * logn) 递归
    public static void dfs(int[] nums, int index, int depth) {

        for (int i = index; i < nums.length; i++) {
            if(nums[i] != 0) {
                if(depth == 0) {
                    dfs(nums, i + 1, depth + 1);
                } else if(depth == 1) {
                    int sum = nums[i] + nums[index - 1];
                    int target = searchFirstIndex(nums, i, nums.length - 1, sum);
                    num += (target - i - 1);
                }
            }
        }
    }

    static int num = 0;
    public static int triangleNumber1(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0, 0, 0, 0);
        return num;
    }
    // 1.0 o(n ^ 3)
    public static void dfs(int[] nums, int index, int depth, int first, int second) {

        for (int i = index; i < nums.length; i++) {
            if(nums[i] != 0) {
                if(depth == 0) {
                    dfs(nums, i + 1, depth + 1, nums[i], 0);
                } else if(depth == 1) {
                    if(nums[i] > first) {
                        second = nums[i];
                    } else {
                        second = first;
                        first = nums[i];
                    }
                    dfs(nums, i + 1, depth + 1, first, second);
                } else {
                    int third = nums[i];
                    if(second > nums[i]) {
                        third = second;
                        second = nums[i];
                    }
                    if(first + second > third) {
                        num++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 82245147
        System.out.println(triangleNumber(new int[]{448,37,309,942,542,244,266,708,507,573,288,330,625,298,65,642,122,279,178,443,627,379,103,650,149,449,236,685,20,673,33,179,947,854,332,545,98,103,531,999,978,249,413,705,389,774,315,878,465,329,256,315,290,874,179,517,514,474,973,382,682,23,162,150,290,342,3,134,810,847,311,660,478,295,108,117,218,158,566,728,227,698,408,434,438,445,705,680,163,595,633,96,692,46,403,972,835,307,797,776,728,667,574,460,491,402,794,65,880,558,123,427,875,547,886,324,927,487,782,986,605,672,684,290,218,568,910,941,263,581,769,480,968,308,128,413,922,38,783,890,211,858,395,293,515,320,131,591,863,95,845,461,775,312,634,339,821,692,511,467,849,480,166,152,438,340,900,421,162,879,298,723,381,0,883,377,77,356,903,450,666,688,507,558,507,480,760,948,322,213,889,69,82,724,922,864,242,7,549,122,933,805,921,228,738,349,78,378,780,99,710,961,559,384,740,728,300,875,729,372,178,575,158,611,160,760,101,874,863,363,995,33,837,329,681,166,570,23,680,321,344,354,327,497,133,882,754,733,848,611,606,726,247,708,738,713,607,56,656,525,651,819,157,538,150,525,122,109,340,734,44,819,118,526,66,788,939,806,525,846,270,982,297,115,807,532,126,486,394,37,215,309,668,396,506,910,51,358,103,685,403,489,109,606,348,798,128,244,76,485,734,162,501,578,106,172,915,878,993,9,786,849,282,998,99,158,422,554,694,121,166,812,396,496,221,698,924,399,926,835,360,42,130,9,926,301,607,177,189,666,931,263,945,503,680,441,420,988,533,572,321,403,933,229,861,293,161,245,773,217,656,122,668,610,859,507,758,59,867,674,181,289,89,179,164,926,399,850,65,735,168,432,443,216,332,466,132,612,380,187,828,619,491,449,284,32,670,334,129,970,724,432,611,480,814,639,972,143,747,55,999,159,447,440,677,307,56,677,325,210,863,923,421,203,435,393,558,186,60,721,366,411,945,398,989,721,215,598,781,721,901,175,38,50,497,3,428,873,984,610,770,373,648,152,545,363,259,777,876,595,420,303,787,427,85,133,674,559,796,157,347,276,719,193,678,671,831,37,863,961,270,965,441,448,599,836,10,488,342,128,629,151,514,942,864,653,842,814,581,284,751,819,159,388,184,301,629,866,232,148,513,405,792,606,414,907,439,752,888,146,425,861,545,884,487,617,634,112,725,690,149,301,302,926,249,766,653,697,849,44,952,536,306,568,783,275,113,488,23,689,663,94,68,691,579,484,761,534,299,96,698,154,147,482,626,327,56,290,156,287,810,440,923,588,9,121,973,877,185,990,921,980,845,200,668,317,19,696,199,115,376,814,714,53,58,994,488,800,59,358,413,250,296,917,571,403,51,701,751,990,663,609,724,824,413,30,876,899,424,547,214,36,713,183,306,335,923,676,674,370,16,794,980,875,405,187,184,158,63,476,489,248,846,419,422,347,533,123,19,549,685,150,762,56,84,498,185,139,452,380,974,630,767,992,775,55,769,397,294,863,525,716,962,276,553,55,841,311,690,308,845,536,301,161,972,273,646,673,967,45,538,190,119,68,359,62,429,572,401,775,356,720,330,22,438,94,268,72,566,31,516,18,599,996,379,30,208,94,593,991,156,272,422,963,271,740,225,454,606,644,238,175,985,922,783,643,60,904,166,946,114,527,210,315,536,799,502,160,721,904,653,260,704,274,460,421,801,189,679,749,243,455,969,421,381,20,13,768,314,525,339,951,883,215,289,680,697,357,615,981,264,801,689,734,221,693,111,785,260,737,396,196,298,873,611,546,40,808,563,13,176,874,859,580,325,332,141,367,284,238,707,528,293,957,676,289,100,973,566,261,448,123,539,262,317,900,188,827,920,573,930,23,435,54,239,807,355,584,208,937,245,967,675,332,114,713,161,249,152,911,891,114,432,42,406,345,786,310,398,341,776,485,874,732,613,548,948,649,672,845,139,524,129,991,479,477,808,670,92,288,93,844,277,397,691,887,174,960,21,320,679,445,349,431,725,724,346,90,604,719,365,349,603,135,658,576,658,41,795,293,421,356,399,610,725,99,799,104,479,140,269,585,51,383,414,643,320,601,284,702,249,431,411,868,403,554,426,396,349,882,142,640,251,419,664,474,441,544,719,994,110,936,168,207,579,445,379,949,773,848,597,216,726,643,634,517,790,413,658,71,485,645,31,639,845,709,967,947,637,427,309,792,967,728,474,401,110,580,394,877}));
        System.out.println(triangleNumber(new int[]{2,2,3,4}));
        System.out.println(triangleNumber(new int[]{2,2,3,4,5,6,6,10,17}));

    }

}
