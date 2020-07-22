package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;
import cn.wolfshadow.test.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class BinarySearchTreeGenerate implements Testable {
    @Override
    public void test() {
        int n = 3;
        int start = 1;
        int end = n;
        List<TreeNode> treeNodes = generatTree(n, 1);
        for(TreeNode node : treeNodes){
            List<Integer> list = TreeNode.levelSort(node);
            System.out.println(list.toString());
        }

    }

    public List<TreeNode> generatTree(int n,int start){
        List<TreeNode> list = new ArrayList<>();
        if (n < 1) return list;
        if (n == 1) {
            list.add(new TreeNode(start));
            return  list;
        } else{
            //分别以当前节点作为根结点
            for (int i = 1; i <= n; i++) {
                int realI = start + i - 1;//真实节点的值
                List<TreeNode> left = generatTree(i - 1,start);
                List<TreeNode> right = generatTree(n - i, realI+1);
                if (left.isEmpty() && right.isEmpty()){
                    //理论上不存在这种情况
                }else if (left.isEmpty()){
                    for (TreeNode root : right) {
                        TreeNode thisNode = new TreeNode(realI);
                        thisNode.left = null;
                        thisNode.right = root;
                        list.add(thisNode);
                    }
                }else if (right.isEmpty()){
                    for (TreeNode root : left) {
                        TreeNode thisNode = new TreeNode(realI);
                        thisNode.right = null;
                        thisNode.left = root;
                        list.add(thisNode);
                    }
                }else {
                    for (TreeNode lNode : left) {
                        for (TreeNode rNode : right) {
                            TreeNode thisNode = new TreeNode(realI);
                            thisNode.left = lNode;
                            thisNode.right = rNode;
                            list.add(thisNode);
                        }
                    }
                   
                }
            }
        }
        return  list;
    }
    

}
