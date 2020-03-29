package com.leetcode;

/**
 * @Author: wcl
 * @Description:
 * @Date: Create in 16:47 2019-03-20
 * @Modified By:
 */
public class ZY {


    public static void main(String[] args) {

    int[] a={2,4,3,1,5,6};

      System.out.println(count(a,  6));
      System.out.println(a. toString());
    }
    private static int num = 0;

    public static int count(int[] a, int n) {

    num = 0;

    mergeSortCounting(a,  0,  n-1);
    return num;
    }

    private static void mergeSortCounting(int[] a, int p, int r) {

        if (p >= r) {
            return;
        }

        int q = (p + r) / 2;

        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);

    }
    private static void merge(int[] a, int p, int q, int r) {

        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {

            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            }else {
                num += (q - i + 1);
                tmp[k++] = a[j++];
            }

         }

          while (i <= q) {
              tmp[k++] = a[i++];
          }

          while (j <= r) {
              tmp[k++] = a[j++];
          }
          for(i=0;i<=r-p;++i) {
              a[p + i] = tmp[i];
          }
    }
}
