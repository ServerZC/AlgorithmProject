package cn.wolfshadow.test.leetcode;

import cn.wolfshadow.test.Testable;

import java.util.*;

/**
 * 题目：不同的二叉搜索树
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。
 * 每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 *
 * 注意:
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 */
public class Bipartite implements Testable {

    @Override
    public void test() {
        int[][] graph = {
                {1,3},
                {0,2},
                {1,3},
                {0,2}};
        /*int[][] graph = {
                {1,2,3},
                {0,2},
                {0,1,3},
                {0,2}};*/
        /*int [][] graph = {
                {},{10,44,62},{98},{59},{90},{},{31,59},{52,58},{},{53},
                {1,63},{51,71},{18,64},{24,26,45,95},{61,67,96},{},{40},
                {39,74,79},{12,21,72},{35,85},{86,88},{18,76},{71,80},
                {27,58,85},{13,26,87},{75,94},{13,24,68,77,82},{23},
                {56,96},{67},{56,73},{6},{41},{50,88,91,94},{},
                {19,72,92},{59},{49},{49,89},{17},{16},{32,84,86},
                {61,73,77},{94,98},{1,74},{13,57,90},{},{93},{},
                {37,38,54,68},{33},{11},{7,85},{9},{49},{61},
                {28,30,87,93},{45,69,77},{7,23,76},{3,6,36,62},
                {81},{14,42,55,62},{1,59,61},{10},{12,93},{},{96},
                {14,29,70,73},{26,49,71,76},{57,83},{67},{11,22,68,89},
                {18,35},{30,42,67},{17,44},{25},{21,58,68},{26,42,57,95},
                {},{17},{22,83},{60},{26,83,84,94},{69,80,82},{41,82},
                {19,23,52},{20,41},{24,56},{20,33},{38,71,99},{4,45},
                {33},{35},{47,56,64},{25,33,43,82},{13,77},{14,28,66},{},{2,43},{89}
        };*/
        System.out.println(dfs(graph));
    }

    /**
     * 分析：【思路可能不正确，非正确答案】
     * 1、独立集合的所有节点之间肯定没有连接
     * 2、先看第1组数据，表明节点1和数组中的所有节点相连，因此节点1和其他节点不能在同一集合，同时说明数组内的节点必须在同一集合中
     * 3、默认节点1在集合B，于是将其他节点放入集合A
     * 4、遍历其他数组，如果数组中有1个节点存在于集合A，则将数组中的节点全部放入A
     * 5、如果遍历完成后：A集合有新增，继续遍历
     * 6、最终如果A集合节点数小于总共有边的节点数，true；否则false
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph){
        if (graph == null) return false;
        int n = graph.length;
        if (n < 2) return false;
        if (n == 2 ) return true;
        Set<Integer> setA = new HashSet<>();//集合A
        int counter = 0;//记录有边的节点数
        List<int[]> lastArray = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].length > 0) {
                lastArray.add(graph[i]);
                counter ++;
            }
        }
        boolean matching = true;//可匹配
        while (lastArray.size() > 0 && matching){
            matching = false;//默认没有匹配节点
            Iterator<int[]> iterator = lastArray.iterator();
            while (iterator.hasNext()){
                int[] next = iterator.next();
                List<Integer> temp = new ArrayList<>();
                boolean inA = false;
                for (int i = 0; i < next.length; i++) {
                    temp.add(next[i]);
                    if (setA.contains(next[i])) inA = true;
                }
                if (setA.isEmpty() || inA){
                    setA.addAll(temp);
                    iterator.remove();//移除当前数组
                    matching = true;
                }
            }
        }

        return setA.size() < counter;
    }

    /**
     * 深度优先遍历，借助栈实现
     * 耗时比较长
     *
     * @param graph
     * @return
     */
    public boolean dfs(int[][] graph){
        if (graph == null) return false;
        int n = graph.length;
        if (n <= 2 ) return true;
        Deque<Integer> stack = new LinkedList<>();
        Set<Integer> setA = new HashSet<>();//集合A
        Set<Integer> setB = new HashSet<>();//集合B
        int counter  = 0;//记录已经标记的节点数

        //1、第一步找到一个有边的节点
        int i = 0;
        for (; i < n; i++) {
            int[] nodes = graph[i];
            if (nodes.length > 0) {
                if (setA.contains(i) || setB.contains(i)){
                    continue;
                }

                setA.add(i);
                stack.push(i);
                counter ++;
                //2、找到一个有边节点后，由此开始进行深度优先遍历，直到栈为空；如果此时还没有遍历完成所有节点，则继续寻找未标记过的有边节点
                while (!stack.isEmpty()){
                    Integer index = stack.pop();
                    int[] ints = graph[index];
                    if (setA.contains(index)){
                        for(int j : ints){
                            if (setA.contains(j)) return false;
                            boolean added = setB.add(j);
                            if (added){//如果添加失败，说明已经在此集合中，直接忽略
                                stack.push(j);
                                counter ++;
                            }
                        }
                    }else{
                        for(int j : ints){
                            if (setB.contains(j)) return false;
                            boolean added = setA.add(j);
                            if (added){//如果添加失败，说明已经在此集合中，直接忽略
                                stack.push(j);
                                counter ++;
                            }

                        }
                    }
                }
            }else {
                //空节点(无边节点)跳过，表示其可以分到任意组;此时仍然需要将计数器+1
                counter ++;
            }
            if (counter == n) break;
        }
 
        return true;
    }
}

/**
 * 力扣平台：
 * 深度优先遍历答案
 */
class Solution {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean valid;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED) {
                dfs(i, RED, graph);
            }
        }
        return valid;
    }

    public void dfs(int node, int c, int[][] graph) {
        color[node] = c;
        int cNei = c == RED ? GREEN : RED;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, cNei, graph);
                if (!valid) {
                    return;
                }
            } else if (color[neighbor] != cNei) {
                valid = false;
                return;
            }
        }
    }

}
