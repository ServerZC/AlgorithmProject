package cn.wolfshadow.test.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目： 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class SumTreePath {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node6;
        node3.right = node7;
        node2.left = node4;
        node2.right = node5;
        node5.left = node8;

        long start = System.currentTimeMillis();
        int sum = 22;
        System.out.println(hasSum(root,sum));
        long end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start)+" ms");

    }

    /**
     * 树的深度优先遍历
     * @param root
     * @param sum
     * @return
     */
    private static boolean hasSum(TreeNode root, int sum){
        if (root == null) return false;
        //递归
        //return withRecursion(root,sum);
        //栈
        return withStack(root,sum);
    }

    /**
     * 使用递归实现
     * 耗时更短，效率高
     * @param node
     * @param sum
     * @return
     */
    private static boolean withRecursion(TreeNode node, int sum){
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left == null && right == null){
            if (node.val == sum) return true;
        }
        if (left != null){
            left.val += node.val;
            boolean leftResult = withRecursion(left, sum);
            if(leftResult) return true;
        }
        if (right != null) {
            right.val += node.val;
            boolean rightResult = withRecursion(right, sum);
            if(rightResult) return true;
        }

        return false;
    }

     /**
     * 借助栈实现，遍历时修改节点值，表示该节点到根的和值
     * @param root
     * @param sum
     * @return
     */
    private static boolean withStack(TreeNode root, int sum){
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left == null && right == null){
                if (node.val == sum) return true;
            }
            if (left != null){
                left.val += node.val;
                stack.push(left);
            }
            if (right != null) {
                right.val += node.val;
                stack.push(right);
            }

        }
        return false;
    }


}

