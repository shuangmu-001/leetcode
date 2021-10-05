package com.question.day09;

import com.Test;
import com.Utils;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/">
 * The Number of Weak Characters in the Game</a>
 *
 * @author zms
 * @date 10:54 上午 2021/9/29
 */
public class Code05TheNumberOfWeakCharactersInTheGame implements Test {
    /**
     * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，
     * 其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
     * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。
     * 更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
     * 返回 弱角色 的数量。
     * <p>
     * 示例 1：
     * 输入：properties = [[5,5],[6,3],[3,6]]
     * 输出：0
     * 解释：不存在攻击和防御都严格高于其他角色的角色。
     * <p>
     * 示例 2：
     * 输入：properties = [[2,2],[3,3]]
     * 输出：1
     * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
     * <p>
     * 示例 3：
     * 输入：properties = [[1,5],[10,4],[4,3]]
     * 输出：1
     * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
     * <p>
     * 提示：
     * 2 <= properties.length <= 10^5
     * properties[i].length == 2
     * 1 <= attacki, defensei <= 10^5
     */
    // 暴力解
    public static int numberOfWeakCharacters01(int[][] properties) {
        int ans = 0;
        int n = properties.length;
        for (int[] ints : properties) {
            for (int[] property : properties) {
                if (ints[0] < property[0] && ints[1] < property[1]) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static int numberOfWeakCharacters02(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });
        int n = properties.length;
        int ans = 0;
        int attack = properties[n - 1][0];
        int defense = properties[n - 1][1];
        for(int i = n - 2; i >= 0; i--) {
            if(properties[i][1] < defense) {
                ans++;
            } else if(attack == properties[i][0]) {
                defense = properties[i][1];
            } else {
                attack = properties[i][0];
                defense = properties[i][1];
            }
        }
        return ans;
    }
    public static int numberOfWeakCharacters(int[][] properties) {
    	int maxAttack = 0;
    	for(int[] property: properties) {
    		maxAttack = Math.max(maxAttack, property[0]);
    	}
    	int[] bucket = new int[maxAttack + 1];
    	for(int[] p : properties) {
    		bucket[p[0]] = Math.max(bucket[p[0]], p[1]);
    	}
    	int rightMax = bucket[maxAttack];
    	for(int i = maxAttack; i >= 0; i--) {
    		if(rightMax > bucket[i]) {
    			bucket[i] = rightMax;
    		} else {
    			rightMax = bucket[i];
    		}
    	}
    	int ans = 0;
    	for(int[] p : properties) {
    		if(bucket[p[0] + 1] > p[1]) {
    			ans++;
    		}
    	}
    	return ans;
    }
    @Override
    public void test(int n) {
        int[][] properties = genRandomTwoArr(n + 1, 2, 10 ^ 5, true);
        int ans01 = numberOfWeakCharacters(properties);
        int ans02 = numberOfWeakCharacters01(properties);
        if(ans01 != ans02) {
            System.out.println("思路有误");
            Utils.printArrays(properties);
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        int[][] properties = {
                {7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}
        };
        System.out.println(numberOfWeakCharacters(properties));
    }
}
