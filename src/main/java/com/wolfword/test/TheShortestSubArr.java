package com.wolfword.test;

/**
 * 题目：长度最小的子数组
 * 定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class TheShortestSubArr {

    public static void main(String[] args) {
        int[] arrays = {1,2,3,4,5};
        int number = 15;
        System.out.println(shortestLen(arrays,number));
        System.out.println(doubleIndex(arrays,number));
    }

    private static  int shortestLen(int[] arrays, int number){
        if (arrays == null || arrays.length == 0) return 0;
        int resutl = 0;
        boolean init = false;//出现满足条件的结果后置为true
        int sum = 0;//当sum值已经大于number以后，该值的累加已经没有意义了，因为累加后肯定也满足条件
        for (int i = 0; i < arrays.length; i++) {
            //1、判断当前元素是否大于指定正整数
            if (arrays[i] >= number) return 1;
            //2、累加和值
            sum += arrays[i];
            if (!init){//之前还未出现过满足条件的子数组，此值需要累加
                resutl ++;
            }
            if (sum >= number){//当前累加的长度减1，反向循环判断是否存在更短且满足条件的子数组
                if (!init) init = true;
                int tempLen = 1;
                int tempSum = arrays[i];
                for (int j = i-1; j > i-resutl; j--) {
                    tempSum += arrays[j];
                    tempLen ++;
                    if (tempSum >= number){
                        resutl = tempLen;
                        break;
                    }
                }
            }
        }
        if (init) return  resutl;
        else return  0;
    }

    private static int doubleIndex(int[] arrays, int number){
        if (arrays == null || arrays.length == 0) return 0;
        int start = 0, end = 0;
        int sum = 0;
        int internal = 0;
        int result = Integer.MAX_VALUE;
        boolean init = false;//出现满足条件的结果后置为true
        while (end < arrays.length){
            sum += arrays[end];
            internal ++;
            if (sum < number){
                end++;
                continue;
            }
            if (!init) init = true;
            if (internal < result) result = internal;

            while (start <= end){
                sum -= arrays[start];
                if (sum >= number){
                    internal --;
                    start ++;
                    if (internal < result) result = internal;
                }else {
                    sum += arrays[start];//还原减掉的当前首位
                    end ++;
                    break;
                }
            }
        }
        if (init) return  result;
        else return  0;
    }
}
