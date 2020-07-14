package cn.wolfshadow.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 题目： 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 */
public class Intersection {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5,6,7,8,9,10};
        int[] nums2 = {2,2,3,3,4,5};
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(intersect(nums1,nums2)));
        long end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start)+" ms");
        start = end;
        System.out.println(Arrays.toString(intersectLikeStack(nums1,nums2)));
        end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start)+" ms");

    }


    /**
     * 方案一：借助HashMap
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) return  new int[]{};
        int shortLen = nums1.length;
        int longLen = nums2.length;
        if (shortLen > longLen) return intersect(nums2,nums1);

        HashMap<Integer,Integer> map = new HashMap<>();

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < shortLen; i++) {
            Integer count = map.get(nums1[i]);
            if (count == null) map.put(nums1[i],1);
            else map.put(nums1[i],count+1);
        }
        int index = 0;
        for (int i = 0; i < longLen; i++) {
            Integer temp = map.get(nums2[i]);
            if (temp != null && temp > 0) {
                integers.add(nums2[i]);
                map.put(nums2[i],temp-1);
            }
        }
        int[] result = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            result[i] = integers.get(i);
        }

        return  result;
    }

    /**
     * 方案二：先排序，再使用双指针
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] intersectLikeStack(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) return  new int[]{};
        int len1 = nums1.length;
        int len2 = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> integers = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2){
            if (nums1[i] == nums2[j]){
                integers.add(nums1[i]);
                i++; j++;
            }else if (nums1[i] < nums2[j]){
                i++;
            }else {
                j++;
            }
        }

        int[] result = new int[integers.size()];
        for (int k = 0; k < integers.size(); k++) {
            result[k] = integers.get(k);
        }

        return  result;
    }




}
