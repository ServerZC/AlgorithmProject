package com.wolfword.test;

import java.util.Arrays;

/**
 * 题目：最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class LongestCommonStr {

    public static void main(String[] args) {
        int[] numbers1 = {1,2,3,2,1};
        int[] numbers2 = {3,2,1,4,7};
        int[] numbers3 = {0,0,2,0,1};
        System.out.println(moveArray(numbers1,numbers2));
    }

    /**
     * 网友思路：滑动窗口
     *
     */
    private static int moveArray(int[] numbers1, int[] numbers2){
        //1、以第一个数组为参照物，滑动第二个数组
       int result = move(numbers1,numbers2);
       //2、以第二个数组为参照物
       result = Math.max(move(numbers2,numbers1), result);
       return result;
    }
    private static int move(int[] numbers1, int[] numbers2){
        //以第一个数组为参照物，滑动第二个数组
        int overlap = 0;//重叠部分的长度
        int result = 0;
        for (int i = 0; i < numbers1.length; i++) {
            overlap = Math.min(numbers1.length-i, numbers2.length);
            int common = 0;//公共元素个数
            for (int j = 0; j < overlap; j++) {
                if (numbers1[j+i] == numbers2[j]){
                    common ++;
                }else {
                    if (common > 0) result = Math.max(result,common);
                    common = 0;
                }
            }
            if (common > 0) result = Math.max(result,common);
        }
        return  result;
    }


    /**
     * 转换成字符串求子串
     * 问题：当字符串较长时会超时
     * @param numbers1
     * @param numbers2
     * @return
     */
    private static  int lenCommon(int[] numbers1, int[] numbers2){
        if (numbers1 == null || numbers1.length == 0
                || numbers2 == null || numbers2.length == 0) return 0;
        if (numbers1.length >= numbers2.length)
            return lenCommon(numbers1,numbers2,true);
        else
            return lenCommon(numbers2,numbers1,true);
    }

    private static  int lenCommon(int[] numbers1, int[] numbers2, boolean longFirst){
        StringBuffer sb = new StringBuffer("|");
        for(int i : numbers1) sb.append(i).append("|");
        String longer = sb.toString();
        sb = new StringBuffer("|");
        for(int i : numbers2) sb.append(i).append("|");
        String shorter = sb.toString();
        if (longer.contains(shorter)){
            return numbers2.length;
        } else {
            for (int i = 0; i < 2; i++) {
                int subLeft = lenCommon(numbers1,Arrays.copyOfRange(numbers2,0,numbers2.length - 1),true);
                int subRight = lenCommon(numbers1, Arrays.copyOfRange(numbers2,1,numbers2.length),true);
                return subLeft > subRight ? subLeft : subRight;
            }
        }
        return  0;
    }



}
