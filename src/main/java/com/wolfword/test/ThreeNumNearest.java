package com.wolfword.test;

import java.util.Arrays;

/**
 * 题目：最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeNumNearest {
    public static void main(String[] args) {
        int[] arrays = {1,2,4,8,16,32,64,128};
        int target = 82;
//        System.out.println(wildlySearch(arrays,target));
        System.out.println(nearestSum(arrays,target));
    }

    /**
     * 粗暴的解决方案：三重遍历可以实现
     * @param arrays
     * @param target
     * @return
     */
    private static int wildlySearch(int[] arrays, int target){
        if (arrays == null || arrays.length < 3) return Integer.MIN_VALUE;
        int nearestSum = 0;
        int abs = 0;
        int tempSum = 0;
        int tempAbs = 0;
        int length = arrays.length;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i+1; j < length - 1; j++) {
                for (int k = j+1; k < length; k++) {
                    if (k == 2) {//仅第一次循环会执行
                        nearestSum = arrays[i] + arrays[j] + arrays[k];
                        abs = Math.abs(nearestSum - target);
                    } else {
                        tempSum = arrays[i] + arrays[j] + arrays[k];
                        tempAbs = Math.abs(tempSum - target);
                        if (tempAbs < abs){
                            abs = tempAbs;
                            nearestSum = tempSum;
                        }
                    }
                }
            }
        }
        return  nearestSum;
    }

    /**
     * 头尾指针实现：先排序，再使用头尾指针
     * @param arrays
     * @param target
     * @return
     */
    private static int nearestSum(int[] arrays, int target){
        if (arrays == null || arrays.length < 3) return Integer.MIN_VALUE;
        shellSort(arrays);
        //Arrays.sort(arrays);
        int nearestSum = 0;
        int abs = 0;
        int tempSum = 0;
        int tempAbs = 0;
        for (int i = 0; i < arrays.length - 2; i++) {
//            int firstInterval = 0;//记录前一次计算出的绝对值间隔
//            int secondInterval = 0;//记录后一次计算出的绝对值间隔
            int front=i+1,rear=arrays.length - 1;
            if(i == 0){//仅在第一次循环时执行此段
                nearestSum = arrays[i] + arrays[front] + arrays[rear];
                abs = Math.abs(nearestSum - target);
                //第一次需要比较前指针后移和后指针前移来决定怎么操作
                if(rear - front > 1){
                    int temp1 = arrays[i] + arrays[front+1] + arrays[rear];
                    int temp2 = arrays[i] + arrays[front] + arrays[rear-1];
                    int abs1 = Math.abs(temp1 - target);
                    int abs2 = Math.abs(temp2 - target);
                    if (abs1 > abs2){
                        rear --;
                    }else{
                        front ++;
                    }
                }else {//此情况说明只有3个元素
                    return nearestSum;
                }
            }
            if (abs == 0) return nearestSum;//绝对值等于0最接近，直接返回
            while (front < rear){
                tempSum = arrays[i] + arrays[front] + arrays[rear];
                tempAbs = Math.abs(tempSum - target);
                if (tempSum > target){//大于目标值，后指针前移
                    rear --;
                }else if(tempSum < target){
                    front ++;
                }else {//已经相等了，最接近
                    return tempSum;
                }
                if (tempAbs < abs){//记录绝对值最小的和值及绝对值
                    abs = tempAbs;
                    nearestSum = tempSum;
                }
            }
        }
        return  nearestSum;
    }

    private static void shellSort(int[] arrays){
        int n = arrays.length / 2;
        while(n >= 1){
            for (int i = n; i < arrays.length; i++) {
                int temp = arrays[i];
                int index = i;
                for (int j = i - n; j >= 0 ; j -= n) {
                    if (temp < arrays[j]){
                        arrays[index] = arrays[j];
                        index = j;
                    }
                }
                arrays[index] = temp;
            }
            n /= 2;
        }
    }
}
