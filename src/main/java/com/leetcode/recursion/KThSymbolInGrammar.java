package com.leetcode.recursion;

/**
 * @author zms
 * @date 3:47 下午 2020/9/3
 * <a href="https://leetcode.com/problems/k-th-symbol-in-grammar/">K-th Symbol in Grammar</a>
 */
public class KThSymbolInGrammar {
    /**
     * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     *
     * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
     *
     * Examples:
     * Input: N = 1, K = 1
     * Output: 0
     *
     * Input: N = 2, K = 1
     * Output: 0
     *
     * Input: N = 2, K = 2
     * Output: 1
     *
     * Input: N = 4, K = 5
     * Output: 1
     *
     * Explanation:
     * row 1: 0
     * row 2: 01
     * row 3: 0110
     * row 4: 01101001
     * Note:
     *
     * N will be an integer in the range [1, 30].
     * K will be an integer in the range [1, 2^(N-1)].
     */
    public static int kthGrammar(int N, int K) {
        if(N == 1) {
            return 0;
        }
        return (~K & 1) ^ kthGrammar(N - 1, (K + 1) >> 1);
    }

    public  static int kthGrammar1(int N, int K) {
        if(N == 1) {
            return 0;
        }
        int res = kthGrammar1(N - 1, (K + 1) >> 1);
        if((res & 1) == 0) {
            return res ^ 1;
        } else {
            return res & 1;
        }
    }

    public static int kthGrammar2(int N, int K) {
        if(N == 1) {
            return 0;
        }
        int res = kthGrammar2(N - 1, (K + 1) >> 1);
        if((res & 1) == 0) {
            return res ^ 1;
        } else {
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(kthGrammar1(19, 1222) == kthGrammar2(19, 1222));
    }

}
