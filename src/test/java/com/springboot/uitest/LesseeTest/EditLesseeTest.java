package com.springboot.uitest.LesseeTest;

import com.springboot.common.PageList;
import com.springboot.data.Browser;
import com.springboot.data.EditLessee;
import com.springboot.data.Lessee;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, SystemAdmin.class, Lessee.class, EditLessee.class})
public class EditLesseeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    LesseeService lesseeService;
    @Autowired
    SystemAdmin systemAdmin;
    @Autowired
    EditLessee editLessee;

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
     * 修改租户信息
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4)
    public void editLessee() throws InterruptedException, IOException {
        //修改租户信息
        lesseeService.editLessee(OpenTest.driver, editLessee);
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void logout() throws InterruptedException, IOException {
        //登录系统
        loginService.logout(OpenTest.driver);
        Thread.sleep(3000);
    }
}