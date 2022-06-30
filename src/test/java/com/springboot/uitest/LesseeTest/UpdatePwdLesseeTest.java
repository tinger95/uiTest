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

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, SystemAdmin.class, Lessee.class})
public class UpdatePwdLesseeTest extends AbstractTestNGSpringContextTests {
    public WebDriver driver;
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    LesseeService lesseeService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    SystemAdmin systemAdmin;

    PageList pageList = new PageList();
    private String tempPWD;

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
     * 重置租户管理员密码
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    @Parameters({"lesseeName"})
    public void updatePwdLessee(String lesseeName) throws InterruptedException, IOException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(driver, "运营管理", "租户管理");
        //重置租户管理员密码
        tempPWD = lesseeService.updatePwdLessee(driver, lesseeName);
        Thread.sleep(5000);
    }

    /**
     * 退出登录
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4, dependsOnMethods = "updatePwdLessee")
    public void logout() throws InterruptedException, IOException {
        loginService.logout(driver);
        Thread.sleep(1000);
    }

    /**
     * 租户管理员登录租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 5, dependsOnMethods = "logout")
    @Parameters({"lesseeName"})
    public void lesseeLogin(String lesseeName) throws InterruptedException, IOException {
        //登录系统
        loginService.login(driver, lesseeName, "admin", tempPWD);
        Thread.sleep(3000);
    }

    /**
     * 修改密码为1qaz2wsx
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 6, dependsOnMethods = "lesseeLogin")
    public void updatePwd() throws InterruptedException, IOException {
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(driver);
        Thread.sleep(3000);
    }

    @Test(priority = 10, dependsOnMethods = "updatePwd")
    public void end() {
        browserService.quitDriver();
    }

}