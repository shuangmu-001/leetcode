package com.leetcode.dp.game;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO <a href="https://leetcode.com/problems/stone-game-ii/">Stone Game II</a>
 *
 * @author zms
 * @date 6:11 下午 2021/4/23
 */
public class StoneGameII {
    /**
     * Alice and Bob continue their games with piles of stones.
     * There are a number of piles arranged in a row,
     * and each pile has a positive integer number of stones piles[i].
     * The objective of the game is to end with the most stones.
     * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
     * On each player's turn, that player can take all the stones in the first X remaining piles,
     * where 1 <= X <= 2M.  Then, we set M = max(M, X).
     * The game continues until all the stones have been taken.
     * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
     * <p>
     * Example 1:
     * Input: piles = [2,7,9,4,4]
     * Output: 10
     * Explanation:  If Alice takes one pile at the beginning,
     * Bob takes two piles, then Alice takes 2 piles again.
     * Alice can get 2 + 4 + 4 = 10 piles in total.
     * If Alice takes two piles at the beginning,
     * then Bob can take all three piles left.
     * In this case, Alice get 2 + 7 = 9 piles in total.
     * So we return 10 since it's larger.
     * <p>
     * Example 2:
     * Input: piles = [1,2,3,4,5,100]
     * Output: 104
     * <p>
     * Constraints:
     * 1 <= piles.length <= 100
     * 1 <= piles[i] <= 10^4
     */
    public static int stoneGameII01(int[] piles) {
        return before01(piles, 0, 1);
    }

    public static int sum(int[] arr, int i, int j) {
        int sum = 0;
        for (int k = i; k < j; k++) {
            sum += arr[k];
        }
        return sum;
    }

    public static int before01(int[] piles, int index, int m) {
        int max = index + (m << 1);
        if (max >= piles.length) {
            return sum(piles, index, piles.length);
        }
        int sum = 0;
        int ans = 0;
        for (int i = index; i < max; i++) {
            sum += piles[i];
            int next = Math.max(m, i - index + 1);
            ans = Math.max(ans, sum + after01(piles, i + 1, next));
        }
        return ans;
    }

    public static int after01(int[] piles, int index, int m) {
        int max = index + (m << 1);
        if (max >= piles.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = index; i < max; i++) {
            int next = Math.max(m, i - index + 1);
            ans = Math.min(ans, before01(piles, i + 1, next));
        }
        return ans;
    }

    public static int stoneGameII02(int[] piles) {
        Map<Integer, Map<Integer, Integer>> fMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> gMap = new HashMap<>();
        return before02(piles, 0, 1, fMap, gMap);
    }

    public static int before02(int[] piles, int index, int m,
                               Map<Integer, Map<Integer, Integer>> fMap,
                               Map<Integer, Map<Integer, Integer>> gMap) {
        if (fMap.containsKey(index) && fMap.get(index).containsKey(m)) {
            return fMap.get(index).get(m);
        }
        int max = index + (m << 1);
        if (max >= piles.length) {
            return sum(piles, index, piles.length);
        }
        int sum = 0;
        int ans = 0;
        for (int i = index; i < max; i++) {
            sum += piles[i];
            int next = Math.max(m, i - index + 1);
            ans = Math.max(ans, sum + after02(piles, i + 1, next, fMap, gMap));
        }
        if (!fMap.containsKey(index)) {
            fMap.put(index, new HashMap<>());
        }
        fMap.get(index).put(m, ans);
        return ans;
    }

    public static int after02(int[] piles, int index, int m,
                              Map<Integer, Map<Integer, Integer>> fMap,
                              Map<Integer, Map<Integer, Integer>> gMap) {
        if (gMap.containsKey(index) && gMap.get(index).containsKey(m)) {
            return gMap.get(index).get(m);
        }
        int max = index + (m << 1);
        if (max >= piles.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = index; i < max; i++) {
            int next = Math.max(m, i - index + 1);
            ans = Math.min(ans, before02(piles, i + 1, next, fMap, gMap));
        }
        if (!gMap.containsKey(index)) {
            gMap.put(index, new HashMap<>());
        }
        gMap.get(index).put(m, ans);
        return ans;
    }

