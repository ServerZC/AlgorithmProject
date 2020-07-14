package cn.wolfshadow.test.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 7、题目：和可被K整除的子数组
 * 给定一个整数数组A，返回其中元素之和可被K整除的（连续、非空）子数组的数目。
 * 示例：
 * 输入：A＝[4,5,0,-2,-3,1]， K＝5
 * 输出：7
 */
public class ExactDivision {

    public static void main(String[] args) {
        int[] arrays = {4,5,0,-2,-3,1};
        int divisor = 5;
        System.out.println(fairilyProcess(arrays,divisor));
    }

    /**
     * 粗暴方案：2重遍历
     * @param arrays
     * @param divisor
     * @return
     */
    private  static int wildlyProcess(int[] arrays, int divisor){
        int result = 0;
        if (arrays == null || arrays.length == 0 || divisor == 0) return result;
        for (int i = 0; i < arrays.length; i++) {
            int sum = arrays[i];
            if (sum % divisor == 0) result ++;
            for (int j = i+1; j < arrays.length; j++) {
                sum += arrays[j];
                if (sum % divisor != 0) continue;
                result ++;
            }
        }
        return  result;
    }

    /**
     * 优雅的解决方案：学习网友
     * 核心思想：从第一个元素开始，求和并以和与除数的余数为key保存到Map中，默认value为1
     * 当出现某个和与除数的余数已经存在于Map中时，说明这个和值对应的子集与Map中已经记录过的子集的差集mod值为0，即可被余数整除
     * 解法十分巧妙，只需要遍历一次，时间复杂度接近 O（n）
     * @param arrays
     * @param divisor
     * @return
     */
    private  static int fairilyProcess(int[] arrays, int divisor){
        int result = 0;
        if (arrays == null || arrays.length == 0 || divisor == 0) return result;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);//初始为0的意义用于第一次出现余数为的0的情况
        for (int i = 0; i < arrays.length; i++) {
            sum += arrays[i];
            int mod = sum % divisor;
            int temp = 0;
            if (map.containsKey(mod)){
                temp = map.get(mod);
                result += temp;
            }
            map.put(mod,temp+1);
        }
        return  result;
    }

    private static int subarraysDivByK(int[] a, int k) {
        if (a == null || a.length == 0 || k == 0) return 0;
        Map<Integer,Integer> map=new HashMap<>();
        int count=0;
        int sum=0;
        map.put(0,1);
        for(int i=0;i<a.length;i++) {
            sum+=a[i];
            int mod=sum%k;
            if(mod<0)
                mod+=k;//!!!
            if(map.containsKey(mod)) {
                count+=map.get(mod);
                map.put(mod, map.get(mod)+1);
            }else {
                map.put(mod,1);
            }
        }
        return count;
    }

}
