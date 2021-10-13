package com.leetcode.dp.game;

/**
 * <a href="https://leetcode.com/problems/stone-game-iii/">Stone Game III</a>
 *
 * @author zms
 * @date 6:57 下午 2021/10/12
 */
public class StoneGameIII {
    /**
     * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
     * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
     * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
     * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
     * <p>
     * 示例 1：
     * 输入：values = [1,2,3,7]
     * 输出："Bob"
     * 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
     * <p>
     * 示例 2：
     * 输入：values = [1,2,3,-9]
     * 输出："Alice"
     * 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
     * 如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
     * 如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
     * 注意，他们都应该采取 最优策略 ，所以在这里 Alice 将选择能够使她获胜的方案。
     * <p>
     * 示例 3：
     * 输入：values = [1,2,3,6]
     * 输出："Tie"
     * 解释：Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
     * <p>
     * 示例 4：
     * 输入：values = [1,2,3,-1,-2,-3,7]
     * 输出："Alice"
     * <p>
     * 示例 5：
     * 输入：values = [-1,-2,-3]
     * 输出："Tie"
     * <p>
     * 提示：
     * 1 <= values.length <= 50000
     * -1000<= values[i] <= 1000
     */
    public static String stoneGameIII01(int[] stoneValue) {
        int before = before01(stoneValue, 0);
        int after = after01(stoneValue, 0);
        return before > after ? "Alice" : before < after ? "Bob" : "Tie";
    }

    public static int before01(int[] arr, int index) {
        int length = arr.length;
        if (index >= length) {
            return 0;
        }
        if (index == length - 1) {
            return arr[index];
        }
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < 3 && index + i < length; i++) {
            sum += arr[index + i];
            ans = Math.max(ans, sum + after01(arr, index + i + 1));
        }
        return ans;
    }

    public static int after01(int[] arr, int index) {
        int length = arr.length;
        if (index >= length - 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3 && index + i < length; i++) {
            ans = Math.min(ans, before01(arr, index + i + 1));
        }
        return ans;
    }

    public static String stoneGameIII02(int[] stoneValue) {
        int length = stoneValue.length;
        Integer[] fMap = new Integer[length];
        Integer[] gMap = new Integer[length];
        int before = before02(stoneValue, 0, fMap, gMap);
        int after = after02(stoneValue, 0, fMap, gMap);
        return before > after ? "Alice" : before < after ? "Bob" : "Tie";
    }

    public static int before02(int[] arr, int index, Integer[] fMap, Integer[] gMap) {
        int length = arr.length;
        if (index >= length) {
            return 0;
        }
        if (fMap[index] != null) {
            return fMap[index];
        }
        if (index == length - 1) {
            fMap[index] = arr[index];
            return fMap[index];
        }
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < 3 && index + i < length; i++) {
            sum += arr[index + i];
            ans = Math.max(ans, sum + after02(arr, index + i + 1, fMap, gMap));
        }
        fMap[index] = ans;
        return ans;
    }

    public static int after02(int[] arr, int index, Integer[] fMap, Integer[] gMap) {
        int length = arr.length;
        if (index >= length) {
            return 0;
        }
        if (gMap[index] != null) {
            return gMap[index];
        }
        if (index == length - 1) {
            gMap[index] = 0;
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3 && index + i < length; i++) {
            ans = Math.min(ans, before02(arr, index + i + 1, fMap, gMap));
        }
        gMap[index] = ans;
        return ans;
    }

    public static String stoneGameIII03(int[] stoneValue) {
        int length = stoneValue.length;
        int[] fMap = new int[length + 1];
        int[] gMap = new int[length + 1];
        fMap[length - 1] = stoneValue[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            fMap[i] = Integer.MIN_VALUE;
            gMap[i] = Integer.MAX_VALUE;
            int sum = 0;
            int max = Math.min(3, length - i);
            for (int j = 0; j < max; j++) {
                sum += stoneValue[i + j];
                fMap[i] = Math.max(fMap[i], sum + gMap[i + j + 1]);
                gMap[i] = Math.min(gMap[i], fMap[i + j + 1]);
            }
        }
        return fMap[0] > gMap[0] ? "Alice" : fMap[0] < gMap[0] ? "Bob" : "Tie";
    }

    public static void main(String[] args) {
        System.out.println(stoneGameIII03(new int[]{1, 2, 3, 7}));
        System.out.println(stoneGameIII03(new int[]{1, 2, 3, -9}));
        System.out.println(stoneGameIII03(new int[]{1, 2, 3, 6}));
        System.out.println(stoneGameIII03(new int[]{1, 2, 3, -1, -2, -3, 7}));
        System.out.println(stoneGameIII03(new int[]{-1, -2, -3}));
    }
}
