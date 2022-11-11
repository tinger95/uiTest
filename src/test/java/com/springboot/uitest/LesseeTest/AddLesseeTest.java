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
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, SystemAdmin.class, Lessee.class})
public class AddLesseeTest extends AbstractTestNGSpringContextTests {
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
    @Autowired
    Lessee lessee;

    private String tempPWD;

    PageList pageList = new PageList();

    /**
     * admin登录系统租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, systemAdmin.getLesseeName(), systemAdmin.getUserName(), systemAdmin.getPassWord());
        Thread.sleep(5000);
    }

    /**
     * 超级管理员新增租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    public void addLessee() throws InterruptedException, IOException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(OpenTest.driver, "运营管理", "租户管理");

        //新增租户，密码为返回值
        tempPWD = lesseeService.addLessee(OpenTest.driver, lessee);
        System.out.println(tempPWD);
        Thread.sleep(5000);
    }

    /**
     * 退出登录
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4, dependsOnMethods = "addLessee")
    public void logout() throws InterruptedException, IOException {
        loginService.logout(OpenTest.driver);
        Thread.sleep(1000);
    }

    /**
     * 租户管理员登录租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 5, dependsOnMethods = "logout")
    public void lesseeLogin() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lessee.getName(), "admin", tempPWD);
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
        operatorService.updatePassWord(OpenTest.driver, lessee.getPassWord());
        Thread.sleep(3000);
    }

}