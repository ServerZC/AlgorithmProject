package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

/**
 * 题目：LCP 13. 寻宝
 * 我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。
 * 迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。
 * 要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一个石头到指定地点。
 * 迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。
 * 我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。
 *
 * 示例 1：
 * 输入： ["S#O", "M..", "M.T"]
 * 输出：16
 * 解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。
 * 示例 2：
 * 输入： ["S#O", "M.#", "M.T"]
 * 输出：-1
 * 解释：我们无法搬到石头触发机关
 * 示例 3：
 * 输入： ["S#O", "M.T", "M.."]
 * 输出：17
 * 解释：注意终点也是可以通行的。
 */
public class MinSteps implements Testable {
    @Override
    public void test() {
        String[] input = {"S#O", "M..", "M.T"};
        System.out.println(minSteps(input));
    }

    /**
     * 分析
     * todo 未解决
     * 第1步：计算从起点S到各个O点的最短步数，记为so1,so2
     * 第2步：分别计算从O点到各M点的最短步数，记为om1,om2...
     * 第3步：分别计算从M点到T点的最短步数，记为mt1,mt2...
     * 第4步：选取(mt-om)最小值作为最后一次的移动；因为搬石头到机关处，除最后一次外都需要1个来回
     * 第5步：累加上述值得到最少步数
     * @param input
     * @return
     */
    private int minSteps(String[] input) {
        if (input == null || input.length == 0) return -1;
        int row = input.length;//行
        int line = input[0].length();//列
        int[] sLoc = new int[2];//记录S点的坐标位置
        int[] tLoc = new int[2];//记录T点的坐标位置

        boolean findS = false;
        boolean findO = false;
        for (int i = 0; i < row; i++) {
            String str = input[i];
            for (int j = 0; j < line; j++) {
                char c = str.charAt(j);
                switch (c){
                    case 'S' :
                        sLoc[0] = i;
                        sLoc[1] = j;
                    case '#' :
                    case 'M' :
                    case 'T' :
                        tLoc[0] = i;
                        tLoc[1] = j;
                }
            }
        }
        return 0;
    }
}
