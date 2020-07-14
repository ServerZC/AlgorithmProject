package cn.wolfshadow.test.leetcode;

/**
 * 3、题目：回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左到右）和倒序都是一样的整数。
 * 示例1：
 * 输入：121
 * 输出：true
 * 示例2：
 * 输入：-121
 * 输出：false
 * 示例3：
 * 输入：10
 * 输出：false
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class SymmetryNumber {

    public static void main(String[] args) {
        int number = 1020120201;
//        boolean judge = judge(number);
        boolean judge = isSymmetryNumber(number);
        System.out.print(number);
        System.out.print(" ");
        if (!judge) System.out.print("不");
        System.out.println("是回文数。");
    }

    /**
     * 我的思路：迭代比较首尾数字，不转为字符串的解法
     * @param number
     * @return
     */
    private static boolean judge(int number){
        if (number < 0) return  false;
        if (number < 10) return  true;
        //1、 判断其位数或者获得其相同位数的最小整数
        int specialNum = getSpecialNum(number);
        int start = number / specialNum;
        int end = number % 10;
        if (start != end) return false;

        //2、去头去尾
        //去头
        int newNumber = number - start * specialNum;
        //去尾
        newNumber /= 10;

        //3、如果此时为0，则不再继续
        if (newNumber == 0) return  true;

        //4、判断新数是否首位为0的情况
        int temp = newNumber * 100;//因为去头去尾导致数值至少变短了2位，因此乘以100用于获取新的头部
        int newStart = temp / specialNum;//获取新头部
        int newEnd = newNumber % 10;//新尾部
        while (newStart == 0){
            //判断此时尾部对称的位置是否是0
            if (newEnd != 0) return false;
            //头尾同时为0的情况，再次去头去尾（实际上只需要去尾）
            newNumber /= 10;
            //继续获取头部，利用temp查找下一个非0头部
            temp *= 10;
            newStart = temp / specialNum;//获取新头部
            newEnd = newNumber % 10;//新尾部
        }

        //5、直到去除掉所有的0首位，然后迭代
        return  judge(newNumber);
    }

    /**
     * 思路：把数据反转后比较是否相等
     * @param number
     * @return
     */
    private  static  boolean isSymmetryNumber(int number){
        if (number < 0) return false;
        if (number < 10) return true;
        int temp = number;
        int compareNum = 0;
        int end = 0;//取最后一位
        while (temp > 0){
            end = temp % 10;//取最后一位
            temp /= 10;//去掉最后一位
            compareNum *= 10;
            compareNum += end;
        }
        return compareNum == number;
    }

    private static int getSpecialNum(int number){
        if (number < 10) {
            //todo 抛出异常
        }
        int result = 10;
        int temp = number / result;
        while (temp >= 10){
            result *= 10;
            temp = number / result;
        }
        return  result;
    }
}
