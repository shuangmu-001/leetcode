package com.leetcode.search;

/**
 * @author wcl
 * @date 5:52 PM 2020/4/3
 * <a href="https://leetcode.com/problems/first-bad-version/">
 *     First Bad Version</a>
 */
public class FirstBadVersion {
    /**
     * You are a product manager and currently leading a team to develop a new product.
     * Unfortunately, the latest version of your product fails the quality check.
     * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     *
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
     * which causes all the following ones to be bad.
     *
     * You are given an API bool isBadVersion(version) which will return whether version is bad.
     * Implement a function to find the first bad version. You should minimize the number of calls to the API.
     *
     * Example:
     *
     * Given n = 5, and version = 4 is the first bad version.
     *
     * call isBadVersion(3) -> false
     * call isBadVersion(5) -> true
     * call isBadVersion(4) -> true
     * Then 4 is the first bad version.
     */
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left <= right) {
            // 用long 和 left + (right - left) / 2 解决(right + left) / 2 的溢出
            int mid = (left + (right - left) / 2);
            if(isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version);
     */
    private static int target = 1702766719;
    static boolean isBadVersion(int version) {
        return version >= target;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(2126753390) == 1702766719);
    }
}
