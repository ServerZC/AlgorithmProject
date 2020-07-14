package cn.wolfshadow.test;

import cn.wolfshadow.test.leetcode.MinPathSum;
import cn.wolfshadow.test.others.ArrayCreator;

public class TestMain {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        Testable testable = new MinPathSum();
        testable.test();


        long end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start) +" ms");


    }
}
