package com.wolfword.test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 示例：
 * matrix = [[ 1,  5,  9],
 *           [10, 11, 13],
 *           [12, 13, 15]],
 * k = 8,
 * 返回 13。
 *  
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 
 */
public class KthSmallest {
    public static void main(String[] args) {
        int[][] numbers = {
                {1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 3;
        System.out.println(getNum(numbers,k));
    }

    /**
     * 我的分析：设计n个指针
     * 实际上就是归并排序
     * @param numbers
     * @param k
     * @return
     */
    private static int getNum(int[][] numbers, int k){
        if (k == 1) return  numbers[0][0];
        int n = numbers[0].length;
        int[] indexArray = new int[n];
        int incr = 1;//记录已知排序的个数
        for (int i = 1; i < n; i++) {
            indexArray[i] = -1;
        }
        indexArray[0] = 1;
        indexArray[1] = 0;
        while (indexArray[n-1] < n){
            int[] temp = new int[n];//保存n个指针，每行1个
            int min = 0;//
            int index = 0;
            boolean initMin = false;
            for (int i = 0; i < n; i++) {
                if (indexArray[i] == -1) break;//指针还未初始为0的行不需要比较
                if (indexArray[i] == n) continue;//指针已经指向末尾的行跳过
                if (!initMin) {
                    min = numbers[i][indexArray[i]];//当前值先假设为最小值
                    initMin = true;
                    index = i;
                    continue;
                }
                if (numbers[i][indexArray[i]] < min){//对比各行指针指向的值
                    min = numbers[i][indexArray[i]];
                    index = i;
                }
            }
            //当前行的指针后移
            indexArray[index] ++;
            if (index < n-1 && indexArray[index+1] == -1) indexArray[index+1] = 0;//初始下一行
            incr ++;
            if (incr == k) return min;
        }
        return Integer.MAX_VALUE;
    }

    private static int getKth(int[] input, int k){
        Arrays.sort(input);
        return input[k-1];
    }
}
