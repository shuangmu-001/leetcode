package com.leetcode.dynamic.programming;

/**
 * @author wcl
 * @date 3:20 PM 2019/11/29
 */
public class PoorPig {

    /**
     * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water.
     * They all look identical.
     * If a pig drinks the poison it will die within 15 minutes.
     * What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?
     *
     * Answer this question, and write an algorithm for the general case.
     *
     * General case:
     *
     * If there are n buckets and a pig drinking poison will die within m minutes,
     * how many pigs (x) you need to figure out the poisonous bucket within p minutes?
     * There is exactly one bucket with poison.
     *
     * Note:
     *  1、A pig can be allowed to drink simultaneously on as many buckets as one would like,
     *      and the feeding takes no time.
     *  2、After a pig has instantly finished drinking buckets,
     *      there has to be a cool down time of m minutes. During this time,
     *      only observation is allowed and no feedings at all.
     *  3、Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
     *
     *
     * @param buckets 桶的个数
     * @param minutesToDie 单位时间
     * @param minutesToTest 目标时间
     * @return pig count
     */
    private static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {

//        int count = minutesToTest / minutesToDie;
//        double v = Math.log(buckets) / Math.log(2);
//        if(count == 1) {
//            return v % 2.0 == 0 ? (int)v : (int)v + 1;
//        }
//
//        if(buckets <= 2 || count >= buckets - 1) {
//            return 1;
//        }
//
//
//        return 0;

        int pigs = 0;
        int count = minutesToTest / minutesToDie + 1;
        while(Math.pow(count,pigs++)<buckets){
        }
        return pigs-1;

    }


    public static void main(String[] args) {
        System.out.println(poorPigs(1000, 15, 30));
        System.out.println(poorPigs(2000, 5, 120));
        System.out.println(n(4));
        System.out.println(Math.log(1000) / Math.log(4));
        double v = Math.log(5) / Math.log(2);
        System.out.println(v % 2.0 == 0);
        System.out.println(v + 1);

    }

    public static int n(int n) {
        if(n<=1) {
            return 1;
        }
        return n * n(n -1);
    }



}
