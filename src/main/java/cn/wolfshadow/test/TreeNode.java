package cn.wolfshadow.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     * 先根遍历：使用递归
     * @param node
     * @return
     */
    public static void preSort(TreeNode node, List<Integer> numbers){
        if (node == null) return;
        numbers.add(node.val);
        if (node.left != null) preSort(node.left,numbers);
        if (node.right != null) preSort(node.right,numbers);
    }

    /**
     * 层次遍历：借助队列
     * @param node
     * @return
     */
    public static List<Integer> levelSort(TreeNode node){
        List<Integer> result = new ArrayList<>();
        if (node == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            result.add(poll.val);
            if (poll.left != null) queue.add(poll.left);
            if (poll.right != null) queue.add(poll.right);
        }
        return  result;
    }

}
