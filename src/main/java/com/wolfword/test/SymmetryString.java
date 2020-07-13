package com.wolfword.test;

/**
 * 题目： 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * ​说明：本题中，我们将空字符串定义为有效的回文串。
 * ​示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * ​输入: "race a car"
 * 输出: false
 */
public class SymmetryString {

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        str = "race a car";
        str = "race4 a!@!#4#E car";
        boolean judge = judge(str);
        System.out.println(str);
        if (!judge) System.out.print("不");
        System.out.println("是回文串。");
    }

    /**
     * 我的思路：头尾指针，排除非字母字符
     * @param input
     * @return
     */
    private static boolean judge(String input){
        if (input == null) return false;
        if (input.trim().length() == 0) return  true;
        int start = 0,end = input.length()-1;
        while (start < end){
            char c1 = input.charAt(start);
            if (!Character.isLetterOrDigit(c1)){
                start ++;
                continue;
            }
            c1 = Character.toUpperCase(c1);
            char c2 = input.charAt(end);
            if (!Character.isLetterOrDigit(c2)){
                end --;
                continue;
            }
            c2 = Character.toUpperCase(c2);

            if (c1 != c2) return  false;
            start ++;
            end -- ;
        }
        return  true;
    }


}
