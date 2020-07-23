package cn.wolfshadow.test;

import cn.wolfshadow.test.leetcode.*;
import cn.wolfshadow.test.others.*;

import java.io.BufferedInputStream;
import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //String className = "Bipartite.class";

        Testable testable = new MinPathSum2();
        testable.test();

        long end = System.currentTimeMillis();
        System.out.println("耗时： "+(end-start) +" ms");


    }

    public static Testable getTestable4Name(String className){
        try {
            Class aClass = Class.forName(className);
            return  (Testable)aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
