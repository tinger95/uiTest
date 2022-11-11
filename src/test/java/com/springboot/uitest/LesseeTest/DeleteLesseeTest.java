package com.springboot.uitest.LesseeTest;

import com.springboot.common.PageList;
import com.springboot.data.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, SystemAdmin.class, Lessee.class})
public class DeleteLesseeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    LesseeService lesseeService;
    @Autowired
    SystemAdmin systemAdmin;

    PageList pageList = new PageList();

    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, systemAdmin.getLesseeName(), systemAdmin.getUserName(), systemAdmin.getPassWord());
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void changeMenu() throws InterruptedException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(OpenTest.driver, "运营管理", "租户管理");
    }

    /**
     * 注销租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4)
    @Parameters({"lesseeName"})
    public void deleteLessee(String lesseeName) throws InterruptedException, IOException {
        //注销指定租户
        lesseeService.deleteLessee(OpenTest.driver, lesseeName);
        Thread.sleep(5000);
    }

}