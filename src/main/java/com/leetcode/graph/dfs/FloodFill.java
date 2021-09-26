package com.leetcode.graph.dfs;

import com.Utils;

/**
 * @author zms
 * @date 4:35 PM 2020/5/11
 * <a href="https://leetcode.com/problems/flood-fill/">
 * Flood Fill</a>
 */
public class FloodFill {
    /**
     * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
     *
     * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
     *
     * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
     *
     * At the end, return the modified image.
     *
     * Example 1:
     * Input:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * Output: [[2,2,2],[2,2,0],[2,0,1]]
     * Explanation:
     * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
     * by a path of the same color as the starting pixel are colored with the new color.
     * Note the bottom corner is not colored 2, because it is not 4-directionally connected
     * to the starting pixel.
     * Note:
     *
     * The length of image and image[0] will be in the range [1, 50].
     * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
     * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
     */
    static int oldColor;
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        oldColor = image[sr][sc];
        if(oldColor != newColor) {
            dfs(image, sr, sc, newColor);
        }
        return image;
    }

    public static void dfs(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] != oldColor) {
            return;
        }
        if(image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
        }
//        if(sr == image.length - 1 && sc == image[0].length - 1) {
//            return;
//        }
        if(sr > 0) {
            dfs(image, sr - 1, sc, newColor);
        }
        if(sc > 0) {
            dfs(image, sr, sc - 1, newColor);
        }
        if(sr < image.length - 1) {
            dfs(image, sr + 1, sc, newColor);
        }
        if(sc < image[0].length - 1) {
            dfs(image, sr, sc + 1, newColor);
        }
    }

    public static void main(String[] args) {
        Utils.printTwoArrays(floodFill(new int[][]{
                {1,1,1,1},
                {1,1,0,1},
                {1,0,1,1}
        }, 1, 1, 2));
        Utils.printTwoArrays(floodFill(new int[][]{
                {0,0,0},
                {0,1,1},
        }, 1, 1, 1));
    }
}
