package cn.wolfshadow.test.leetcode;

import java.util.*;

/**
 * 5、题目：三数之和
 * 给你一个包含N个整数的数组nums，判断nums中是否存在三个元素a，b，c，使得a+b+c=0？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复三元组。
 * 示例：
 * 给定数组nums=[-1,0,1,2,-1,-4]，
 * 满足要求的三元组集合为：
 * [[-1,0,1],[-1,-1,2]]
 */
public class ThreeNumEquals0 {

    public static void main(String[] args) {
        //       int[] numbers = {-1,0,1,2,-1,-4};
        int[] numbers = {-1,0,1,2,-1,-4,-1,2,0,0,2,4,8,-4,-4};
//        wildlyProcess(numbers);
        fairilyProcess(numbers);
    }

    /**
     * 优雅的解决方案：
     * 1、先排序
     * 2、使用双指针
     *
     * @param numbers
     */
    private static void fairilyProcess(int[] numbers){
        Arrays.sort(numbers);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] > 0) break;
            if(i > 0 && numbers[i] == numbers[i-1]) continue;
            int j=i+1,k=numbers.length-1;
            while(j < k){
                int sum = numbers[i]+numbers[j]+numbers[k];
                if (sum == 0){
                    //添加
                    List<Integer> list = new ArrayList<>(3);
                    list.add(numbers[j]);
                    list.add(numbers[i]);
                    list.add(numbers[k]);
                    if (!result.contains(list)){
                        result.add(list);
                    }
                    j++;
                    k--;
                }else if (sum < 0){
                    j++;
                }else {
                    k--;
                }
            }


        }
        //打印
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).toString());
        }
    }

    /**
     * 粗暴解决方案：直接遍历，时间复杂度（n^3）
     */
    private static void wildlyProcess(int[] numbers){
        if (numbers == null || numbers.length == 0) {
            System.out.println("入参不合法！");
            return;
        }
//        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> tempList = new ArrayList<>();
        //三重遍历
        for (int i = 0; i < numbers.length-2; i++) {
            for (int j = i+1; j < numbers.length-1; j++) {
                for (int k = j+1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0){
                        List<Integer> list = new ArrayList<>(3);
                        list.add(numbers[i]);
                        list.add(numbers[j]);
                        list.add(numbers[k]);
                        //排序
                        sort(list);
                        if (tempList.contains(list)) continue;
                        tempList.add(list);
                    }
                }
            }
        }
        //打印
        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(tempList.get(i).toString());
        }

    }

    private static void sort(List<Integer> list){
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

}

