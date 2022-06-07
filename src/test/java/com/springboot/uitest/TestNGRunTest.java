package com.springboot.uitest;

import com.springboot.practice.Config;
import com.springboot.practice.TestNgRunService;
import com.springboot.practice.TestNgRunServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = {TestNgRunServiceImpl.class, Config.class})
public class TestNGRunTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private TestNgRunService testNgRunService;
    @Test
    public void run(){
        String url = testNgRunService.runAll();
        System.out.println(url);
    }
}
