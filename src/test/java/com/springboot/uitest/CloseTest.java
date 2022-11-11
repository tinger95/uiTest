package com.springboot.uitest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class CloseTest extends AbstractTestNGSpringContextTests {
    @Test
    public void end() {
        try {
            Thread.sleep(2000);
            OpenTest.driver.quit();
            System.out.println("成功关闭浏览器！");
        } catch (Exception e) {
            System.out.println("关闭浏览器报错" + e);
        }
    }
}
