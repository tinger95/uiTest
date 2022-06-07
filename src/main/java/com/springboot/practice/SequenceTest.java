package com.springboot.practice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SequenceTest {
    int i;

    @BeforeClass(groups = "before")
    public void beforeTest0() {
        i = 10;
        System.out.println("beforeTest0");
    }

    @Test(priority = 1, groups = {"sequence"})
    public void test1() {
        System.out.println("test1 + sequence");
    }

    @Test(priority = 3, groups = {"sequence", "filter3"})
    public void test3() {
        System.out.println("test3 + sequence,filter3");
    }

    @Test(priority = 2, groups = {"sequence", "filter2"})
    public void test2() {
        System.out.println("test2 + sequence,filter2");
    }

    @Test(priority = 4, groups = {"sequence", "filter4"})
    public void test4() {
        System.out.println("test4 + sequence,filter4");
    }

    @Parameters({"testParam"})
    @Test(priority = 5, groups = {"sequence", "param"})
    public void test5(int testParam) {
        System.out.println(testParam);
        System.out.println("test5 + sequence,param");
    }

    @Test(priority = 5, groups = {"before"})
    public void test5() {
        System.out.println(i);
        System.out.println("test5 + sequence,param");
    }
//
//    @AfterGroups(groups = {"before"})
//    public void afterTest0() {
//        i = -1;
//        System.out.println("afterTest0" + i);
//    }
//
//    @DataProvider(name = "test1")
//    public Object[][] createData1() {
//        return new Object[][] {
//                { "Cedric", new Integer(36) },
//                { "Anne", new Integer(37)},
//        };
//    }

    @Test(dataProvider = "test1")
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

}
