package com.wolfword.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 题目：最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度
 * 要求算法的时间复杂度为O（n）
 * 示例：
 * 输入：[100，4，432，1，3，2]
 *
 * 输出：4
 * 解释：最长连续序列是[1，2，3，4]，长度为4
 */
public class LongestSeq {
    private static  final int DEFAULT_MAX_VALUE = 100;

    public static void main(String[] args){
//        int[] radomSeq = radomSeq(5, 10);
        int[] radomSeq = {1,100,4,432,3,2,0,5};
        System.out.println(Arrays.toString(radomSeq));
        int compute = compute(radomSeq);
        System.out.println("最长连续序列: "+compute);
    }

    /**
     * 不能解决出现重复数字的情况
     * @param input
     * @return
     * @author ServerZhang
     * @date 2020年6月8日
     */
    private static int compute(int[] input){
        int max = 0;
        if (input == null || input.length == 0){
            return  max;
        }
        max = 1;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        Integer temp = null;
        for(int i : input){
            int sum = 0;
            Integer value = map.getOrDefault(i, 0);
            Integer frontValue = map.getOrDefault(i - 1, 0);
            Integer rearValue = map.getOrDefault(i + 1, 0);

            if (value == 0){//无相同值的节点
                value = 1;
                sum = value+frontValue+rearValue;
                map.put(i,sum);
                if (frontValue != 0){
                    map.put(i-frontValue, sum);//这里为什么是i-frontValue，因为直接忽略掉中间的数值
                }
                if (rearValue != 0){
                    map.put(i+rearValue, sum);//同上
                }
                max = max>sum ? max : sum;
            }else {//有相同值的节点，说明已经处理过
            	//相同节点
            	
                /*map.put(i,value+1);
                if (frontValue != 0){
                    map.put(i-1, frontValue+1);//这里是i-1
                }
                if (rearValue != 0){
                    map.put(i+1, rearValue+1);
                }
                max += 1;*/
            }


        }
        return  max;

    }

    /**
     * 解决有重复值的序列，暂未做出来
     * TODO
     * 
     * @param input
     * @return
     * @author ServerZhang
     * @date 2020年6月8日
     */
    private static int bestCompute(int[] input){
        int max = 0;
        if (input == null || input.length == 0){
            return  max;
        }
        max = 1;
        Map<Integer,SeqNode> map = new HashMap<Integer,SeqNode>();
        Integer temp = null;
        for(int i : input){
            int sum = 0;
            SeqNode seqNode = map.get(i);
            SeqNode leftNode = map.get(i-1);
            SeqNode rightNode = map.get(i+1);
            if (seqNode == null) {//之前未出现过同i相同值的节点
            	seqNode = new SeqNode();
            	int value = 1;
            	sum += value;
            	if (leftNode != null) {
            		leftNode.setRight(leftNode.getRight()+1);//之前没有出现过相同值的节点，因此理论上左节点的右值为0
            		sum += leftNode.getRight();
            		seqNode.setLeft(leftNode.getValue());//这个值是左节点实际出现的次数+左节点的左节点数
				}else {
					seqNode.setLeft(0);
				}
            	if (rightNode != null) {
					rightNode.setLeft(rightNode.getLeft()+1);
					sum += rightNode.getLeft();
					seqNode.setRight(rightNode.getValue());
				}else {
					seqNode.setRight(0);
				}
            	seqNode.setValue(sum);
                map.put(i,seqNode);
                if(leftNode != null){
                	int left = leftNode.getLeft();
                	for(int index=0; i<left; i++){//遍历左节点的所有左节点
                		//TODO 
                	}
                }
			}else{
				//TODO 
			}
            
        }
        return  max;
    }
    
    private static int[] radomSeq(int length){
        return radomSeq(length, DEFAULT_MAX_VALUE);
    }

    private static int[] radomSeq(int length, int maxValue){
        int len = length;
        int[] result = new int[length];
        Random random = new Random();
        for(int i=0; i<length; i++){
            result[i] = random.nextInt(maxValue);
        }
        return  result;
    }

    private static  int max(int ... values){
        int resutl = Integer.MIN_VALUE;
        for(int i : values){
            if (i > resutl){
                resutl = i;
            }
        }
        return  resutl;
    }
}

class SeqNode{
	int left;//左距离
	int right;//右距离
	int value;//连续的总数
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
