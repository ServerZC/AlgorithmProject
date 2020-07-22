package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 * 题目：旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class MinInArray implements Testable {
    @Override
    public void test() {
        int[] array = {3,4,5,1,2};
        System.out.println(min(array));
    }

    /**
     * 可改进：若使用二分法效率更高
     * @param array
     * @return
     */
    public int min(int[] array){
        int index = 1;
        while (index < array.length){
            if (array[index] >= array[index-1]){
                index ++;
                continue;
            }
            return array[index];
        }
        return array[0];
    }
}
