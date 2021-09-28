package com.question.day09;

import java.util.Arrays;

/**
 * <a href="https://leetcode-cn.com/problems/russian-doll-envelopes/">
 * russian doll envelopes</a>
 *
 * @author zms
 * @date 9:11 下午 2021/9/27
 */
public class Code04RussianDollEnvelopes {
    /**
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 注意：不允许旋转信封。
     * <p>
     * 示例 1：
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * <p>
     * 示例 2：
     * 输入：envelopes = [[1,1],[1,1],[1,1]]
     * 输出：1
     * <p>
     * 提示：
     * 1 <= envelopes.length <= 5000
     * envelopes[i].length == 2
     * 1 <= wi, hi <= 10^4
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });
        int n = envelopes.length;
        int[] arr = new int[n];
        int ans = 1;
        arr[0] = envelopes[0][1];
        for (int[] envelope : envelopes) {
            ans = search(arr, ans - 1, envelope[1]);
        }
        return ans;
    }

    // 最长递增子序列（不存在重复的数据）
    // 查找target在数组中的位置，即首个大于target的位置
    public int search(int[] arr, int end, int target) {
        int start = 0;
        int size = end + 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                return size;
            }
        }
        arr[start] = target;
        return Math.max(start + 1, size);
    }
}
