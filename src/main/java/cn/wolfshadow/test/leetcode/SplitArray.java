package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

import java.util.Arrays;

/**
 * 题目：分割数组的最大值
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */
public class SplitArray implements Testable {
    @Override
    public void test() {
        //int[] nums = {7,2,5,10,8};
        int[] nums = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        int m = 20;
        System.out.println(minSum(nums,m));
    }

    /**
     * 使用递归实现
     * 数据量较大时，超出时间限制
     * @param nums
     * @param m
     * @return
     */
    private int minSum(int[] nums, int m){
        if (nums == null || nums.length == 0) return 0;
        long[] numbers = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = nums[i];
        }
        return (int)minSum(numbers,m,0);
    }
    private long minSum(long[] nums, int m, int index){
        long sum = 0;
        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
        }
        if (m == 1) return sum;
        long avg = sum / m;//大概的均分值
        long temp = 0;
        int i = index;
        for (; i < nums.length; i++) {
            temp += nums[i];
            if (temp < avg) continue;
            break;
        }
        if (i == nums.length) return temp;
        long lastOne = minSum(nums, m - 1, i);
        long thisOne = 0;
        if (i == nums.length - 1){
            thisOne = nums[i];
        }else {
            thisOne = minSum(nums, m - 1, i+1);
        }
        return Math.min(Math.max(temp-nums[i],lastOne),Math.max(temp,thisOne));
    }

}
