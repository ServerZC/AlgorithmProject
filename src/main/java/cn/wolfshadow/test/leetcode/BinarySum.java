package cn.wolfshadow.test.leetcode;

/**
 * 题目：二进制之和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class BinarySum {
    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(sum(a,b));
    }

    private static String sum(String a, String b){
        if (a == null || b==null ) return null;
        //因为最长10000位，只能从字符串层面进行处理
        int aLen = a.length();
        int bLen = b.length();
        if ((a.startsWith("0") && aLen > 1)
            || (b.startsWith("0") && bLen > 1)){
            return  null;
        }
        if (aLen > bLen){
            return sumWithLength(a,b);
        }else {
            return sumWithLength(b,a);
        }

    }
    private static String sumWithLength(String longStr, String shortStr){
        int longLen = longStr.length();
        int shortLen = shortStr.length();
        char a,b;
        StringBuffer sb = new StringBuffer();
        boolean carry = false;//进位
        int j = shortLen - 1;
        for (int i= longLen-1; i>=0; i--){
            a = longStr.charAt(i);
            if (j >= 0){
                b = shortStr.charAt(j--);
            }else {
                b = '0';
            }
            if (a == '0' && b == '0'){
                if (carry) sb.append("1");
                else sb.append("0");
                carry = false;
            }else if ((a == '0' && b == '1') || (a == '1' && b == '0')){
                if (carry) {
                    sb.append("0");
                }else {
                    sb.append("1");
                    carry = false;
                }
            }else if(a == '1' && b == '1'){
                if (carry) sb.append("1");
                else sb.append("0");
                carry = true;
            }else {
                return null;
            }
        }
        if (carry) sb.append("1");
        return sb.reverse().toString();
    }
}
