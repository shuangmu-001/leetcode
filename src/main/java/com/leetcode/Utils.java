package com.leetcode;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author wcl
 * @date 2:37 PM 2019/12/27
 */
public class Utils {

    public static void printTwoArrays(int[][] arr) {
        System.out.println("------二维数组的打印----------");
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(String.format("%11d,", ints[j]));
            }
            System.out.println();
        }
    }

    public static void printThreeArrays(int[][][] arr) {
        System.out.println("------三维数组的打印----------");
        for (int[][] ints : arr) {
            System.out.println("----------------");
            for (int[] i : ints) {
                for (int j : i) {
                    System.out.print(String.format("%11d,", j));
                }
                System.out.println();
            }
        }
    }

    public static void printArrays(int[] arr) {
        System.out.println("------一维数组的打印----------");
        for (int ints : arr) {
            System.out.print(ints);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void printArrays(long[] arr) {
        System.out.println("------一维数组的打印----------");
        for (long ints : arr) {
            System.out.print(ints);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void printArrays(char[] arr) {
        System.out.println("------一维数组的打印----------");
        for (char ints : arr) {
            System.out.print(ints);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void printArrays(boolean[] arr) {
        System.out.println("------一维数组的打印----------");
        for (boolean ints : arr) {
            System.out.print(ints);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void printArrays(byte[] arr) {
        System.out.println("------一维数组的打印----------");
        for (byte ints : arr) {
            System.out.print(ints);
            System.out.print(",");
        }
        System.out.println();
    }

    public static void printArrays(Object[] arr) {
        System.out.println("------一维数组的打印----------");
        for (Object ints : arr) {
            System.out.println(ints);
//            System.out.print(";");
        }
        System.out.println();
    }

    public static void heapSort(int[][] pairs) {
        int len = pairs.length;
        for(int i = pairs.length - 1; i >= 0; i--) {
            heapify(pairs, i, len);
        }
        for(int i = pairs.length - 1; i > 0; i--) {
            swap(pairs, 0, i);
            heapify(pairs, 0, i);
        }
    }
    public static void swap(int[][] pairs, int i, int j) {
        int[] temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    public static void heapify(int[][] pairs, int i, int len) {
        int temp = pairs[i][1];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && pairs[k][1] < pairs[k + 1][1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            if (pairs[k][1] > temp) {
                swap(pairs, i, k);
                i  =  k;
            } else {
                break;
            }
        }
    }

    public static void heapSort(int[] pairs) {
        int len = pairs.length;
        for(int i = pairs.length - 1; i >= 0; i--) {
            heapify(pairs, i, len);
        }
        for(int i = pairs.length - 1; i > 0; i--) {
            swap(pairs, 0, i);
            heapify(pairs, 0, i);
        }
    }
    public static void swap(int[] pairs, int i, int j) {
        int temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    public static void heapify(int[] pairs, int i, int len) {
        int temp = pairs[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && pairs[k] < pairs[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            if (pairs[k] > temp) {
                swap(pairs, i, k);
                i  =  k;
            } else {
                break;
            }
        }
    }

    public static String mystery(String s) {
        int n = s.length();
        if(n <= 1) {
            return s;
        }

        String a = s.substring(0, n / 2);
        String b = s.substring(n / 2, n);
        return mystery(b) + mystery(a);
    }

    public static void main(String[] args) {
        System.out.println(mystery("12345"));
    }

}
