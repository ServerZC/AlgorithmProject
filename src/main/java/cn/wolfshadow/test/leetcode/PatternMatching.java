package cn.wolfshadow.test.leetcode;

/**
 * 题目：面试题——模式匹配
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 * ​
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 */
public class PatternMatching {

    public static void main(String[] args) {
        String pattern = "ab";
        String value = "";
        System.out.println(match(pattern,value));
    }

    private static boolean match(String pattern, String value){
        if (pattern == null || value == null) {
            System.out.println("参数不能为空！");
            return  false;
        }
        String newStr = pattern.replaceAll("[ab]", "");
        if (!newStr.isEmpty()) {
            System.out.println("pattern只能包含a和b！");
            return false;
        }
        //仅三种情况：只包含a，只包含b，同时包含a和b
        if (pattern.contains("a") && pattern.contains("b")){
            if (value.trim().isEmpty()) return false;
            //1、假设a匹配空字符串
            String onlyB = pattern.replaceAll("a","");
            boolean result = sigleMatch(value, onlyB.length());
            if (result) return result;

            //2、假设b匹配空字符串
            String onlyA = pattern.replaceAll("b","");
            result = sigleMatch(value, onlyA.length());
            if (result) return result;

            //3、假设ab均匹配为非空字符串
            //穷举ab可能的长度
            int length = value.length();
            int aCount = pattern.replaceAll("b","").length();
            int bCount = pattern.replaceAll("a","").length();
            int aLen = 1, bLen = 1;//匹配子串的长度
            while (aLen * aCount < value.length()){//小于字符串总长度，而不是等于
                int mod = (length - aLen * aCount) % bCount;
                if (mod == 0){//可以除尽
                    bLen = (length - aLen * aCount) / bCount;
                    result = doubleMatch(pattern,value,aLen,bLen);
                    if (result) return result;
                }
                aLen ++;
            }
            return  false;
        }else if (pattern.contains("a")){//只包含a，只包含b
            return sigleMatch(value,pattern.length());
        }else if (pattern.contains("b")){//只包含a，只包含b
            return sigleMatch(value,pattern.length());
        }else {//不包含a和b
            if (value.trim().isEmpty()) return true;
            return  false;
        }
    }

    private static boolean sigleMatch(String str, int count){
        int length = str.length();
        if (length % count != 0) return false;
        int aLen = length / count;
        String subStr = str.substring(0,aLen);
        str = str.replaceAll(subStr,"");
        return str.equals("");
    }

    private static boolean doubleMatch(String pattern, String value, int aLen, int bLen){
        String aStr = null,bStr = null;
        int index = 0;//value的游标位置
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if('a' == c){
                if (aStr == null) {
                    aStr = value.substring(index,index+aLen);
                }else {
                    if (!aStr.equals(value.substring(index,index+aLen))) return false;
                }
                index += aLen;
            }else{
                if (bStr == null){
                    bStr = value.substring(index,index+bLen);
                }else {
                    if (!bStr.equals(value.substring(index,index+bLen))) return false;
                }
                index += bLen;
            }
        }
        if (aStr.equals(bStr)) return  false;
        return  true;
    }


}