    public static int stoneGameII03(int[] piles) {
        int n = piles.length;
        Integer[][] fMap = new Integer[n][n + 1];
        Integer[][] gMap = new Integer[n][n + 1];
        // 前n项和
        int[] sums = new int[n];
        sums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + piles[i];
        }
        return before03(piles, 0, 1, fMap, gMap, sums);
    }

    public static int before03(int[] piles, int index, int m,
                               Integer[][] fMap, Integer[][] gMap, int[] sums) {
        if (fMap[index][m] != null) {
            return fMap[index][m];
        }
        int max = index + (m << 1);
        if (max >= piles.length) {
            fMap[index][m] = sums[index];
            return fMap[index][m];
        }
        int sum = 0;
        int ans = 0;
        for (int i = index; i < max; i++) {
            sum += piles[i];
            int next = Math.max(m, i - index + 1);
            ans = Math.max(ans, sum + after03(piles, i + 1, next, fMap, gMap, sums));
        }
        fMap[index][m] = ans;
        return ans;
    }

    public static int after03(int[] piles, int index, int m,
                              Integer[][] fMap, Integer[][] gMap, int[] sums) {
        if (gMap[index][m] != null) {
            return gMap[index][m];
        }
        int max = index + (m << 1);
        if (max >= piles.length) {
            gMap[index][m] = 0;
            return gMap[index][m];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = index; i < max; i++) {
            int next = Math.max(m, i - index + 1);
            ans = Math.min(ans, before03(piles, i + 1, next, fMap, gMap, sums));
        }
        gMap[index][m] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(stoneGameII03(new int[]{2, 7, 9, 4, 4}));
        System.out.println(stoneGameII03(new int[]{1, 2, 3, 4, 5, 100}));
//        System.out.println(stoneGameII02(new int[]{461, 8775, 9312, 2634, 8339, 1821, 5692, 1511, 5467, 7849, 7480, 4166, 8152, 7438, 4340, 3900, 8421, 9162, 879, 3298, 5723, 8381, 10000, 4883, 377, 6077, 2356, 3903, 1450, 2666, 9688, 6507, 8558, 4507, 8480, 1760, 5948, 2322, 1213, 7889, 1069, 4082, 7724, 4922, 5864, 3242, 6007, 549, 8122, 7933, 5805, 8921, 4228, 2738, 6349, 4078, 7378, 780, 1099, 6710, 4961, 6559, 4384, 740, 5728, 2300, 5875, 3729, 2372, 7178, 3575, 9158, 5611, 8160, 8760, 1101, 874, 6863, 9363, 1995, 8033, 7837, 3329, 681, 9166, 2570, 3023, 2680, 9321, 4344, 2354, 327, 6497, 8133, 2882, 1754, 6733, 6848, 1611, 2606, 6726, 6247, 2708, 5738, 5713, 5607, 1056, 8656, 4525, 5651, 8819, 157, 538, 1150, 9525, 122, 5109, 340, 5734, 44, 3819, 2118, 9526, 9066, 2788, 1939, 3806, 2525, 3846, 4270, 5982, 6297, 2115, 5807, 532, 3126, 5486, 5394, 8037, 5215, 5309, 668, 8396, 1506, 7910, 2051, 1358, 1103, 6685, 3403, 489, 1109, 2606, 3348, 9798, 3128, 3244, 1076, 3485, 6734, 1162, 7501, 6578, 8106, 1172, 9915, 7878, 993, 9, 8786, 6849, 4282, 7998, 7099, 6158, 1422, 2554, 5694, 1121, 4166, 6812, 1396, 7496, 9221, 1698, 9924, 5399, 926, 835, 5360, 8042, 4130, 4009, 4926, 1301, 5607, 3177, 4189, 7666, 3931, 6263, 9945, 3503, 1680, 441, 9420, 7988, 5533, 9572, 4321, 9403, 6933, 6229, 9861, 7293, 3161, 3245, 2773, 2217, 1656, 3122, 668, 6610, 6859, 2507, 3758, 3059, 8867, 6674, 4181, 3289, 6089, 5179, 8164, 1926, 7399, 850, 7065, 2735, 2168, 432, 7443, 1216, 332, 6466, 5132, 2612, 7380, 4187, 7828, 7619, 7491, 8449, 5284, 1032, 670, 9334, 129, 9970, 9724, 8432, 611, 2480, 4814, 1639, 972, 3143, 4747, 1055, 9999, 8159, 8447, 440, 7677, 4307, 56, 9677, 1325, 6210, 6863, 923, 1421, 8203, 2435, 7393, 6558, 8186, 60, 1721, 5366, 7411, 5945, 2398, 7989, 7721, 3215, 7598, 781, 8721, 9901, 2175, 9038, 4050, 8497, 8003, 6428, 4873, 4984, 2610, 5770, 8373, 4648, 1152, 3545, 4363, 5259, 777, 6876, 1595, 1420, 1303, 9787, 8427, 4085, 8133, 5674, 7559, 3796, 4157, 9347, 6276, 5719, 3193, 2678, 2671, 8831, 8037, 863, 3961, 2270, 9965, 2441, 5448, 7599, 6836, 10, 3488, 9342, 8128, 4629, 4151, 5514, 7942, 3864, 1653, 1842, 9814, 2581, 284, 7751, 2819, 5159, 388, 5184, 4301, 6629, 1866, 6232, 7148, 9513, 5405, 792, 4606, 3414, 2907, 8439, 6752, 6888, 6146, 5425, 3861, 3545, 884, 3487, 7617, 6634, 2112, 9725, 690, 8149, 4301, 302, 9926, 8249, 766, 7653, 6697, 4849, 7044, 7952, 4536, 9306, 2568, 1783, 8275, 7113, 5488, 1023, 2689, 663, 2094, 2068, 7691, 8579, 8484, 4761, 7534, 6299, 8096, 3698, 2154, 5147, 9482, 7626, 327, 6056, 2290, 8156, 7287, 7810, 5440, 1923, 3588, 2009, 2121, 1973, 6877, 5185, 4990, 7921, 1980, 5845, 8200, 1668, 3317, 2019, 5696, 5199, 1115, 1376, 814, 3714, 53, 5058, 9994, 2488, 5800, 1059, 5358, 4413, 2250, 4296, 1917, 1571, 2403, 2051, 701, 751, 1990, 6663, 9609, 724, 2824, 3413, 8030, 2876, 5899, 4424, 1547, 7214, 7036, 8713, 6183, 306, 4335, 7923, 9676, 4674, 6370, 2016, 1794, 8980, 3875, 3405, 3187, 3184, 4158, 63, 8476, 2489, 7248, 5846, 4419, 1422, 3347, 8533, 3123, 2019, 3549, 685, 150, 9762, 1056, 6084, 4498, 6185, 3139, 5452, 1380, 3974, 6630, 2767, 9992, 8775, 6055, 1769, 3397, 5294, 6863, 9525, 5716, 8962, 2276, 8553, 6055, 4841, 7311, 690, 308, 8845, 4536, 2301, 9161, 972, 8273, 8646, 1673, 6967, 9045, 9538, 190, 4119, 9068, 6359, 9062, 6429, 9572, 3401, 2775, 9356, 4720, 7330, 3022, 5438, 9094, 268, 2072, 8566, 6031, 6516, 5018, 2599, 3996, 4379, 4030, 6208, 9094, 5593, 7991, 5156, 1272, 7422, 8963, 2271, 9740, 6225, 2454, 4606, 4644, 2238, 7175, 5985, 2922, 3783, 6643, 9060, 2904, 166, 8946, 4114, 2527, 2210, 1315, 4536, 2799, 6502, 8160, 4721, 6904, 6653, 7260, 6704, 4274, 5460, 5421, 801, 1189, 4679, 749, 5243, 6455, 6969, 3421, 381, 20, 1013, 4768, 5314, 3525, 9339, 3951, 4883, 8215, 5289, 5680, 1697}));
    }
}
