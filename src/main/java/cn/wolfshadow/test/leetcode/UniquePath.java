package cn.wolfshadow.test.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class UniquePath {

    public static void main(String[] args) {
        /*int[][] input ={
                {0,0,0,0},
                {0,1,0,0},
                {0,0,0,0}};*/
        int[][] input ={{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0},
                {1,0,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,1},
                {0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,1,1,0,0,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0},
                {1,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,1,0,1,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,1,0,1},
                {0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,1,1,0,0,0,1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
                {0,0,1,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0},
                {0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0},
                {0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,0,0,0,1,0,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
                {0,0,0,0,0,1,0,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0},
                {0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
                {0,1,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,0},
                {0,0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,1,1,0,1,0,1,1,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0},
                {0,1,0,0,0,0,0,1,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0}};
        long start = System.currentTimeMillis();
        System.out.println(countPath(input));
        long end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start)+" ms");
    }

    /**
     * 分析：深度优先遍历（DFS depth-first search），类似于树的先序/根遍历，可以采用递归或借助栈实现
     * @param input
     * @return
     */
    private static int countPath(int[][] input){
        if (input == null) return  0;
        int m = input.length;//行
        int n = input[0].length;//列
        if (m == 0 && n == 0) return 0;


        //使用栈实现
        //return withStack(input, m,n);

        //使用递归实现
        //return withRecursion(input,0,0);

        //使用动态规划实现
        return dynamicPlan(input);

    }

    /**
     * 方法一：这里尝试借助栈实现遍历。
     * 结论：逻辑清晰，代码简洁，但数据量较大时耗时较长
     * @param input
     * @param m
     * @param n
     * @return
     */
    private static int withStack(int[][] input, int m ,int n){
        int i=0,j=0;
        int firstValue = input[i][j];
        if (firstValue == 1) return 0;
        int resut = 0;//可能路径的次数

        Deque<Node> stack = new LinkedList<>();
        stack.push(new Node(i,j));
        while (!stack.isEmpty()){
            Node node = stack.pop();
            if (node.x + 1 == n && node.y + 1 == m) {
                resut ++;
                continue;
            }
            if (node.x + 1 < n && input[node.y][node.x+1] == 0){
                stack.push(new Node(node.x+1, node.y));
            }
            if (node.y + 1 < m && input[node.y+1][node.x] == 0){
                stack.push(new Node(node.x, node.y+1));
            }
        }

        return resut;
    }

    /**
     * 方法二：使用递归实现深度优先遍历
     * f(i,j) = f(i+1,j) + f(i,j+1)
     * @param input
     * @return
     */
    private static int withRecursion(int [][] input, int i, int j){
        if (i >= input.length || j >= input[0].length) return 0;
        if (input[i][j] == 1) return  0;
        if (i == input.length-1 && j == input[0].length-1) return 1;
        return withRecursion(input,i,j+1) + withRecursion(input,i+1,j);
    }

    /**
     * 方法三：动态规划（在网友思想上稍做改动，直接在入参input里面修改数值以减少空间复杂度）
     * f(i,j) = f(i-1,j) + f(i,j-1)
     * T(O) = O(m*n)
     * S(O) = O(1)
     * 最佳解决方案
     * @param input
     * @return
     */
    private static int dynamicPlan(int[][] input){
        int m = input.length;//行
        int n = input[0].length;//列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (input[i][j] == 1) input[i][j] = 0;
                else {
                    if (i == 0 && j == 0) input[i][j] = 1;
                    else {
                        if (i > 0) input[i][j] += input[i-1][j];
                        if (j > 0) input[i][j] += input[i][j-1];
                    }
                }
            }
        }
        return input[m-1][n-1];
    }

}
class Node{
    int x;
    int y;
    int value;

    public Node(int x, int y){
        this(x,y,0);
    }

    public  Node(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
