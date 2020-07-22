package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 * 题目：不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class BinarySearchTreeCount implements Testable {


    @Override
    public void test() {
        int n = 3;
        //System.out.println(countTrees(n));
        System.out.println(efficientProcess(n));
    }

    /**
     * 分析：动态规划 + 递归
     * 分别以每一个数作为根节点，累加所有的可能
     * @param n
     * @return
     */
    public int countTrees(int n){
        if (n < 1) return 0;
        if (n == 1) return 1;
        //1、第1个数为根结点，其他节点必然在右侧
        int result = countTrees(n-1);
        //2、以中间部分的数为根节点
        for (int i = 1; i < n-1; i++) {
            int left = countTrees(i);//左侧的可能
            int right = countTrees(n-1-i);
            result += left * right;
        }
        //3、最后1个数为根节点，其他节点必然在左侧
        result += countTrees(n-1);

        return result;
    }

    /**
     * 不使用递归，改进版，更高效
     * @param n
     * @return
     */
    public int efficientProcess(int n){
        if (n < 1) return 0;
        if (n == 1) return 1;
        int [] counter = new int[n+1];
        counter[0] = 1;
        counter[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                counter[i] += counter[j-1] * counter[i-j];
            }
        }
        return counter[n];
    }
}
