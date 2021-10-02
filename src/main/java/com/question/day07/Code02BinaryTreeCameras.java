package com.question.day07;

import com.leetcode.graph.tree.bt.TreeNode;

/**
 * <a href="https://leetcode.com/problems/binary-tree-cameras/">Binary Tree Cameras</a>
 *
 * @author zms
 * @date 8:56 下午 2021/9/25
 */
public class Code02BinaryTreeCameras {

    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     * 示例 1：
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     * <p>
     * 示例 2：
     * 输入：[0,0,null,0,null,0,null,null,0]
     * 输出：2
     * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     * <p>
     * 提示：
     * 给定树的节点数的范围是[1, 1000]。
     * 每个节点的值都是 0。
     */
    public int minCameraCover1(TreeNode root) {
        CoverInfo process = process1(root);
        return (int) Math.min(process.unCovered + 1, Math.min(process.coveredNoCamera, process.coveredHasCamera));
    }

    // coveredNoCamera coveredHasCamera unCovered
    private static class CoverInfo {
        // 当前节点没有被监控到的
        long unCovered;
        // 当前节点被监控到但是节点上没有安装相机
        long coveredNoCamera;
        // 当前节点被监控到但是节点上安装相机
        long coveredHasCamera;

        public CoverInfo(long unCovered, long coveredNoCamera, long coveredHasCamera) {
            this.unCovered = unCovered;
            this.coveredNoCamera = coveredNoCamera;
            this.coveredHasCamera = coveredHasCamera;
        }
    }

    public CoverInfo process1(TreeNode root) {
        if (root == null) {
            return new CoverInfo(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        CoverInfo left = process1(root.left);
        CoverInfo right = process1(root.right);
        // 保证当前节点一下的节点都被监控到
        long unCovered = left.coveredNoCamera + right.coveredNoCamera;
        // 当前节点在没有安装相机的情况下被监控，则左右节点至少有一个节点有安装相机
        long coveredNoCamera = Math.min(
                left.coveredHasCamera + right.coveredNoCamera,
                Math.min(left.coveredNoCamera + right.coveredHasCamera,
                        left.coveredHasCamera + right.coveredHasCamera
                ));
        // 当前节点在安装相机的情况下，则左右节点最少相机情况和+1
        long coveredHasCamera = Math.min(left.unCovered, Math.min(left.coveredNoCamera, left.coveredHasCamera))
                + Math.min(right.unCovered, Math.min(right.coveredNoCamera, right.coveredHasCamera))
                + 1;
        return new CoverInfo(unCovered, coveredNoCamera, coveredHasCamera);
    }

    // 为什么只会存在一种状态呢？
    // 左右子树的最小和也是整棵树数的最小
    private enum CoverStatus {
        // 当前节点没有被监控到的
        UNCOVERED,
        // 当前节点被监控到但是节点上没有安装相机
        COVERED_NO_CAMERA,
        // 当前节点被监控到但是节点上安装相机
        COVERED_HAS_CAMERA
    }

    private static class Info {
        // 当前节点是否被监控以及是否有安装相机的状态
        CoverStatus status;
        // 当前节点的所有子节点都被监控的最少相机数
        int cameras;

        public Info(CoverStatus status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public int minCameraCover(TreeNode root) {
        Info info = process(root);
        return CoverStatus.UNCOVERED.equals(info.status) ? info.cameras + 1 : info.cameras;
    }

    public Info process(TreeNode root) {

        if (root == null) {
            return new Info(CoverStatus.COVERED_NO_CAMERA, 0);
        }

        Info left = process(root.left);
        Info right = process(root.right);
        // 当前节点默认状态是没有被监控
        CoverStatus status = CoverStatus.UNCOVERED;
        int cameras = left.cameras + right.cameras;
        // 至少有一个子节点没有被监控呢，则当前节点需要安装相机，当前节点的状态就是有相机被监控
        if (CoverStatus.UNCOVERED.equals(left.status) || CoverStatus.UNCOVERED.equals(right.status)) {
            status = CoverStatus.COVERED_HAS_CAMERA;
            cameras += 1;
        }
        // 至少有一个子节点有相机，则当前节点不需要安装相机，当前节点的状态就是没有相机被监控
        else if (CoverStatus.COVERED_HAS_CAMERA.equals(left.status) ||
                CoverStatus.COVERED_HAS_CAMERA.equals(right.status)) {
            status = CoverStatus.COVERED_NO_CAMERA;
        }
        return new Info(status, cameras);
    }
}
