package com.leetcode.graph.dfs;

/**
 * TODO 1、拓扑排序；2、判断是否有环
 * <a href="https://leetcode.com/problems/course-schedule/">Course Schedule</a>
 *
 * @author zms
 * @date 9:34 下午 2020/5/29
 */
public class CourseSchedule {
    /**
     * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，
     * 表示如果要学习课程ai 则 必须 先学习课程 bi 。
     * <p>
     * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * <p>
     * 示例 2：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     * <p>
     * 提示：
     * 1 <= numCourses <= 10^5
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * prerequisites[i] 中的所有课程对 互不相同
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }
}
