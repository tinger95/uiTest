package com.springboot.uitest.OperatorTest;

import com.springboot.bean.Browser;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, OperatorServiceImpl.class, SystemAdmin.class})
public class SearchOperatorTest extends AbstractTestNGSpringContextTests {
    public WebDriver driver;
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    SystemAdmin systemAdmin;

    PageList pageList = new PageList();

    @Test(priority = 1)
    public void start() {
        driver = browserService.startChrome();
    }

    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(driver, systemAdmin.lesseeName, systemAdmin.userName, systemAdmin.passWord);
        Thread.sleep(5000);
    }

    /**
     * 条件查询操作员信息
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    @Parameters({"searchType", "searchValue"})
    public void SearchOperator(String searchType, String searchValue) throws InterruptedException, IOException {
        //进入系统管理-操作员管理页面，显示操作员列表
        pageList.getList(driver, "系统管理", "操作员管理");

        //根据查询条件查询操作员信息
        operatorService.searchOperator(driver, searchType, searchValue);
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void end() {
        browserService.quitDriver();
    }

}