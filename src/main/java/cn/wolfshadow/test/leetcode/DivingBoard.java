package cn.wolfshadow.test.leetcode;

import java.util.*;

/**
 * 题目： 跳水板
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
 * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 *
 * 提示：
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 */
public class DivingBoard {
    public static void main(String[] args) {
        int shorter = 1;
        int longer = 2;
        int k = 20;
        int[] result = listMaybeLength(shorter, longer, k);
        System.out.println(Arrays.toString(result));
    }

    private static int[] listMaybeLength(int shorter, int longer, int k){
        if(k == 0) return new int[0];
        if (shorter == longer) return new int[]{shorter * k};
        if (shorter > longer){//如果入参写反了，交换
            shorter += longer;
            longer = shorter - longer;
            shorter -= longer;
        }
        int[] result = new int[k+1];
        for (int i = 0; i <= k; i++) {
            result[i] = longer * i  + shorter * (k-i);
        }

        return result;
    }
}
