package com.wolfword.test;

import java.util.Arrays;
import java.util.Random;

/**
 * 题目要求：
 * 给定一个数字，
 * 按照如下规则翻译成字符串：0翻译成“a”，1翻译成“b”...25翻译成“z”。
 * 一个数字有多种翻译可能，
 * 例如12258一共有5种，分别是bccfi，bwfi，bczi，mcfi，mzi。
 * 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class Number2Str {

    public static final char[] pool = new char[26];

    static {
        for(int i=0; i<26; i++){
            pool[i] = (char)(i + 97);
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(pool));
        int input = 12258;
        int compute = translate(input);
        System.out.println("数值 "+input+" 的翻译可能一共有 "+compute+" 种");
    }


    /**
     * 看到题目，很容易想到使用递归：用f(i)来表示从第i位开始的不同翻译数目，
     * 可以得到有：f(i)=f(i+1)+g(i,i+1)*f(i+2)。
     * i和i+1位数字拼起来在10~25范围内时g(i,i+1)的值为1，否则为0。
     * @param number
     * @return
     */
    private static int translate(int number){
        if (number<0){
            return  0;
        }else {//
            String str = ""+number;
            int length = str.length();
            int[] count = new int[length];
            for (int i = length-1; i >= 0; i--) {
                if (i == length - 1){
                    count[i] = 1;
                }else if (i == length - 2){
                    String sub = str.substring(i);
                    int parseInt = Integer.parseInt(sub);
                    if (parseInt < 26){
                        count[i] = 2;
                    }else{
                        count[i] = 1;
                    }
                }else {
                    String sub = str.substring(i, i + 2);
                    int parseInt = Integer.parseInt(sub);
                    if (parseInt < 26){
                        count[i] = count[i+1] + count[i+2];//加上i+2的对应的可能数，实际相当于乘以2
                    }else{
                        count[i] = count[i+1];
                    }
                }

            }
            return  count[0];
        }

    }

    public static int getTranslationCount(int number) {
        if(number<0)
            return 0;
        String sNumber=String.valueOf(number);
        int len=sNumber.length();
        int[] counts=new int[len];
        for(int i=len-1;i>=0;i--) {
            if(i==len-1) {
                counts[i]=1;
            }else {
                counts[i]=counts[i+1];
                if(canBeTrans(sNumber,i)) {
                    if(i==len-2)
                        counts[i]+=1;
                    else
                        counts[i]+=counts[i+2];
                }
            }
        }
        return counts[0];
    }

    private static boolean canBeTrans(String sNumber, int i) {
        int a=sNumber.charAt(i)-'0';
        int b=sNumber.charAt(i+1)-'0';
        int convert=a*10+b;
        if(convert>=10 && convert<=25)
            return true;
        return false;
    }


    private static int compute(int input){
        if (input<0){
            return  0;
        }else  if (input < 10){//一位数
            return 1;
        }else if (input < 26){//26以内的两位数(去除单个解析的情况)
            return 2;
        }else if (input <100){//其他情况的两位数
            return 1;
        }else {//3位数以上
            String str = input + "";
            int result = 1;//全部单个解析的可能情况
            for (int i = 0,len=str.length(); i < len-2; i++) {
                String left = str.substring(i, i + 2);
                String right = str.substring(i + 2);
                String last = str.substring(i+1);

                if (i == len-2){//右侧无数据了
                    result += compute(Integer.parseInt(left)) - 1;//减掉全部为单个解析的情况
                }else{
                    result += compute(Integer.parseInt(left)) * compute(Integer.parseInt(right)) - 1;//减掉全部为单个解析的情况
                }
            }
            return  result;
        }

    }

    private static int randomNum(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
}
