package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

import java.util.Arrays;

/**
 * 题目：两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class SumToTarget implements Testable {

    @Override
    public void test() {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(getIndexs(numbers,target)));
    }

    public int[] getIndexs(int[] numbers, int target){
        int[] indexs = new int[2];
        if (numbers == null || numbers.length < 2) return indexs;
        int length = numbers.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                if (target < numbers[i]+numbers[j]) break;
                if (target < numbers[i]+numbers[j]) break;
                if (numbers[i]+numbers[j] == target){
                    indexs[0] = i+1;
                    indexs[1] = j+1;
                    return indexs;
                }
            }
        }
        return indexs;
    }
    public int[] withRecurse(int[] numbers, int target){
        int[] indexs = new int[2];
        if (numbers == null || numbers.length < 2) return indexs;
        int start = 0;
        int end = numbers.length - 1;
        while (start < end){
            int sum = numbers[start] + numbers[end];
            if (sum > target) end--;
            else if (sum < target) start++;
            else return new int[]{start+1,end+1};
        }

        return indexs;
    }



}
