package com.springboot.uitest;

import com.springboot.bean.Browser;
import com.springboot.remote.BrowserService;
import com.springboot.remote.BrowserServiceImpl;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class})
public class BaseTest extends AbstractTestNGSpringContextTests{

    @Autowired
    BrowserService browserService;
    private int result;
    public WebDriver driver;

    @Test(priority = 1)
    public void start() {
        driver = browserService.startChrome();
        result = 111;
    }

    @AfterSuite
    public void end() {
        browserService.quitDriver();
    }

    @Test(priority = 2)
    public void lesseeAdminLogin() throws InterruptedException, IOException {
        System.out.println("aaaaa");
        Thread.sleep(3000);
    }

}
