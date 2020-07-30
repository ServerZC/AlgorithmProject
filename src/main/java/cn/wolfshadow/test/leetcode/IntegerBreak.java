package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 *题目：整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak implements Testable {
    @Override
    public void test() {
        int n = 57;
       // System.out.println(maxProduct(n,true));
        System.out.println(withDp(n));
    }


    /**
     * 递归思想，暴力计算
     * 可行，但提交到力扣平台超出时间限制
     * @param n
     * @param start
     * @return
     */
    private int maxProduct(int n,boolean start) {
        if (n < 2 || n > 58) return 1;
        if (n == 2) return start ? 1 : 2;
        int max = 0;
        int endMax = n;
        if (start) endMax = n-1;
        for (int i = 1; i <= endMax; i++) {
            if (i==n) max = Math.max(max,i);
            else {
                int temp = i * maxProduct(n - i,false);
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    private int withDp(int n){
        if (n < 2 || n > 58) return 0;
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 0;
        f[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                int temp1 = j * (i - j);
                int temp2 = j * f[i-j];
                max = Math.max(max,Math.max(temp1,temp2));
            }
            f[i] = max;
        }

        return f[n];
    }
}
