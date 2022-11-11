package com.springboot.uitest.LesseeTest;

import com.springboot.common.PageList;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {LesseeServiceImpl.class, LoginServiceImpl.class})
public class CheckLesseeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LesseeService lesseeService;
    @Autowired
    LoginService loginService;

    PageList pageList = new PageList();

    @Test(priority = 1)
    @Parameters({"lesseeName", "userName", "passWord"})
    public void login(String lesseeName, String userName, String passWord) throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lesseeName, userName, passWord);
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void changeMenu() throws InterruptedException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(OpenTest.driver, "运营管理", "租户管理");

    }

    /**
     * 查看租户信息
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4)
    @Parameters({"lesseeName"})
    public void checkLessee(String lesseeName) throws InterruptedException, IOException {
        //查看指定租户信息
        lesseeService.checkLessee(OpenTest.driver, lesseeName);
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void logout() throws InterruptedException, IOException {
        //登录系统
        loginService.logout(OpenTest.driver);
        Thread.sleep(3000);
    }
}