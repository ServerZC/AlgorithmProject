package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 * 题目：搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class SearchInsertLocation implements Testable {
    @Override
    public void test() {
        int[] arrays = {1,3,5,6};
        int target = 2;
        System.out.println(wildlySearch(arrays,target));
        System.out.println(halfSearch(arrays,target));
        System.out.println(withRecurse(arrays,target,0,arrays.length-1));
    }

    /**
     * 方法一：
     * 粗暴查询，最简单直接
     * @param arrays
     * @param target
     * @return
     */
    public int wildlySearch(int[] arrays, int target){
        if (arrays == null || arrays.length == 0) return 0;
        int index = 0;
        while (index < arrays.length){
            if (target  <= arrays[index]){
                return index;
            }
            index ++;
        }
        return index;

    }

    /**
     * 二分搜索
     * @param arrays
     * @param target
     * @return
     */
    public int halfSearch(int[] arrays, int target){
        if (arrays == null || arrays.length == 0) return 0;
        int start = 0,end = arrays.length-1;
        while (start < end){
            int mid = (end-start)>>1;
            if (target == arrays[mid]) return mid;
            if (target > arrays[mid]){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        if (target == arrays[start]) return start;
        if (target > arrays[start]) return start+1;
        else return start-1;
    }

    /**
     * 二分迭代
     * @param arrays
     * @param target
     * @param start
     * @param end
     * @return
     */
    public int withRecurse(int[] arrays,int target, int start ,int end){
        if (arrays == null || arrays.length == 0) return 0;
        if (target <= arrays[start]) return start;
        if (target > arrays[end]) return end+1;
        if (target == arrays[end]) return  end;
        //以下情况为target区间为 (arrays[start],arrays[end])
        if (end - start == 1) return end;

        int mid = (start+end)/2;
        if (target == arrays[mid]) return mid;
        if (target > arrays[mid]) start = mid+1;
        else end = mid - 1;
        return withRecurse(arrays,target,start,end);
    }
}
