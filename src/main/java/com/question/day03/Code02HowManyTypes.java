package com.question.day03;

import com.Test;
import com.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 位操作
 *
 * @author wcl
 * @date 7:46 下午 2021/9/19
 */
public class Code02HowManyTypes implements Test {

    //只由小写字母（a-z）组成的一批字符串都放在字符串类型的数组String[] arr 中，
    //如果其中两个字符串所含有的字符种类完全一样，将两个字符串算作一类，
    //比如 aaaabbbc 和bac就算作一类，返回arr中有多少类
    public int howMayTypes(String[] arr) {
        if (arr == null) {
            return 0;
        }
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        // 去重
        Set<Integer> res = new HashSet<>();
        for (String s : arr) {
            // 用位运算进行操作
            // 由于字符串是有小写字母组成，可以将每个小写字母看成一位（组成一样就算是一类），
            // 将字符串转换成Integer
            int type = 0;
            for (char c : s.toCharArray()) {
                type |= (1 << (c - 'a'));
            }
            res.add(type);
        }
        return res.size();
    }

    public int howMayTypes1(String[] arr) {
        if (arr == null) {
            return 0;
        }
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        // 组成一样就算是一类
        // 将字符串排序去重
        Set<String> res = new HashSet<>();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chars[0]);
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] != chars[i - 1]) {
                    stringBuilder.append(chars[i]);
                }
            }
            res.add(stringBuilder.toString());
        }
        return res.size();
    }

    @Override
    public String getSourceStr() {
        return "abcdefghijklmnopqrstwvuxyz";
    }

    @Override
    public void test(int n) {
        String[] s = genTargetStrArr(n);
        if (howMayTypes(s) != howMayTypes1(s)) {
            System.out.println("不同种类数");
            Utils.printArrays(s);
        }
    }
}
