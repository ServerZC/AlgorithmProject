package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

import java.util.Arrays;

/**
 * 题目：最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum2 implements Testable {
    @Override
    public void test() {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(fastSearch(grid));
    }

    private int minPath(int[][] grid) {
        if(grid == null) return 0;
        int m = grid.length;
        if (m < 1) return 0;
        int n = grid[0].length;
     
        return withRecurse(grid,m,n);
    }

    /**
     * 使用迭代实现
     * 未通过力扣提交，数据较大时超出时间限制
     * @param grid
     * @param m
     * @param n
     * @return
     */
    private int withRecurse(int[][] grid,int m, int n) {
        if (m == 1) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += grid[0][i];
            }
            return sum;
        }else{
            if (n == 1){
                int sum = 0;
                for (int i = 0; i < m; i++) {
                    sum += grid[i][0];
                }
                return sum;
            }
        }
        int lastRow = withRecurse(grid, m - 1, n);
        int lastLine = withRecurse(grid, m, n - 1);
        return grid[m-1][n-1] + Math.min(lastRow,lastLine);
    }

    /**
     * 动态规划：
     * 1、设计长度为n的数组，初始值为起点通过第一行到达各元素的路径和
     * 2、遍历行，除第一元素仅有一种路径外，其他元素都有2种路径，计算2种路径最小值加上当前值即为起点到达当前节点的最短路径
     * 3、遍历完成后可得到起点到最后一行所有元素的最短路径
     * @param grid
     * @return
     */
    private int fastSearch(int[][] grid){
        if(grid == null) return 0;
        int m = grid.length;
        if (m < 1) return 0;
        int n = grid[0].length;
        int [] mins = new int[n];//
        mins[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            mins[i] = grid[0][i] + mins[i-1];
        }
        for (int i = 1; i < m; i++) {
            mins[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                mins[j] = grid[i][j] + Math.min(mins[j-1],mins[j]);
            }
        }
        return mins[n-1];
    }
}
