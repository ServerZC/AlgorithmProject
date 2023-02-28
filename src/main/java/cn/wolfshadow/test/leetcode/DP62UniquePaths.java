package cn.wolfshadow.test.leetcode;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * @author wolfshadow.cn
 * @date 2023/2/28 16:44
 */
public class DP62UniquePaths {

    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(String.format("m=%s, n=%s, result=%s",m,n,uniquePaths2(m,n)));
    }

    /**
     * 方法一：递归
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m == 1) return 1;
        if (n == 1) return 1;
        return uniquePaths(m-1,n)+uniquePaths(m,n-1);
    }
    /**
     * 方法二：备忘录 更佳
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        int[][] num = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0){
                    num[i][j] = 1;
                }else{
                    num[i][j] = num[i-1][j] + num[i][j-1];
                }
            }
        }
        return num[m-1][n-1];
    }
}
