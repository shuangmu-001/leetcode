package com.data.structure.tree.question;

/**
 * @author wcl
 * @date 3:34 下午 2021/5/7
 */
public class PaperFolding {
    /**
     * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折一次，压出折痕后展开。
     * 此时折痕是凹下去的，即折痕凸起的方向指向纸条的背面。
     * 如果从纸条的下边向上方连续对折两次，压出折痕后展开，
     * 此时有三条折痕，从上倒下依次是下折痕、下折痕和上折痕。
     * <p>
     * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请从上到下打印折痕的方向；
     * 例如：N=1时，打印：down；N=2时，打印：down down up
     */
    public static void printAllFolds(int N) {
        helper(1, N, false);
        System.out.println();
    }

    public static void helper(int level, int N, boolean flag) {
        if(level > N) {
            return;
        }
        helper(level + 1, N, false);
        System.out.print(flag ? "up " : "down ");
        helper(level + 1, N, true);
    }

    public static void main(String[] args) {
        printAllFolds(1);
        printAllFolds(2);
        printAllFolds(3);
    }

}
