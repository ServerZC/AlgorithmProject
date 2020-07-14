package cn.wolfshadow.test.others;

import cn.wolfshadow.test.Testable;

import java.util.Arrays;

public class ArrayCreator implements Testable {


    public void test() {
        //int n = 5;
        //System.out.println(Arrays.toString(create(n)));
        int[] arrays = {4,11,888,3,4,6,2,65,88,12,3,5,9};
        System.out.println(maxDiff(arrays));
    }

    /**
     * 【未实现】
     * 输入一个int类型的值N，构造一个长度为N的数组arr并返回
     * 要求：对任意i<k<j，都满足arr[i] + arr[j] != arr[k] * 2
     *
     * @param n
     * @return
     */
    public int[] create(int n){
        if (n < 3) return new int[]{};
        int[] array = new int[n];
        array[0] = 1;
        array[1] = 2;
        /*for (int i = 2; i < n; i++) {
            array[i] = array[]
        }*/

        return array;
    }

    /**
     * 有一个长度为n的数组A，求满足0<=a<=b<n的A[b]-A[a]的最大值。
     * 给定数组A及它的大小n，请返回最大差值。
     * 尝试用双指针实现
     * @param arrays
     * @return
     */
    private int maxDiff(int[] arrays){
        if (arrays == null || arrays.length < 2) return 0;
        int length = arrays.length;
        if (length < 2) return 0;
        else if (length == 2) return arrays[1] - arrays[0];
        int i = 0;//指向当前最小值
        int j = 1;//指向当前最大值
        int index = 2;//活动游标
        int max = arrays[1] - arrays[0];
        while (index < length){
            while (index < length && arrays[i] <= arrays[index] && arrays[j] >= arrays[index]){
                index ++;
            }
            if (index == length) return max;//遍历结束
            if (arrays[i] > arrays[index] && index < length-1){//出现更小值（非最后一位）
                i = index;
                j = index + 1;
                max = Math.max(max,arrays[j] - arrays[i]);
            }else if (arrays[j] < arrays[index]){//出现更大值
                j = index;
                max = Math.max(max,arrays[j] - arrays[i]);
            }
            index ++;
        }


        return max;
    }

}
