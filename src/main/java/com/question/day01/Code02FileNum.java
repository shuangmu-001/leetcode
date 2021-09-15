package com.question.day01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 *
 * @author wcl
 * @date 3:46 下午 2021/9/15
 */
public class Code02FileNum {

    public static int getFileNum(String dir) {
        File root = new File(dir);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int count = 0;
        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File parent = queue.poll();
            File[] files = parent.listFiles();
            if (files == null) {
                continue;
            }
            for (File child : files) {
                if (child.isFile()) {
                    count++;
                } else if (child.isDirectory()) {
                    queue.add(child);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/src/main/java/com";
        System.out.println(getFileNum(path));
    }
}
