package com.leetcode.integers;

/**
 * @author wcl
 * @date 4:19 PM 2020/2/22
 * {@link "https://leetcode.com/problems/2-keys-keyboard/"}
 */
public class KeysKeyboard2 {
    /**
     * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
     *      Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
     *      Paste: You can paste the characters which are copied last time.
     * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
     * Output the minimum number of steps to get n 'A'.
     *
     * Example 1:
     *      Input: 3
     *      Output: 3
     *      Explanation:
     *          Intitally, we have one character 'A'.
     *          In step 1, we use Copy All operation.
     *          In step 2, we use Paste operation to get 'AA'.
     *          In step 3, we use Paste operation to get 'AAA'.
     */
    public static int minSteps(int n) {
        if(n == 1) {
            return 0;
        }
        int sqrt = (int) Math.sqrt(n);
        int result = 0;
        int divisor = 2;
        while(divisor <= sqrt) {
            if(n % divisor == 0) {
                result += divisor;
                n /= divisor;
                sqrt = (int) Math.sqrt(n);
            } else {
                divisor++;
            }
        }
//        System.out.println("divisor : " + divisor);
//        System.out.println("n : " + n);
        if(result == 0) {
            result = n;
        } else {
            result += n;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minSteps(3) == 3);
        System.out.println(minSteps(4) == 4);
        System.out.println(minSteps(11) ==11);
        System.out.println(minSteps(30) == 10);
        System.out.println(minSteps(300) == 17);
        System.out.println(minSteps(40) == 11);
        System.out.println(minSteps(21) == 10);
        System.out.println(minSteps(23) == 23);
        System.out.println(minSteps(47) == 47);
        System.out.println(minSteps(133) == 26);
    }
}
