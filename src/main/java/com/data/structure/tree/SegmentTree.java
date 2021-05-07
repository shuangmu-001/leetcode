package com.data.structure.tree;

/**
 * 左神
 * 线段树（区间修改树） 解决区间的修改，更新，查询等问题
 * 目标是：O(logN)
 * @author zms
 * @date 5:47 下午 2021/4/27
 */
public interface SegmentTree {
    /**
     * 从l到r的位置所有的数字都加v
     *
     * @param l   开始位置
     * @param r   结束位置
     * @param v   增加的数值
     * @param arr 目标数组
     */
    void add(int l, int r, int v, int[] arr);

    /**
     * 从l到r的位置所有的数字变成v
     *
     * @param l   开始位置
     * @param r   结束位置
     * @param v   目标数字
     * @param arr 目标数组
     */
    void update(int l, int r, int v, int[] arr);

    /**
     * @param l   开始位置
     * @param r   结束位置
     * @param arr 目标数组
     * @return 返回l到r的累加和
     */
    int getSum(int l, int r, int[] arr);

}
