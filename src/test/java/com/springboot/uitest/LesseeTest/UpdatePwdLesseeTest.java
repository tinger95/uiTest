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

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, SystemAdmin.class, Lessee.class})
public class UpdatePwdLesseeTest extends AbstractTestNGSpringContextTests {
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
     * 重置租户管理员密码
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4)
    @Parameters({"lesseeName"})
    public void updatePwdLessee(String lesseeName) throws InterruptedException, IOException {
        //重置租户管理员密码
        tempPWD = lesseeService.updatePwdLessee(OpenTest.driver, lesseeName);
        Thread.sleep(5000);
    }

    /**
     * 退出登录
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 5)
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
    @Test(priority = 6)
    @Parameters({"lesseeName"})
    public void lesseeLogin(String lesseeName) throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lesseeName, "admin", tempPWD);
        Thread.sleep(3000);
    }

    /**
     * 修改密码为1qaz2wsx
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 6)
    @Parameters({"passWord"})
    public void updatePwd(String passWord) throws InterruptedException, IOException {
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, passWord);
        Thread.sleep(3000);
    }
}