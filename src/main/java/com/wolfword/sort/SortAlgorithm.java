package com.wolfword.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 常用排序算法
 * @author WolfShadow
 * @date 2020年5月13日
 */
public class SortAlgorithm {

	public static void main(String[] args) {
		//生成随机数组
		int count = 20000;
		int[] numbers = numberCreator(count);
		System.out.println("生成随机数：");
		for(int i=0; i<numbers.length; i++) {
			System.out.print(numbers[i]+" ");
			if (i % 20 == 19) {
				System.out.println();
			}
		}
		System.out.println();
		
		String str = "排序算法－%s：";
		long start = System.currentTimeMillis();
		/*
		 * 各种排序算法（从小到大排序）
		 */
		//交换排序－冒泡排序
//		numbers = bubble(numbers);
//		str = String.format(str, "冒泡排序");
		
		//交换排序－快速排序
//		fast(numbers, 0, numbers.length-1);
//		str = String.format(str, "快速排序");		
		
		//选择排序－简单选择排序
//		numbers = select(numbers);
//		str = String.format(str, "简单选择排序");
		
		//插入排序－简单插入排序
//		insert(numbers);
//		str = String.format(str, "简单插入排序");		
		
		//插入排序－希尔排序
//		shell(numbers);
//		str = String.format(str, "希尔排序");

		//归并排序－二路归并排序
		numbers = mergeSort(numbers);
		str = String.format(str, "二路归并排序");

		
		long end = System.currentTimeMillis();
		//输出排序结果
		System.out.println(str);
		for(int i=0; i<numbers.length; i++) {
			System.out.print(numbers[i]+" ");
			if (i % 20 == 19) {
				System.out.println();
			}
		}
		System.out.println(str);
		System.out.println("随机数目："+count);
		System.out.println("总耗时："+(end-start)+" ms");
		
		

	}
	
	public static int[] numberCreator(int count){
		int[] result = new int[count];
		Random random = new Random();
		/*IntStream ints = random.ints(count);
		OfInt iterator = ints.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			result[i++] = iterator.nextInt();
		}*/
		for(int i=0; i<count; i++){
			result[i] = random.nextInt(count);
		}
		return result;
	}
	
