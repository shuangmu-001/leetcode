package com;

import java.util.Arrays;

/**
 * @author wcl
 * @date 10:32 上午 2021/5/12
 */
public class Demo {

    public static String[] merge(String[] str1, String[] str2) {
        if(str1 == null || str1.length == 0) {
            return str2;
        }
        if(str2 == null || str2.length == 0) {
            return str1;
        }
        String[] strings = new String[str1.length];
        for (int i = 0, j = 0; i < str1.length; i++, j++) {
            if(j >= str2.length) {
                j = 0;
            }
            strings[i] = str1[i] + str2[j];
        }
        return strings;
    }

    public static void main(String[] args) {
        String[] str1 = {"A", "B", "C", "D", "E", "F"};
        String[] str2 = {"1", "2", "3", "4", "5", "6", "7"};
        String[] str3 = {"1", "2", "3", "4"};
        System.out.println(Arrays.toString(merge(str1, str2)));
        System.out.println(Arrays.toString(merge(str1, str3)));
    }
}
