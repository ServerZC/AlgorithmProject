package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;
import cn.wolfshadow.test.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class MaxDepth implements Testable {
    @Override
    public void test() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        System.out.println(withRecurse(node1));
        System.out.println(withBfs(node1));
        System.out.println(withDfs(node1));
    }

    public int maxDepth(TreeNode root){
        return 0;
    }

    /**
     * 递归实现深度遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了5.75%的用户
     * @param node
     * @return
     */
    private int withRecurse(TreeNode node){
        if (node == null) return 0;
        TreeNode left = node.left;
        TreeNode right = node.right;
        return 1+Math.max(withRecurse(left),withRecurse(right));
    }

    /**
     * 广度优先搜索（breadth first search）
     * 执行用时：1 ms, 在所有 Java 提交中击败了17.33%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了5.75%的用户
     * @return
     */
    private int withBfs(TreeNode node){
        if (node == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        node.val = 1;//设置第一个节点值为1
        queue.offer(node);
        int max = 0;
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            max = poll.val;
            TreeNode left = poll.left;
            if (left!=null){
                left.val = poll.val+1;
                queue.offer(left);
            }
            TreeNode right = poll.right;
            if (right!=null){
                right.val = poll.val+1;
                queue.offer(right);
            }
        }
        return max;
    }

    /**
     * 深度优先遍历：借助栈
     * 执行用时：3 ms, 在所有 Java 提交中击败了5.16%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了6.90%的用户
     * @param node
     * @return
     */
    private int withDfs(TreeNode node){
        if (node == null) return 0;
        Deque<TreeNode> stack = new LinkedList<>();
        node.val = 1;//设置第一个节点值为1
        stack.push(node);
        int max = 0;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            max = Math.max(max,pop.val);
            TreeNode left = pop.left;
            if (left!=null){
                left.val = pop.val+1;
                stack.push(left);
            }
            TreeNode right = pop.right;
            if (right!=null){
                right.val = pop.val+1;
                stack.push(right);
            }

        }
        return max;
    }
}
