package com.question.day02;

/**
 * @author zms
 * @date 11:34 下午 2021/9/17
 */
public class Code02Cola {

    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */
    public static int putTimes(int m, int a, int b, int c, int x) {
        if (m == 0) {
            return 0;
        }
        int[] rests = {100, 50, 10};
        int[] zhang = {c, b, a};
        int ans = 0;
        int preRemainingPrice = 0;
        int preRemainingZhang = 0;
        for (int i = 0; i < rests.length && m != 0; i++) {
            // 计算之前剩余的价值 需要添加几张当前的面额才能购买一瓶可乐
            // 由于不能购买到一瓶才会剩余
            int curQianFirstBuyZhang = (x - preRemainingPrice + rests[i] - 1) / rests[i];
            if (curQianFirstBuyZhang > zhang[i]) {
                preRemainingZhang += zhang[i];
                preRemainingPrice += rests[i] * zhang[i];
                continue;
            }
            // 购买第一瓶可乐找零
            int remainingPrice = curQianFirstBuyZhang * rests[i] - (x - preRemainingPrice);
            // 将找零的钱分配给余下的面额
            giveRest(rests, zhang, i, remainingPrice, 1);
            // 之前剩余的张数 + 使用当前面额的张数
            ans += preRemainingZhang + curQianFirstBuyZhang;
            zhang[i] -= curQianFirstBuyZhang;
            // 表示剩余可乐数
            m--;
            // 当前购买面额购买可乐，需要几张
            // 向上取整
            int curQianBuyZhang = (x + rests[i] - 1) / rests[i];
            // 当前可乐可以购买的可乐数
            int num = Math.min(zhang[i] / curQianBuyZhang, m);
            // 表示剩余可乐数
            m -= num;
            // 使用张数
            int used = num * curQianBuyZhang;
            ans += used;
            zhang[i] -= used;
            remainingPrice = curQianBuyZhang * rests[i] - x;
            // 将找零的钱分配给余下的面额
            giveRest(rests, zhang, i, remainingPrice, num);
            preRemainingZhang = zhang[i];
            preRemainingPrice = rests[i] * zhang[i];
        }
        return m == 0 ? ans : -1;
    }

    /**
     * 找零遵循大钱原则分给剩余的面额
     *
     * @param rests          面额数组
     * @param zhang          每种面额的张数
     * @param index          消耗的面额索引
     * @param remainingPrice 每瓶可乐找零的价值
     * @param times          总共买了多少瓶可乐
     */
    public static void giveRest(int[] rests, int[] zhang, int index, int remainingPrice, int times) {
        // 分配单瓶可乐的找零 每个面额 * 可乐数
        for (int i = index + 1; i < rests.length; i++) {
            int count = remainingPrice / rests[i];
            zhang[i] += count * times;
            remainingPrice = remainingPrice % rests[i];
        }
    }
}
