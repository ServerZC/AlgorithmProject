package cn.wolfshadow.test.leetcode;

/**
 * 9、题目： 消灭星星
 * 有一个星星队列，每一颗星星上面都有一个分数，我们每次可以挑选一个进行消灭，
 * 每次消灭的得分等于相邻两个星星的分数乘积，总分为每次消灭星星得分之和。
 * 举个例子，星星队列为{2，4，6，2}，那么消灭4，分数为2*6＝12；
 * 队列变为{2，6，2}，消灭6，分数为2*2＝4，得到部分为16。
 * 不能挑选队首跟队尾，一个长度为N的队列，经过N－2轮后游戏结束，求游戏结束时可获得的最大分数值。
 */
public class WipeOutStar {
    public static void main(String[] args) {

    }

    private  static int maxScore(int[] stars){
        if (stars == null || stars.length < 3) return  0;
        return  1;
    }
}
