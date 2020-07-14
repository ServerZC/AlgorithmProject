package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class MinPathSum implements Testable {

    @Override
    public void test() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);

        /*int[][] triangle = {
                {2},
                {3,4},
                {6,5,7,},
                {4,1,8,3}};*/
        System.out.println(minPath(triangle));

    }

    private int minPath(List<List<Integer>> triangle){
        if (triangle == null) return 0;
        int size = triangle.size();
        if (size == 0) return 0;
        else if (size == 1) return triangle.get(0).get(0);
        else{
            //return withRecursion(triangle,0,0);
            return withDynamicPlanning(triangle);
        }
    }

    /**
     * 方法一：迭代实现深度遍历
     * 方法正确，但数据较大时耗时较长
     * @param triangle
     * @param row
     * @param column
     * @return
     */
    private int withRecursion(List<List<Integer>> triangle, int row, int column){
        int hight = triangle.size();
        int width = hight;//最后一行的元素个数
        if (row == hight-2) return triangle.get(row).get(column)+Math.min(triangle.get(row+1).get(column),triangle.get(row+1).get(column+1));
        else {
            int left = withRecursion(triangle, row+1, column);
            int right = withRecursion(triangle, row + 1, column + 1);
            return triangle.get(row).get(column)+Math.min(left,right);
        }
    }

    /**
     * 方法二：动态规划
     * 计算分别到达最后一排每个节点的最短路径
     * @param triangle
     * @return
     */
    private int withDynamicPlanning(List<List<Integer>> triangle){
        int size = triangle.size();
        int [] paths = new int[size];
        paths[0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            int[] temp = new int[i+1];
            temp[0] = paths [0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                temp[j] = triangle.get(i).get(j) + Math.min(paths[j-1],paths[j]);
            }
            temp[i] = paths[i-1] + triangle.get(i).get(i);
            for (int j = 0; j < i+1; j++) {
                paths[j] = temp[j];
            }
        }
        int min = paths[0];
        for (int i = 1; i < size; i++) {
            if (paths[i] < min) min = paths[i];
        }

        return min;
    }


}
