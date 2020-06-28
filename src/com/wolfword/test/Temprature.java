package com.wolfword.test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 4、题目：每日温度
 * 根据每日气温列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用0来代替。
 * 例如，给定一个列表 temperatures = [73,74,75,71,69,72,76,73]，输出结果 [1,1,4,2,1,1,0,0]
 */
public class Temprature {

    public static void main(String[] args) {
        int[] input = {73,74,75,71,69,72,76,73};
        //input = null;
        int[] output = dailyTemperatures(input);
        System.out.println(Arrays.toString(output));
    }

    /**
     * 粗暴的解决方案：二重循环，平均时间复杂度O（n^2/2）~O（n）
     * @param input
     * @return
     */
    private static  int[] compute(int[] input){
        if (input == null || input.length < 2) return new int[1];
        int[] output = new int[input.length];
        for (int i = 0; i < input.length-1; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] < input[j]) {
                    output[i] = j-i;
                    break;
                }
            }
        }
        return  output;
    }

    /**
     * 解决思路：借助栈来实现
     * 使用栈保持栈顶元素到栈底元素从小到大，当遍历元素e时，若e大于栈顶元素，则弹出栈，并计算天数，否则压入栈。
     * @param temperatures
     * @return
     */
    private static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i =temperatures.length-1;i>=0; i--){
            while (!stack.isEmpty()&&temperatures[i]>=temperatures[stack.peek()])
                stack.pop();
            if(stack.isEmpty())
                res[i] = 0;
            else
                res[i] = stack.peek()-i;
            stack.push(i);
        }
        return res;

    }
}
