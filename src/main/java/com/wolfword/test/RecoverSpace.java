package com.wolfword.test;

import java.util.*;

/**
 * 题目： 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 *
 * 提示：
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 */
public class RecoverSpace {
    public static void main(String[] args) {
        String[] dictionary = {"frrrbbrrbfrfqqbbbrb","qr","b","rf","qqbbbbfrqbrrqrffbrqqqbqqfqfrr","r","ffqq","bffbqfqqbrrrf","fq","qfr","fr","rqrrbfbfq","r","f","qbqbrbrbqfqbbbfbbbfbq","bqqbbbqrbbrf","f"};
        String sentence = "bqqffbqbbfqrfrrrbbrrbfrfqqbbbrbfqfffffrfqfqfffffrrfqfrrqbqfrbfrqqrfrbrbbqbqbqqfqrfbfrfr";
        //String[] dictionary = {"jxnonurhhuanyuqahjy","phrxu","hjunypnyhajaaqhxduu"};
        //String sentence = "qahurhoharrdjxnonurhhuanyuqahjyppnha";

        long start = System.currentTimeMillis();
        System.out.println(countUnknowChar(dictionary,sentence));
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
        start = end;
        System.out.println(withBucket(dictionary,sentence));
        end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
        start = end;
        System.out.println(dynamicPlan(dictionary,sentence));
        end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

    /**
     * 解法一：
     * 方法可行，但在力扣平台提交时超出时间限制
     * 说明不是较优解法
     * @param dictionary
     * @param sentence
     * @return
     */
    private static int countUnknowChar(String[] dictionary, String sentence){
        //1、重组字典结构，方便快速查询
        Map<Character, Set<String>> dicMap = new HashMap<>();
        for(String str : dictionary){
            if (str == null || str.trim().equals("")) continue;
            char c = str.charAt(0);
            Set<String> set = dicMap.get(c);
            if (set == null) set = new HashSet<>();
            set.add(str);
            dicMap.put(c, set);
        }

        //2、遍历句子的字符，
        int length = sentence.length();
        int result = 0;
        int index = 0;
        while (index < length){
            int tempL = longestMathLength(sentence, index, dicMap);
            if (tempL == 0){//无匹配
                result ++;
                index ++;
            }else {
                index += tempL;
            }
        }

        return result;
    }
    
    private static int longestMathLength(String sentence, int start, Map<Character,Set<String>> map){
        if (start >= sentence.length()) return 0;
        char c = sentence.charAt(start);
        int tempMax = 0;//记录可匹配的最长串
        if (!map.containsKey(c)) return 0;
        else {
            Set<String> strings = map.get(c);
            Set<String> mathcedSet = new HashSet<>();//匹配上的字符串集合，它们应该全是包含关系
            for(String str : strings){
                if (start + str.length() <= sentence.length()){
                    String subStr = sentence.substring(start, start + str.length());
                    if (str.equals(subStr)) {
                        mathcedSet.add(str);
                    }
                }
            }
            if (!mathcedSet.isEmpty()){//迭代计算，按此字符串匹配，可连续匹配的长度
                for(String str : mathcedSet){
                    int temp = longestMathLength(sentence,start+str.length(),map);
                    temp += str.length();
                    if (temp > tempMax) tempMax = temp;
                }

            }
        }
        return tempMax;
        
    }

    /**
     * 方法二： 借助桶实现
     * 解法正确，但在力扣平台提交代码依然超时
     * 因为句子长度最大了1000，所以最多设计1000个桶
     * @return
     */
    private static int withBucket(String[] dictionary, String sentence){
        List<Set<Integer>> buckets = new ArrayList<>();
        //1、重组字典结构，方便快速查询
        Map<Character, Set<String>> dicMap = new HashMap<>();
        for(String str : dictionary){
            if (str == null || str.trim().equals("")) continue;
            char c = str.charAt(0);
            Set<String> set = dicMap.get(c);
            if (set == null) set = new HashSet<>();
            set.add(str);
            dicMap.put(c, set);
        }

        //2、遍历句子的字符，
        int length = sentence.length();
        int result = 0;
        int index = 0;
        while (index < length){
            char c = sentence.charAt(index);
            if(!dicMap.containsKey(c)){
                buckets.add(null);
                index ++;
                continue;
            }
            Set<String> strings = dicMap.get(c);
            Set<Integer> bucket = new HashSet<>();
            for(String str : strings){
                if (index + str.length() <= sentence.length()){
                    String subStr = sentence.substring(index, index + str.length());
                    if (str.equals(subStr)) {
                        bucket.add(str.length());
                    }
                }
            }
            if (!bucket.isEmpty()) buckets.add(bucket);
            else buckets.add(null);
            index++;
        }

        //3、遍历桶列表，计算各种数值，并得到最小的值
        return minCount(buckets,0);

    }

    private static int minCount(List<Set<Integer>> list, int index){
        int count = 0;
        int result = Integer.MAX_VALUE;
        if (index >= list.size()) return 0;
        while (index < list.size()){
            Set<Integer> set = list.get(index);
            if(set == null){
                count ++;
                index ++;
                if (index >= list.size()) result = count;
                continue;
            }
            for(Integer integer : set){
                int i = minCount(list, index + integer);
                if (i+count < result) result = i+count;
                //if (i == 0) return index = list.size();
            }
            break;
        }
        return  result;
    }

    /**
     * 方法三：Trie + 动态规划（答案之一）
     * 我认为可以近似理解为26叉树，底层用数组实现方便快速查找
     * @param dictionary
     * @param sentence
     * @return
     */
    private static int dynamicPlan(String[] dictionary, String sentence){
        if (dictionary == null || dictionary.length == 0) return  0;
        Trie root = new Trie();//根结点
        for(String str : dictionary){
            root.insert(str);
        }

        int length = sentence.length();
        int[] counter = new int[length+1];
        counter[0] = 0;
        for (int i = 1; i <= length; i++) {
            counter[i] = counter[i-1] + 1;

            Trie node = root;
            for (int j = i; j > 0 ; j--) {
                char c = sentence.charAt(j-1);
                int index = c - 'a';
                if (node.next[index] == null) break;
                else if (node.next[index].isEnd) counter[i] = Math.min(counter[i],counter[j-1]);

                if (counter[i] == 0) break;
                node = node.next[index];
            }

        }


        return counter[length];
    }
}

class Trie{
    Trie[] next;
    boolean isEnd;

    public Trie() {
        next = new Trie[26];
        isEnd = false;
    }

    void insert(String word){
        if (word == null) return;
        Trie node = this;
        for(int i=word.length()-1; i>=0; i--){
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.next[index] == null) node.next[index] = new Trie();
            node = node.next[index];
        }
        node.isEnd = true;
    }
}
