package com.springboot.uitest.LesseeTest;

import com.springboot.bean.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, SystemAdmin.class, Lessee.class})
public class EditLesseeTest extends AbstractTestNGSpringContextTests {
    public WebDriver driver;
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    LesseeService lesseeService;
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
     * 修改租户信息
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    @Parameters({"lesseeName", "lesseeNewName", "remark", "logo"})
    public void editLessee(String lesseeName, String lesseeNewName, String remark, String logo) throws InterruptedException, IOException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(driver, "运营管理", "租户管理");
        //修改租户信息
        lesseeService.editLessee(driver, lesseeName, lesseeNewName, remark, logo);
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void end() {
        browserService.quitDriver();
    }

}