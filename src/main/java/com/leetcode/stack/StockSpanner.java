package com.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zms
 * @date 3:30 下午 2020/5/19
 * <a href="https://leetcode.com/problems/online-stock-span/">
 * Online Stock Span</a>
 *
 * Write a class StockSpanner which collects daily price quotes for some stock,
 * and returns the span of that stock's price for the current day.
 * The span of the stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85],
 * then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 *
 * Example 1:
 * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * Output: [null,1,1,1,2,1,4,6]
 * Explanation:
 * First, S = StockSpanner() is initialized.  Then:
 * S.next(100) is called and returns 1,
 * S.next(80) is called and returns 1,
 * S.next(60) is called and returns 1,
 * S.next(70) is called and returns 2,
 * S.next(60) is called and returns 1,
 * S.next(75) is called and returns 4,
 * S.next(85) is called and returns 6.
 *
 * Note that (for example) S.next(75) returned 4, because the last 4 prices
 * (including today's price of 75) were less than or equal to today's price.
 *
 *
 * Note:
 * Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 * There will be at most 10000 calls to StockSpanner.next per test case.
 * There will be at most 150000 calls to StockSpanner.next across all test cases.
 * The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */
public class StockSpanner {

    List<Integer> prices = new ArrayList<>();
    List<Integer> indexs = new ArrayList<>();
    public StockSpanner() {

    }

    public int next(int price) {
        if(prices.isEmpty()) {
            prices.add(price);
            indexs.add(0);
            return 1;
        }
        int size = prices.size();
        if(price < prices.get(size - 1)) {
            prices.add(price);
            indexs.add(size);
            return 1;
        }
        System.out.println(indexs);
        int index = indexs.get(size - 1);
        while(index >= 0 && prices.get(index) <= price) {
            int next = indexs.get(index);
            if(index == next) {
                index--;
            } else {
                index = next;
            }
        }
        prices.add(price);
        indexs.add(index + 1);

//        for (int i = index; i >= 0; i--) {
//            int before = prices.get(i);
//            if(before <= price) {
//                if(i == 0) {
//                    indexs.add(i);
//                }
//
//            } else {
//                indexs.add(i);
//                break;
//            }
//        }


        return size - index;
    }

    public static void main(String[] args) {
        StockSpanner ss = new StockSpanner();
        System.out.println(ss.next(100));
        System.out.println(ss.next(80));
        System.out.println(ss.next(60));
        System.out.println(ss.next(70));
        System.out.println(ss.next(60));
        System.out.println(ss.next(75));
        System.out.println(ss.next(85));
        System.out.println(ss.next(100));
        System.out.println(ss.next(8));
        System.out.println(ss.next(6));
        System.out.println(ss.next(70));
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