	 /**
	 * 交换排序－冒泡排序
	 * @param input
	 * @return
	 * @auther WolfShadow
	 * @date 2020年5月13日
	 */
	public static int[] bubble(int[] input){
		int[] result = input;
		int temp = 0;
		for(int i=0; i<result.length-1; i++){
			for(int j=0; j<result.length-1-i; j++){
				if (result[j] > result[j+1]){
					temp = result[j];
					result[j] = result[j+1];
					result[j+1] = temp;
				}
			}
		}
		return result;
	}
	/**
	 * 交换排序－快速排序
	 * @param input
	 * @return
	 * @auther WolfShadow
	 * @date 2020年5月13日
	 */
	public static void fast(int[] input, int left, int right){
		int start = left;
		int end = right;
		//将第一个数作为参照值
		int temp = input[start];
		while(start < end){
			//从后向前滑动end游标，直到找到小于temp的位置或者碰到start
			while(start < end && input[end] >= temp){
				end --;
			}
			//将小于参照值的数放到start位置（如果是start=end的情况，此行相当于无用功）
			input[start] = input[end];
			//从前向后滑动start游标，直到找到大于temp的位置或者碰到end
			while (start < end && input[start] <= temp) {
				start ++;
			}
			//将大于参照值的数放到end位置（如果是start=end的情况，此行相当于无用功）
			input[end] = input[start];
		}
		//此时游标的位置设值为“参照值”
		input[start] = temp;
		//迭代左侧排序
		if (start - 1 > left) {
			fast(input, left, start-1);
		}
		//迭代右侧排序
		if (start + 1 < right) {
			fast(input, start+1, right);
		}
	}
	/**
	 * 选择排序－简单选择排序
	 * @param input
	 * @return
	 * @auther WolfShadow
	 * @date 2020年5月13日
	 */
	public static int[] select(int[] input){
		int[] result = input;
		int temp = 0;
		for(int i=0; i<result.length-1; i++){
			int min = i;
			for(int j=i+1; j<result.length; j++){
				if (result[j] < result[min]){
					min = j;
				}
			}
			temp = result[i];
			result[i] = result[min];
			result[min] = temp;
		}
		return result;
	}
	/**
	 * 插入排序－简单插入排序
	 * @param input
	 * @return
	 * @auther WolfShadow
	 * @date 2020年5月13日
	 */
	public static void insert(int[] input){
		//从第二个数开始循环，依次同前面的数比较、互换
		for(int i=1; i<input.length; i++){
			//空出当前位置，取此值作为对比数temp
			int blank = i;
			int temp = input[i];
			for(int j=i-1; j>=0; j--){
				/*
				 * 如果j处数大于最初空出位置的数，则将j处数放到空出位置，j处空出；
				 * 否则，即前面的数值小于对比数temp，中止内层循环
				 */
				if (input[j] <= temp) {
					break;
				}
				input[blank] = input[j];
				blank = j;
			}
			//循环完成，将对比数放回当前空出的位置
			input[blank] = temp;
		}
	}
	/**
	 * 插入排序－希尔排序
	 * @param input
	 * @return
	 * @auther WolfShadow
	 * @date 2020年5月13日
	 */
	public static void shell(int[] input){
		//以length/2为算法，通过除以2来逐渐缩小增量
		int length = input.length;
		int n = length/2;
		//n逐渐减半；当n=1时，就和简单插入排序一样
		while(n >= 1){
			for(int i=n; i<length; i++){
				//空出当前位置，取此值作为对比数temp
				int blank = i;
				int temp = input[i];
				for(int j=i-n; j>=0; j-=n){
					/*
					 * 如果j处数大于最初空出位置的数，则将j处数放到空出位置，j处空出；
					 * 否则，即前面的数值小于对比数temp，中止内层循环
					 */
					if (input[j] <= temp) {
						break;
					}
					input[blank] = input[j];
					blank = j;
				}
				//循环完成，将对比数放回当前空出的位置
				input[blank] = temp;
			}
			n = n / 2;
		}
	}

	public static int[] mergeSort(int[] arrays){
		if (arrays == null) { return  null;}
		if (arrays.length <= 1) return arrays;
		if (arrays.length == 2){
			if (arrays[0] > arrays[1]){
				int temp = arrays[0];
				arrays[0] = arrays[1];
				arrays[1] = temp;
			}
			return arrays;
		}else {
			int half = arrays.length / 2;
			int[] resutl = new int[arrays.length];
			int[] leftArray = Arrays.copyOfRange(arrays, 0, half);
			int[] rightArray = Arrays.copyOfRange(arrays, half, arrays.length);
			leftArray = mergeSort(leftArray);
			rightArray = mergeSort(rightArray);
			int i = 0,j=0,index=0;
			while (i < leftArray.length || j < rightArray.length){
				if (i >= leftArray.length){//左边已遍历完成
					resutl[index++] = rightArray[j++];
					continue;
				}
				if (j >= rightArray.length){//右边已遍历完成
					resutl[index++] = leftArray[i++];
					continue;
				}
				if (leftArray[i] < rightArray[j]){
					resutl[index++] = leftArray[i++];
				}else if(leftArray[i] == rightArray[j]){
					resutl[index++] = leftArray[i++];
					resutl[index++] = rightArray[j++];
				}else {
					resutl[index++] = rightArray[j++];
				}
			}
			return  resutl;
		}
	}
	
	
	 /**
	 * 交换数组2个位置的值
	 * 不借助中间变量的一种巧妙方式
	 * @param arrays
	 * @param a
	 * @param b
	 * @auther WolfShadow
	 * @date 2020年5月14日
	 */
	private static void swap(int[] arrays, int a, int b){
		arrays[a] = arrays[a] + arrays[b];
		arrays[b] = arrays[a] - arrays[b];
		arrays[a] = arrays[a] - arrays[b];
	}
	

}
