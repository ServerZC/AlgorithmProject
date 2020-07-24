package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 * 题目：除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 * 示例 1：
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 * 提示：
 * 1 <= N <= 1000
 */
public class DivisorGame implements Testable {
    @Override
    public void test() {
        int input = 2;
        System.out.println();
    }

    /**
     * 分析：只要对方拿到的是奇数，则自己下轮拿到的肯定是偶数（奇数的约数肯定是奇数，奇数-奇数得到偶数）
     * 策略：保证自己拿到的偶数，然后给对方创建奇数，最终自己获胜
     * 结论：输入为偶数则可能获胜
     * 感悟：数学分析，解该题关键
     * @param input
     * @return
     */
    public boolean winning(int input){
        return input%2 == 0;
    }
}
