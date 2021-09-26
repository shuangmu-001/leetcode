package com;

import com.leetcode.graph.tree.bt.TreeNode;
import com.leetcode.graph.tree.linkedList.FlattenAMultilevelDoublyLinkedList;
import com.leetcode.graph.tree.linkedList.ListNode;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zms
 * @date 2:37 PM 2019/12/27
 */
public class Utils {

    public static void printTwoArrays(int[][] arr) {
        System.out.println("------二维数组的打印----------");
        for (int[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%11d,", ints[j]);
            }
            System.out.println();
        }
    }

    public static void printTwoArrays(boolean[][] arr) {
        System.out.println("------二维数组的打印----------");
        for (boolean[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf("%5s, ", ints[j]);
            }
            System.out.println();
        }
    }

    public static void printTwoArrays(Object[][] arr) {
        System.out.println("------二维数组的打印----------");
        for (Object[] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(ints[j] + ",          ");
            }
            System.out.println();
        }
    }

    public static void printThreeArrays1(int[][][] arr) {
        System.out.println("------三维数组的打印----------");
        for (int[][] ints : arr) {
            for (int[] i : ints) {
                System.out.printf("%s,", Arrays.toString(i));
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
                    System.out.printf("%11d,", j);
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
            if (ints != null) {
                System.out.print(ints + "---" + ints.hashCode());
            } else {
                System.out.print("null");
            }

            System.out.print(";");
        }
        System.out.println();
    }

    public static void heapSort(int[][] pairs) {
        int len = pairs.length;
        for (int i = pairs.length - 1; i >= 0; i--) {
            heapify(pairs, i, len);
        }
        for (int i = pairs.length - 1; i > 0; i--) {
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
                i = k;
            } else {
                break;
            }
        }
    }

    public static void heapSort(int[] pairs) {
        int len = pairs.length;
        for (int i = pairs.length - 1; i >= 0; i--) {
            heapify(pairs, i, len);
        }
        for (int i = pairs.length - 1; i > 0; i--) {
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
                i = k;
            } else {
                break;
            }
        }
    }

    public static String mystery(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }

        String a = s.substring(0, n / 2);
        String b = s.substring(n / 2, n);
        return mystery(b) + mystery(a);
    }

    public static ListNode arrayToListNode(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        ListNode res = new ListNode(0);
        ListNode dummy = res;
        for (int arr : num) {
            dummy.next = new ListNode(arr);
            dummy = dummy.next;
        }
        return res.next;
    }

    public static TreeNode arrayToTreeNode(Integer[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        TreeNode res = new TreeNode(num[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(res);
        for (int i = 1; i < num.length; i++) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                return null;
            }
            if (num[i] != null) {
                TreeNode node = new TreeNode(num[i]);
                poll.left = node;
                queue.add(node);
            }
            i++;

            if (i < num.length && num[i] != null) {
                TreeNode node = new TreeNode(num[i]);
                poll.right = node;
                queue.add(node);
            }
        }
        return res;
    }

    public static FlattenAMultilevelDoublyLinkedList.Node arrayToMultilevleDoublyNode(Integer[]... num) {
        FlattenAMultilevelDoublyLinkedList.Node head = null;
        int preIndex;
        int childIndex = Integer.MAX_VALUE;
        FlattenAMultilevelDoublyLinkedList.Node child = null;
        for (int i = num.length - 1; i >= 0; i--) {
            Integer[] n = num[i];
            int index = 0;
            while (n[index] == null) {
                index++;
            }
            if (index > childIndex) {
                throw new IllegalArgumentException("请检验参数");
            }
            preIndex = index;
            FlattenAMultilevelDoublyLinkedList.Node start = null;
            FlattenAMultilevelDoublyLinkedList.Node pre = null;

            for (int j = index; j < n.length; j++) {
                if (n[j] == null) {
                    throw new IllegalArgumentException("请检验参数");
                }
                FlattenAMultilevelDoublyLinkedList.Node node = new FlattenAMultilevelDoublyLinkedList.Node(n[j]);
                if (index == j) {
                    start = node;
                } else {
                    pre.next = node;
                }
                if (j == childIndex) {
                    node.child = child;
                    child = start;
                }
                pre = node;
            }
            child = start;
            childIndex = preIndex;
            if (i == 0) {
                head = start;
            }
        }
        return head;
    }

    public static void printBT(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        System.out.print(node.val + ", ");
        while (!queue.isEmpty()) {
            TreeNode pop = queue.poll();
            if (pop.left == null) {
                System.out.print(null + ", ");
            } else {
                System.out.print(pop.left.val + ", ");
                queue.add(pop.left);
            }
            if (pop.right == null) {
                System.out.print(null + ", ");
            } else {
                System.out.print(pop.right.val + ", ");
                queue.add(pop.right);
            }
        }
        System.out.println();
    }

    public static void printLinkedList(ListNode node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(mystery("12345"));
        System.out.println(new File("./src/main/resources/non-negative.txt").exists());
        System.out.println(read("./src/main/resources/array.txt", 30000));
    }


    // TODO 1、读取文件数据作为输出 (Time Limit Exceeded)
    public static List<int[]> read(String file, final int len) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines()
                    .map(s -> s.split(","))
                    .map(strs -> strArrayToIntArray(strs, len))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] strArrayToIntArray(String[] strs, int len) {
        int length = len == 0 ? strs.length : len;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        return nums;
    }

    public static List<int[]> read(String file, final int len, boolean random) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines()
                    .map(s -> s.split(","))
                    .map(strs -> strArrayToIntArray(strs, len, random))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] strArrayToIntArray(String[] strs, int len, boolean random) {
        if (strs.length < len && random) {
            throw new IllegalArgumentException("数据太少不能随机");
        }
        int[] nums = new int[len];
        ThreadLocalRandom current = ThreadLocalRandom.current();

        for (int i = 0; i < len; i++) {
            if (random) {
                nums[i] = Integer.parseInt(strs[current.nextInt(0, strs.length - 1)]);
            } else {
                nums[i] = Integer.parseInt(strs[i]);
            }
        }
        return nums;
    }

    public static long consumeTime(Consumer<Object> consumer, Object t) {
        long before = System.currentTimeMillis();
        consumer.accept(t);
        long end = System.currentTimeMillis();
        return end - before;
    }

    public static void addLog(String str) {
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile("out.log", "rw");
            FileChannel channel = rw.getChannel();

            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, rw.length(), str.length());
            int capacity = map.capacity();
            System.out.println("MappedByteBuffer : " + capacity);
            byte[] bytes = str.getBytes();
            for (byte b : bytes) {
                map.put(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(rw != null ) {
                try {
                    rw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
