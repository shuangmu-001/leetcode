package com.leetcode.math;

/**
 * @author zms
 * @date 3:33 下午 2020/9/17
 * <a href="https://leetcode.com/problems/robot-bounded-in-circle/">Robot Bounded In Circle</a>
 */
public class RobotBoundedInCircle {
    /**
     * 在无限的平面上，机器人最初位于(0, 0)处，面朝北方。机器人可以接受下列三条指令之一：
     *
     * "G"：直走 1 个单位
     * "L"：左转 90 度
     * "R"：右转 90 度
     * 机器人按顺序执行指令instructions，并一直重复它们。
     *
     * 只有在平面中存在环使得机器人永远无法离开时，返回true。否则，返回 false。
     *
     * 示例 1：
     *
     * 输入："GGLLGG"
     * 输出：true
     * 解释：
     * 机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。
     * 重复这些指令，机器人将保持在以原点为中心，2 为半径的环中进行移动。
     * 示例 2：
     *
     * 输入："GG"
     * 输出：false
     * 解释：
     * 机器人无限向北移动。
     * 示例 3：
     *
     * 输入："GL"
     * 输出：true
     * 解释：
     * 机器人按 (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ... 进行移动。
     * 
     *
     * 提示：
     *
     * 1 <= instructions.length <= 100
     * instructions[i] 在{'G', 'L', 'R'}中
     *
     */
    public boolean isRobotBounded1(String instructions) {
        int x = 0;
        int y = 0;
        int deg = 0;
        for(char c : instructions.toCharArray()) {
            if(c == 'L') {
                deg += 90;
                deg %= 360;
            } else if(c == 'R') {
                deg += 270;
                deg %= 360;
            } else if(c == 'G') {
                if(deg == 90) {
                    y--;
                } else if(deg == 270) {
                    y++;
                } else if(deg == 180) {
                    x--;
                } else if(deg == 0) {
                    x++;
                }
            }
        }
        return deg != 0 || (x == 0 && y == 0);
    }

    public boolean isRobotBounded(String instructions) {
        // 北 = 0, 东 = 1, 南 = 2, 西 = 3;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0;
        int y = 0;
        int deg = 0;
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                deg = (deg + 3) % 4;
            } else if (c == 'R') {
                deg = (deg + 1) % 4;
            } else if (c == 'G') {
                x += directions[deg][0];
                y += directions[deg][1];
            }
        }
        return deg != 0 || (x == 0 && y == 0);
    }

    public static void main(String[] args) {
        System.out.println((-270) % 360);
        System.out.println(new RobotBoundedInCircle().isRobotBounded("GLRLLGLL"));
    }
}
