package com.leetcode.greedy;

import com.Utils;

import java.util.*;

/**
 * @author zms
 * @date 3:13 下午 2020/9/21
 * <a href="https://leetcode.com/problems/car-pooling/">Car Pooling</a>
 */
public class CarPooling {
    /**
     * You are driving a vehicle that  has capacity empty seats initially available for passengers.
     * The vehicle only drives east (ie. it cannot turn around and drive west.)
     *
     * Given a list of trips, trip[i] = [num_passengers, start_location, end_location]
     * contains information about the i-th trip:
     * the number of passengers that must be picked up,
     * and the locations to pick them up and drop them off.
     * The locations are given as the number of kilometers  due east from your vehicle's initial location.
     *
     * Return true if and only if  it is possible to pick up and drop off all passengers for all the given trips.  
     *
     * Example 1:
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
     * Output: false
     *
     * Example 2:
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
     * Output: true
     *
     * Example 3:
     * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
     * Output: true
     *
     * Example 4:
     * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
     * Output: true
     *
     * Constraints:
     * 1、trips.length <= 1000
     * 2、trips[i].length == 3
     * 3、1 <= trips[i][0] <= 100
     * 4、0 <= trips[i][1] < trips[i][2] <= 1000
     * 5、1 <=  capacity <= 100000
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        if(trips == null || trips.length == 0) {
            return true;
        }
        int[][] newTrips = new int[trips.length * 2][3];
        for (int i = 0; i < trips.length * 2; i++) {
            int[] trip = i >= trips.length ? trips[i - trips.length] : trips[i];
            if(i < trips.length) {
                newTrips[i] = trip;
            } else {
                newTrips[i][0] = -trip[0];
                newTrips[i][1] = trip[2];
            }
        }
        Arrays.sort(newTrips, (a,b)->{
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });
        Utils.printTwoArrays(newTrips);
        int max = 0;
        for (int[] trip : newTrips) {
            max += trip[0];
            if(max > capacity) {
                return false;
            }
        }
        return true;
    }

    public boolean carPooling1(int[][] trips, int capacity) {
        int[] dp = new int[1002];
        for(int[] t: trips){
            dp[t[1]] += t[0];
            dp[t[2]] -= t[0];
        }
        int curr = 0;
        for(int i = 0; i <= 1000; i++){
            curr += dp[i];
            if(curr > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(carPooling(new int[][]{
                {3,6,9},
                {10,2,3},
                {1,6,8},
                {2,1,6},
                {9,3,9}}, 12));
    }
}
