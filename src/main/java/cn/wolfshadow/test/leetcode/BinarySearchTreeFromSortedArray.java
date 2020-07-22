package cn.wolfshadow.test.leetcode;


import cn.wolfshadow.test.TreeNode;

import java.util.Arrays;

/**
 * 题目：将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class BinarySearchTreeFromSortedArray {

    public static void main(String[] args) {
        int[] arrays = {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST(arrays);
        System.out.println(treeNode.toString());
    }

    private static TreeNode sortedArrayToBST(int[] arrays){
        if (arrays == null || arrays.length == 0) return  null;
        int length = arrays.length;
        int center = (arrays.length)/2;
        TreeNode root = new TreeNode(arrays[center]);
        root.left = sortedArrayToBST(subArray(arrays,0,center));
        root.right = sortedArrayToBST(subArray(arrays,center+1,arrays.length));
        return  root;

    }
    private static int[] subArray(int [] input, int start,int end){
        if (input == null ) return  null;
        if (start >= input.length) return null;
        if (start >= end) return null;
        return Arrays.copyOfRange(input,start,end);
    }

}


