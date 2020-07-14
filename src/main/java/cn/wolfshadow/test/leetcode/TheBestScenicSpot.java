package cn.wolfshadow.test.leetcode;

/**
 * 8、题目： 最佳观光组合
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 * 示例：
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 */
public class TheBestScenicSpot {

    public static void main(String[] args) {
        int[] arrays = {8,1,5,2,6,4,6,9,12,100};
        System.out.println("粗暴解决方案： "+wildlyProcess(arrays));
        System.out.println("优雅解决方案： "+fairilyProcess(arrays));

    }

    /**
     * 粗暴解决方案：2重遍历，得到最大值
     * @param input
     * @return
     */
    private static int wildlyProcess(int[] input){
        int result = 0;
        if (input == null || input.length == 0) return result;
        int temp = 0;
        for (int i = 0; i < input.length-1; i++) {
            for (int j = i+1; j < input.length; j++) {
                temp = input[i]+input[j]+i-j;
                if (temp > result) result = temp;
            }
        }
        return  result;
    }

    /**
     * 优雅的解决方案：遍历一次，过程中记录input[i]+i及input[j]-j，同时记录它们的和值最大值
     * @param input
     * @return
     */
    private static int fairilyProcess(int[] input){
        int result = 0;
        if (input == null || input.length == 0) return result;
        int maxI = input[0];//用于记录input[i]+i的最大值
        result = maxI;//记录和值的最大值
        for (int i = 1; i < input.length; i++) {
            int tempJ = input[i] - i;
            int tempI = input[i] + i;
            int sum = maxI + tempJ;
            if (sum > result) result = sum;
            if (tempI > maxI) maxI = tempI;
        }
        return  result;
    }


}
