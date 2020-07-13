package com.wolfword.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 题目：数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class BiggerNumber {

    public static void main(String[] args) {
        int[] numbers = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(getBiggerNum(numbers,k));
    }

    private static int getBiggerNum(int[] numbers, int k){
        if (numbers == null || numbers.length == 0
            || k < 1 || k > numbers.length ) return Integer.MIN_VALUE;


//        return wildlyFind(numbers,k);
//        return bySort(numbers,k);
        return likeFastSort(numbers,k,0,numbers.length-1);
    }


    /**
     * 先排序再获取，最简单的实现方式
     * @param numbers
     * @param k
     * @return
     */
    private static int bySort(int[] numbers,int k){
        // 先排序
        Arrays.sort(numbers);
        return numbers[numbers.length-k];
    }

    /**
     * 粗暴解决方案，不建议
     * @param numbers
     * @param k
     * @return
     */
    private static int wildlyFind(int[] numbers,int k){
        int max = 0;
        for (int i = 0; i < k; i++) {
            max = numbers[i];
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]){
                    max = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = max;
                }
            }

        }
        return max;
    }


    /**
     * 像快速排序一样来寻找
     *
     */
    private static  int likeFastSort(int[] numbers,int k ,int left, int right){
        int midValue = numbers[left];
        int front = left,rear = right;
        while (front < rear){
            while (numbers[rear] <= midValue && front < rear) rear --;
            numbers[front] = numbers[rear];
            while (numbers[front] >= midValue && front < rear) front++;
            numbers[rear] = numbers[front];
        }
        //最终front==rear
        numbers[front] = midValue;
        if (front > k-1) return likeFastSort(numbers, k, left, front);
        else if(front < k-1) return likeFastSort(numbers, k, front+1, right);
        else  return midValue;
    }

}
