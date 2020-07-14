package cn.wolfshadow.test.leetcode;

import java.util.*;

/**
 * 6、题目：最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串“”。
 * 示例1：
 * 输入：["flower","flow","flight"]
 * 输出："fl"
 */
public class PublicPrefix {

    public static void main(String[] args) {
        String[] input = {"flower","flow","flowight","flowererere","flowsfasaf"};
        String prefix = searchWithQueue(input);
        System.out.println(prefix);

    }

    /**
     * 我的思路，使用2个队列，循环剔除不能匹配的前缀
     * @param input
     */
    private static String searchWithQueue(String[] input){
        if (input == null || input.length == 0) return "";
        Queue<Character> queue0 = new LinkedList<>();
        Queue<Character> queue1 = new LinkedList<>();
        String result = "";

        int length = input[0].length();
        for (int i = 0; i < length; i++) {//第一个字符串全部放入队列
            queue0.offer(input[0].charAt(i));
        }

        for (int i = 1; i < input.length ; i++) {
            if(i % 2 == 1){
                compare(input[i],queue0,queue1);
                if (i == input.length - 1){//循环完成最后一个字符串的比对
                    result = queue1.toString();
                }
            }else {
                compare(input[i],queue1,queue0);
                if (i == input.length - 1){//循环完成最后一个字符串的比对
                    result = queue0.toString();
                }
            }
        }
        return  result;
    }

    /**
     *
     * @param str
     * @param queue0 拿来同字符串做比较的队列，一般是已数据的队列
     * @param queue1 用来返回最新比较结果的队列，一开始需要清空
     * @return
     */
    private static boolean  compare(String str, Queue<Character> queue0, Queue<Character> queue1){
        queue1.clear();//无数据的队列清空
        for (int j=0; j<str.length(); j++){
            if (queue0.isEmpty()) return false;//如果发现当前队列已空，直接返回false
            Character poll = queue0.poll();
            if (poll.equals(str.charAt(j))){
                queue1.offer(poll);
            }else {//有一个不能匹配则退出循环
                break;
            }
        }
        return  true;
    }

}
